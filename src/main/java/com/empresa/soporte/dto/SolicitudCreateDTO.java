package com.empresa.soporte.dto;

import com.empresa.soporte.model.PrioridadSolicitud;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada utilizado para la creación de solicitudes de soporte.
 * Implementa validaciones estrictas en los campos.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class SolicitudCreateDTO {

    @NotBlank(message = "La descripción de la incidencia es obligatoria.")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres.")
    private String descripcion;

    @NotNull(message = "La prioridad de la solicitud es obligatoria.")
    private PrioridadSolicitud prioridad;

    @NotNull(message = "El ID del cliente es obligatorio.")
    private Long clienteId;

    /**
     * Constructor por defecto.
     */
    public SolicitudCreateDTO() {
    }

    /**
     * Constructor completo.
     */
    public SolicitudCreateDTO(String descripcion, PrioridadSolicitud prioridad, Long clienteId) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.clienteId = clienteId;
    }

    // Getters y Setters

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadSolicitud prioridad) {
        this.prioridad = prioridad;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
