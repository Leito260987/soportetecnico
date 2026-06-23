package com.empresa.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Objeto de Transferencia de Datos (DTO) para representar un Técnico.
 * Separa la entidad de dominio de la capa de presentación.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class TecnicoDTO {

    private Long id;

    @NotBlank(message = "El nombre del técnico es obligatorio.")
    @Size(min = 3, max = 80, message = "El nombre del técnico debe tener entre 3 y 80 caracteres.")
    private String nombre;

    @NotBlank(message = "La especialidad del técnico es obligatoria.")
    @Size(min = 3, max = 50, message = "La especialidad debe tener entre 3 y 50 caracteres.")
    private String especialidad;

    private boolean disponible;

    /**
     * Constructor por defecto.
     */
    public TecnicoDTO() {
    }

    /**
     * Constructor completo.
     */
    public TecnicoDTO(Long id, String nombre, String especialidad, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    // Getters y Setters

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
}
