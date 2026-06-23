package com.empresa.soporte.service;

import com.empresa.soporte.dto.*;
import com.empresa.soporte.exception.BadRequestException;
import com.empresa.soporte.exception.ResourceNotFoundException;
import com.empresa.soporte.model.*;
import com.empresa.soporte.persistence.InMemoryDatabase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación concreta del servicio de solicitudes de soporte técnico.
 * Coordina el mapeo de datos entre DTOs y modelos, y aplica reglas de negocio.
 * Utiliza constructor injection para mayor testabilidad y bajo acoplamiento.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final InMemoryDatabase database;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param database Componente de persistencia en memoria
     */
    public SolicitudServiceImpl(InMemoryDatabase database) {
        this.database = database;
    }

    @Override
    public List<SolicitudResponseDTO> getAllSolicitudes() {
        return database.findAllSolicitudes().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SolicitudResponseDTO getSolicitudById(Long id) {
        Solicitud solicitud = database.findSolicitudById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de soporte no encontrada con el ID: " + id));
        return convertToResponseDTO(solicitud);
    }

    @Override
    public SolicitudResponseDTO createSolicitud(SolicitudCreateDTO dto) {
        // Validar existencia del cliente antes de registrar la solicitud
        Cliente cliente = database.findClienteById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID: " + dto.getClienteId()));

        // Crear modelo de dominio
        Solicitud solicitud = new Solicitud();
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setPrioridad(dto.getPrioridad());
        solicitud.setCliente(cliente);
        
        // Guardar en la base de datos simulada
        Solicitud guardada = database.saveSolicitud(solicitud);
        return convertToResponseDTO(guardada);
    }

    @Override
    public SolicitudResponseDTO updateSolicitud(Long id, SolicitudUpdateDTO dto) {
        // Buscar la solicitud existente
        Solicitud solicitud = database.findSolicitudById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada para actualizar con el ID: " + id));

        // Procesar técnico si se provee
        Tecnico tecnico = null;
        if (dto.getTecnicoId() != null) {
            tecnico = database.findTecnicoById(dto.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con el ID: " + dto.getTecnicoId()));

            // Validar disponibilidad del técnico si es una nueva asignación
            boolean esNuevoTecnico = solicitud.getTecnico() == null || !solicitud.getTecnico().getId().equals(tecnico.getId());
            if (esNuevoTecnico && !tecnico.isDisponible()) {
                throw new BadRequestException("El técnico '" + tecnico.getNombre() + "' no está disponible para nuevas asignaciones.");
            }
        }

        // Reglas de negocio sobre estados de la solicitud
        EstadoSolicitud nuevoEstado = dto.getEstado();

        // 1. Validar que si el estado es EN_PROCESO, tenga un técnico asignado obligatoriamente
        if (nuevoEstado == EstadoSolicitud.EN_PROCESO && tecnico == null) {
            throw new BadRequestException("No se puede cambiar el estado de la solicitud a EN_PROCESO sin asignar un técnico de soporte.");
        }

        // 2. Validar que para resolver o cerrar, tenga un técnico asignado obligatoriamente
        if ((nuevoEstado == EstadoSolicitud.RESUELTA || nuevoEstado == EstadoSolicitud.CERRADA) && tecnico == null) {
            throw new BadRequestException("No se puede marcar la solicitud como " + nuevoEstado + " si no cuenta con un técnico asignado.");
        }

        // 3. Validar comentarios de resolución si la solicitud cambia a RESUELTA o CERRADA
        if ((nuevoEstado == EstadoSolicitud.RESUELTA || nuevoEstado == EstadoSolicitud.CERRADA) 
                && (dto.getComentarios() == null || dto.getComentarios().trim().isEmpty())) {
            throw new BadRequestException("Se requiere ingresar comentarios de resolución detallados para pasar el estado a " + nuevoEstado + ".");
        }

        // Aplicar los cambios al modelo de dominio
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setPrioridad(dto.getPrioridad());
        solicitud.setEstado(nuevoEstado);
        solicitud.setTecnico(tecnico);
        solicitud.setComentarios(dto.getComentarios());

        // Guardar cambios
        Solicitud actualizada = database.saveSolicitud(solicitud);
        return convertToResponseDTO(actualizada);
    }

    @Override
    public void deleteSolicitud(Long id) {
        // Validar existencia de la solicitud antes de eliminar
        if (!database.findSolicitudById(id).isPresent()) {
            throw new ResourceNotFoundException("No se puede eliminar la solicitud porque no existe con el ID: " + id);
        }
        database.deleteSolicitud(id);
    }

    // --- MÉTODOS DE MAPEO MANUAL (DTO <-> MODEL) ---

    private SolicitudResponseDTO convertToResponseDTO(Solicitud solicitud) {
        ClienteDTO clienteDTO = convertToClienteDTO(solicitud.getCliente());
        TecnicoDTO tecnicoDTO = solicitud.getTecnico() != null ? convertToTecnicoDTO(solicitud.getTecnico()) : null;

        return new SolicitudResponseDTO(
                solicitud.getId(),
                solicitud.getDescripcion(),
                solicitud.getFechaCreacion(),
                solicitud.getEstado(),
                solicitud.getPrioridad(),
                clienteDTO,
                tecnicoDTO,
                solicitud.getComentarios()
        );
    }

    private ClienteDTO convertToClienteDTO(Cliente cliente) {
        if (cliente == null) return null;
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getTelefono()
        );
    }

    private TecnicoDTO convertToTecnicoDTO(Tecnico tecnico) {
        if (tecnico == null) return null;
        return new TecnicoDTO(
                tecnico.getId(),
                tecnico.getNombre(),
                tecnico.getEspecialidad(),
                tecnico.isDisponible()
        );
    }
}
