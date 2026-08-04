package com.utm.mayte_api;

import com.utm.mayte_api.model.Categoria;
import com.utm.mayte_api.model.Estado;
import com.utm.mayte_api.model.Prioridad;
import com.utm.mayte_api.model.Ticket;
import com.utm.mayte_api.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pruebas basicas exigidas por la actividad:
 * 1) que el contexto de Spring levante correctamente.
 * 2) que el repositorio pueda guardar y recuperar un Ticket (round trip
 *    contra la base H2 en memoria), validando el mapeo JPA.
 */
@SpringBootTest
class MayteApiApplicationTests {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void contextLoads() {
        // La aplicacion debe iniciar sin lanzar excepciones.
    }

    @Test
    void guardaYRecuperaUnTicket() {
        Ticket nuevo = new Ticket(
                "Ticket de prueba",
                "Descripcion de prueba generada por el test",
                Categoria.RED, Prioridad.MEDIA, Estado.ABIERTO);

        Ticket guardado = ticketRepository.save(nuevo);

        assertThat(guardado.getId()).isNotNull();
        assertThat(ticketRepository.findById(guardado.getId()))
                .isPresent()
                .get()
                .extracting(Ticket::getTitulo)
                .isEqualTo("Ticket de prueba");
    }
}
