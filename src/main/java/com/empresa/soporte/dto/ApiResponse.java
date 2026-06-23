package com.empresa.soporte.dto;

import java.time.LocalDateTime;

/**
 * Envoltorio (Wrapper) genérico para todas las respuestas de la API.
 * Garantiza una estructura JSON homogénea y consistente tanto para respuestas
 * exitosas como para respuestas de error.
 *
 * @param <T> Tipo de datos contenido en la respuesta (payload)
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
    private Object details;

    /**
     * Constructor por defecto.
     * Inicializa automáticamente la fecha y hora de la respuesta.
     */
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor completo.
     *
     * @param status  Código de estado HTTP
     * @param message Mensaje descriptivo de la operación
     * @param data    Datos resultantes de la operación (puede ser nulo)
     * @param details Detalles adicionales (ej. lista de errores de validación, trazas, etc.)
     */
    public ApiResponse(int status, String message, T data, Object details) {
        this();
        this.status = status;
        this.message = message;
        this.data = data;
        this.details = details;
    }

    // Métodos estáticos de fábrica (Factory Methods) para facilitar el uso

    /**
     * Crea una respuesta de éxito con datos.
     */
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status, message, data, null);
    }

    /**
     * Crea una respuesta de éxito sin datos (ej. DELETE).
     */
    public static <T> ApiResponse<T> success(int status, String message) {
        return new ApiResponse<>(status, message, null, null);
    }

    /**
     * Crea una respuesta de error.
     */
    public static <T> ApiResponse<T> error(int status, String message, Object details) {
        return new ApiResponse<>(status, message, null, details);
    }

    // Getters y Setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
