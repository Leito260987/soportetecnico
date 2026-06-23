package com.empresa.soporte.model;

/**
 * Representa a un cliente que solicita soporte técnico en el sistema.
 * Aplica principios de encapsulamiento estricto y diseño cohesivo.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class Cliente {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;

    /**
     * Constructor por defecto.
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param id       Identificador único del cliente
     * @param nombre   Nombre completo del cliente
     * @param email    Correo electrónico de contacto
     * @param telefono Teléfono de contacto
     */
    public Cliente(Long id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
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

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
