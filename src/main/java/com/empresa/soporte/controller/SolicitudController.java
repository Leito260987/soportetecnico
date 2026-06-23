package com.empresa.soporte.controller;

import com.empresa.soporte.dto.ApiResponse;
import com.empresa.soporte.dto.SolicitudCreateDTO;
import com.empresa.soporte.dto.SolicitudResponseDTO;
import com.empresa.soporte.dto.SolicitudUpdateDTO;
import com.empresa.soporte.service.SolicitudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST que gestiona las operaciones HTTP CRUD para solicitudes de soporte técnico.
 * Expone endpoints semánticos bajo la ruta base '/api/v1/solicitudes'.
 * Utiliza validación de entrada mediante @Valid y respuestas estructuradas homogéneas.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    /**
     * Constructor para inyección del servicio de solicitudes.
     *
     * @param solicitudService Servicio de lógica de negocio para solicitudes
     */
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    /**
     * GET /api/v1/solicitudes
     * Obtiene el listado completo de solicitudes de soporte registradas.
     *
     * @return Respuesta estructurada conteniendo la lista de solicitudes.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<SolicitudResponseDTO>>> getAll() {
        List<SolicitudResponseDTO> solicitudes = solicitudService.getAllSolicitudes();
        ApiResponse<List<SolicitudResponseDTO>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Listado de solicitudes recuperado con éxito.",
                solicitudes
        );
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/v1/solicitudes/{id}
     * Obtiene una solicitud de soporte específica por su identificador único.
     *
     * @param id Identificador de la solicitud
     * @return Respuesta estructurada conteniendo el detalle de la solicitud encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SolicitudResponseDTO>> getById(@PathVariable Long id) {
        SolicitudResponseDTO solicitud = solicitudService.getSolicitudById(id);
        ApiResponse<SolicitudResponseDTO> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Solicitud de soporte recuperada con éxito.",
                solicitud
        );
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/v1/solicitudes
     * Crea una nueva solicitud de soporte técnico en el sistema.
     *
     * @param dto DTO con los datos validados de entrada para la creación
     * @return Respuesta estructurada con la información de la solicitud creada y HTTP Status 201.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<SolicitudResponseDTO>> create(@Valid @RequestBody SolicitudCreateDTO dto) {
        SolicitudResponseDTO creada = solicitudService.createSolicitud(dto);
        ApiResponse<SolicitudResponseDTO> response = ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Solicitud de soporte creada con éxito.",
                creada
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * PUT /api/v1/solicitudes/{id}
     * Modifica una solicitud de soporte existente en el sistema.
     * Aplica validaciones del DTO y lógica de negocio detallada.
     *
     * @param id  Identificador de la solicitud a modificar
     * @param dto DTO con los datos actualizados de la solicitud
     * @return Respuesta estructurada con la información actualizada y HTTP Status 200.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SolicitudResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody SolicitudUpdateDTO dto) {
        SolicitudResponseDTO actualizada = solicitudService.updateSolicitud(id, dto);
        ApiResponse<SolicitudResponseDTO> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Solicitud de soporte actualizada con éxito.",
                actualizada
        );
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/v1/solicitudes/{id}
     * Elimina una solicitud de soporte del sistema.
     * Si se elimina correctamente, responde con HTTP Status 204 No Content.
     *
     * @param id Identificador de la solicitud a eliminar
     * @return ResponseEntity con cabecera y estado 204 No Content, sin cuerpo de respuesta.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        solicitudService.deleteSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
