package com.empresa.soporte.dto;

import com.empresa.soporte.model.EstadoSolicitud;
import com.empresa.soporte.model.PrioridadSolicitud;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada utilizado para la actualización de solicitudes de soporte.
 * Contiene campos editables de la solicitud incluyendo asignación y comentarios.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class SolicitudUpdateDTO {

    @NotBlank(message = "La descripción de la incidencia es obligatoria.")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres.")
    private String descripcion;

    @NotNull(message = "La prioridad de la solicitud es obligatoria.")
    private PrioridadSolicitud prioridad;

    @NotNull(message = "El estado de la solicitud es obligatorio.")
    private EstadoSolicitud estado;

    private Long tecnicoId; // Puede ser nulo para desasignar o si aún no se asigna

    @Size(max = 1000, message = "Los comentarios no pueden exceder los 1000 caracteres.")
    private String comentarios;

    /**
     * Constructor por defecto.
     */
    public SolicitudUpdateDTO() {
    }

    /**
     * Constructor completo.
     */
    public SolicitudUpdateDTO(String descripcion, PrioridadSolicitud prioridad, EstadoSolicitud estado, Long tecnicoId, String comentarios) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.tecnicoId = tecnicoId;
        this.comentarios = comentarios;
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

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
