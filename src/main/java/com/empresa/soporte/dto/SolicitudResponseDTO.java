package com.empresa.soporte.dto;

import com.empresa.soporte.model.EstadoSolicitud;
import com.empresa.soporte.model.PrioridadSolicitud;
import java.time.LocalDateTime;

/**
 * DTO de salida para estructurar las respuestas JSON de solicitudes de soporte.
 * Evita exponer directamente las entidades de dominio y previene acoplamientos.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class SolicitudResponseDTO {

    private Long id;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private EstadoSolicitud estado;
    private PrioridadSolicitud prioridad;
    private ClienteDTO cliente;
    private TecnicoDTO tecnico;
    private String comentarios;

    /**
     * Constructor por defecto.
     */
    public SolicitudResponseDTO() {
    }

    /**
     * Constructor completo.
     */
    public SolicitudResponseDTO(Long id, String descripcion, LocalDateTime fechaCreacion, EstadoSolicitud estado,
                                PrioridadSolicitud prioridad, ClienteDTO cliente, TecnicoDTO tecnico, String comentarios) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.comentarios = comentarios;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadSolicitud prioridad) {
        this.prioridad = prioridad;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public TecnicoDTO getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoDTO tecnico) {
        this.tecnico = tecnico;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
