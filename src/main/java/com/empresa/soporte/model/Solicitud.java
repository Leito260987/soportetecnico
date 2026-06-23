package com.empresa.soporte.model;

import java.time.LocalDateTime;

/**
 * Representa una solicitud de soporte técnico en el sistema.
 * Es la entidad central de dominio y contiene relaciones con Cliente y Tecnico.
 * Aplica principios de encapsulamiento estricto y diseño limpio.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class Solicitud {

    private Long id;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private EstadoSolicitud estado;
    private PrioridadSolicitud prioridad;
    private Cliente cliente;
    private Tecnico tecnico;
    private String comentarios;

    /**
     * Constructor por defecto.
     * Inicializa la fecha de creación de forma automática.
     */
    public Solicitud() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoSolicitud.ABIERTA; // Estado por defecto al crear
    }

    /**
     * Constructor completo de la clase.
     *
     * @param id            Identificador de la solicitud
     * @param descripcion   Descripción detallada de la incidencia
     * @param fechaCreacion Fecha y hora en que se creó la solicitud
     * @param estado        Estado actual de la solicitud
     * @param prioridad     Prioridad asignada
     * @param cliente       Cliente asociado a la solicitud
     * @param tecnico       Técnico asignado (puede ser nulo)
     * @param comentarios   Comentarios de resolución o notas adicionales
     */
    public Solicitud(Long id, String descripcion, LocalDateTime fechaCreacion, EstadoSolicitud estado,
                     PrioridadSolicitud prioridad, Cliente cliente, Tecnico tecnico, String comentarios) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion != null ? fechaCreacion : LocalDateTime.now();
        this.estado = estado != null ? estado : EstadoSolicitud.ABIERTA;
        this.prioridad = prioridad;
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.comentarios = comentarios;
    }

    // Getters y Setters con encapsulamiento estricto

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", estado=" + estado +
                ", prioridad=" + prioridad +
                ", cliente=" + (cliente != null ? cliente.getId() : "null") +
                ", tecnico=" + (tecnico != null ? tecnico.getId() : "null") +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}
