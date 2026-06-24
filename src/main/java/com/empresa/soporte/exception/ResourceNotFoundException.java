package com.empresa.soporte.exception;

/**
 * Excepción personalizada que representa que un recurso específico no existe.
 * Se asocia típicamente con el estado HTTP 404 Not Found.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con mensaje de error personalizado.
     *
     * @param message Mensaje que describe el error
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
