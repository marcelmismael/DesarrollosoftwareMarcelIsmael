package com.utm.mayte_api.exception;

/**
 * Se lanza cuando se solicita (GET, PUT o DELETE) un ticket cuyo id
 * no existe en la base de datos.
 */
public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(Long id) {
        super("No se encontro el ticket con id: " + id);
    }
}
