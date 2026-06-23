package com.empresa.soporte.service;

import com.empresa.soporte.dto.SolicitudCreateDTO;
import com.empresa.soporte.dto.SolicitudResponseDTO;
import com.empresa.soporte.dto.SolicitudUpdateDTO;

import java.util.List;

/**
 * Interfaz que define los servicios y operaciones de negocio para la gestión de solicitudes.
 * Proporciona un acoplamiento débil entre el controlador y la lógica de negocio.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public interface SolicitudService {

    /**
     * Recupera todas las solicitudes registradas en el sistema.
     *
     * @return Lista de solicitudes estructuradas en DTOs de salida.
     */
    List<SolicitudResponseDTO> getAllSolicitudes();

    /**
     * Busca una solicitud específica por su identificador único.
     *
     * @param id Identificador de la solicitud.
     * @return DTO con la información detallada de la solicitud.
     * @throws com.empresa.soporte.exception.ResourceNotFoundException Si la solicitud no existe.
     */
    SolicitudResponseDTO getSolicitudById(Long id);

    /**
     * Registra una nueva solicitud de soporte técnico.
     * Realiza validaciones de existencia de clientes antes de la creación.
     *
     * @param dto DTO que contiene los datos para la nueva solicitud.
     * @return DTO resultante de la solicitud creada con su ID generado.
     */
    SolicitudResponseDTO createSolicitud(SolicitudCreateDTO dto);

    /**
     * Actualiza una solicitud existente aplicando reglas de negocio estrictas.
     *
     * @param id  Identificador de la solicitud a actualizar.
     * @param dto DTO con los datos actualizados de la solicitud.
     * @return DTO resultante de la solicitud modificada.
     */
    SolicitudResponseDTO updateSolicitud(Long id, SolicitudUpdateDTO dto);

    /**
     * Elimina una solicitud del sistema verificando su existencia previa.
     *
     * @param id Identificador de la solicitud a eliminar.
     */
    void deleteSolicitud(Long id);
}
