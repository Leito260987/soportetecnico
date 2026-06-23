package com.empresa.soporte.exception;

/**
 * Excepción personalizada que representa una petición incorrecta o violación de regla de negocio.
 * Se asocia típicamente con el estado HTTP 400 Bad Request.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class BadRequestException extends RuntimeException {

    /**
     * Constructor con mensaje de error personalizado.
     *
     * @param message Mensaje que describe la regla de negocio violada
     */
    public BadRequestException(String message) {
        super(message);
    }
}
