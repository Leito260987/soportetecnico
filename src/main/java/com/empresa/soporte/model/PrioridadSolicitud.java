package com.empresa.soporte.model;

/**
 * Define los niveles de prioridad que puede tener una solicitud de soporte técnico.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public enum PrioridadSolicitud {
    /**
     * Incidencias que no afectan el flujo normal de operaciones (ej. dudas conceptuales).
     */
    BAJA,

    /**
     * Incidencias que afectan parcialmente una funcionalidad no crítica.
     */
    MEDIA,

    /**
     * Incidencias con impacto en funcionalidades principales pero con alternativas de solución temporales.
     */
    ALTA,

    /**
     * Incidencias críticas que detienen por completo las operaciones del cliente.
     */
    CRITICA
}
