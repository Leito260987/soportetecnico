package com.empresa.soporte.model;

/**
 * Define los estados posibles en los que puede encontrarse una solicitud de soporte.
 *
 * @author Desarrollador Backend Senior & Arquitecto de Software
 * @version 1.0
 */
public enum EstadoSolicitud {
    /**
     * La solicitud ha sido registrada y está a la espera de revisión o asignación.
     */
    ABIERTA,

    /**
     * La solicitud ha sido asignada a un técnico y está siendo atendida.
     */
    EN_PROCESO,

    /**
     * El técnico ha propuesto una solución al problema reportado.
     */
    RESUELTA,

    /**
     * El cliente o el sistema han confirmado el cierre de la solicitud.
     */
    CERRADA
}
