package com.empresa.soporte.model;

/**
 * Representa a un técnico asignado para resolver solicitudes de soporte.
 * Aplica principios de encapsulamiento estricto y diseño cohesivo.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class Tecnico {

    private Long id;
    private String nombre;
    private String especialidad;
    private boolean disponible;

    /**
     * Constructor por defecto.
     */
    public Tecnico() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param id           Identificador único del técnico
     * @param nombre       Nombre completo del técnico
     * @param especialidad Especialidad técnica (ej. Redes, Software, Hardware)
     * @param disponible   Estado de disponibilidad para nuevas asignaciones
     */
    public Tecnico(Long id, String nombre, String especialidad, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    // Getters y Setters con encapsulamiento estricto

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
