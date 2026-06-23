package com.empresa.soporte.persistence;

import com.empresa.soporte.model.*;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Componente que simula una persistencia de datos local (en memoria).
 * Utiliza estructuras de colecciones concurrentes (ConcurrentHashMap, AtomicLong)
 * para garantizar la seguridad y consistencia en entornos concurrentes (Thread-Safe).
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
@Component
public class InMemoryDatabase {

    // Colecciones concurrentes para almacenar los datos
    private final Map<Long, Cliente> clientes = new ConcurrentHashMap<>();
    private final Map<Long, Tecnico> tecnicos = new ConcurrentHashMap<>();
    private final Map<Long, Solicitud> solicitudes = new ConcurrentHashMap<>();

    // Secuencias atómicas para la autogeneración de IDs únicos (autoincrementales)
    private final AtomicLong clienteIdSequence = new AtomicLong(0);
    private final AtomicLong tecnicoIdSequence = new AtomicLong(0);
    private final AtomicLong solicitudIdSequence = new AtomicLong(0);

    /**
     * Inicializa la base de datos simulada cargando registros de prueba (Seeders)
     * al arrancar la aplicación de Spring Boot.
     */
    @PostConstruct
    public void seedDatabase() {
        // Pre-cargar Clientes
        Cliente c1 = saveCliente(new Cliente(null, "Banco Nacional", "contacto@banconal.com", "+51 987654321"));
        Cliente c2 = saveCliente(new Cliente(null, "Clínica San José", "soporte@clinicasj.com", "+51 912345678"));
        Cliente c3 = saveCliente(new Cliente(null, "Minera del Sur", "it@minerasur.com", "+51 933445566"));

        // Pre-cargar Técnicos
        Tecnico t1 = saveTecnico(new Tecnico(null, "Carlos Mendoza", "Redes e Infraestructura", true));
        Tecnico t2 = saveTecnico(new Tecnico(null, "Ana Torres", "Desarrollo de Software", true));
        Tecnico t3 = saveTecnico(new Tecnico(null, "David López", "Seguridad Informática", false));

        // Pre-cargar Solicitudes
        saveSolicitud(new Solicitud(
                null,
                "Caída del enlace principal de internet en sede central. Afecta al sistema de transacciones.",
                LocalDateTime.now().minusHours(2),
                EstadoSolicitud.EN_PROCESO,
                PrioridadSolicitud.CRITICA,
                c1,
                t1,
                "Se verificó la caída física de la fibra óptica. Técnico en ruta coordinando con el ISP."
        ));

        saveSolicitud(new Solicitud(
                null,
                "Error de sincronización de la base de datos de auditoría con el ERP central.",
                LocalDateTime.now().minusDays(1),
                EstadoSolicitud.ABIERTA,
                PrioridadSolicitud.ALTA,
                c2,
                null,
                "Esperando asignación de especialista de base de datos."
        ));
        
        saveSolicitud(new Solicitud(
                null,
                "Instalación y configuración del nuevo software antivirus corporativo.",
                LocalDateTime.now().minusDays(3),
                EstadoSolicitud.RESUELTA,
                PrioridadSolicitud.MEDIA,
                c3,
                t2,
                "Instalación exitosa en las 15 terminales de la sede administrativa."
        ));
    }

    // --- MÉTODOS PARA CLIENTE ---

    public List<Cliente> findAllClientes() {
        return new ArrayList<>(clientes.values());
    }

    public Optional<Cliente> findClienteById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(clientes.get(id));
    }

    public Cliente saveCliente(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(clienteIdSequence.incrementAndGet());
        }
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    public boolean deleteCliente(Long id) {
        if (id == null) return false;
        return clientes.remove(id) != null;
    }

    // --- MÉTODOS PARA TÉCNICO ---

    public List<Tecnico> findAllTecnicos() {
        return new ArrayList<>(tecnicos.values());
    }

    public Optional<Tecnico> findTecnicoById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(tecnicos.get(id));
    }

    public Tecnico saveTecnico(Tecnico tecnico) {
        if (tecnico.getId() == null) {
            tecnico.setId(tecnicoIdSequence.incrementAndGet());
        }
        tecnicos.put(tecnico.getId(), tecnico);
        return tecnico;
    }

    public boolean deleteTecnico(Long id) {
        if (id == null) return false;
        return tecnicos.remove(id) != null;
    }

    // --- MÉTODOS PARA SOLICITUD ---

    public List<Solicitud> findAllSolicitudes() {
        return new ArrayList<>(solicitudes.values());
    }

    public Optional<Solicitud> findSolicitudById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(solicitudes.get(id));
    }

    public Solicitud saveSolicitud(Solicitud solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(solicitudIdSequence.incrementAndGet());
        }
        solicitudes.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    public boolean deleteSolicitud(Long id) {
        if (id == null) return false;
        return solicitudes.remove(id) != null;
    }
}
