package com.empresa.soporte.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Objeto de Transferencia de Datos (DTO) para representar un Cliente.
 * Separa la entidad de dominio de la capa de presentación.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio.")
    @Size(min = 3, max = 80, message = "El nombre del cliente debe tener entre 3 y 80 caracteres.")
    private String nombre;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "El formato del correo electrónico es inválido.")
    private String email;

    @NotBlank(message = "El número de teléfono es obligatorio.")
    @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 caracteres.")
    private String telefono;

    /**
     * Constructor por defecto.
     */
    public ClienteDTO() {
    }

    /**
     * Constructor completo.
     */
    public ClienteDTO(Long id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
