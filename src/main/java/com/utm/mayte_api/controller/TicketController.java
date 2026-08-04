package com.utm.mayte_api.controller;

import com.utm.mayte_api.model.Ticket;
import com.utm.mayte_api.service.TicketService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de Tickets.
 *
 * Endpoints disponibles:
 *
 * GET     /tickets          -> Obtener todos los tickets
 * GET     /tickets/{id}     -> Buscar ticket por ID
 * POST    /tickets          -> Crear ticket
 * PUT     /tickets/{id}     -> Actualizar ticket
 * DELETE  /tickets/{id}     -> Eliminar ticket
 *
 */

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;


    // Constructor con inyección de dependencia
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    // Listar todos los tickets
    @GetMapping
    public ResponseEntity<List<Ticket>> listarTodos() {

        List<Ticket> tickets = ticketService.listarTodos();

        return ResponseEntity.ok(tickets);
    }


    // Buscar ticket por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(
            @PathVariable Long id) {

        Ticket ticket = ticketService.buscarPorId(id);

        return ResponseEntity.ok(ticket);
    }


    // Crear nuevo ticket
    @PostMapping
    public ResponseEntity<Ticket> crear(
            @Valid @RequestBody Ticket ticket) {

        Ticket nuevoTicket = ticketService.crear(ticket);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoTicket);
    }


    // Actualizar ticket existente
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Ticket ticket) {


        Ticket ticketActualizado =
                ticketService.actualizar(id, ticket);


        return ResponseEntity.ok(ticketActualizado);
    }


    // Eliminar ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {


        ticketService.eliminar(id);


        return ResponseEntity
                .noContent()
                .build();
    }

}