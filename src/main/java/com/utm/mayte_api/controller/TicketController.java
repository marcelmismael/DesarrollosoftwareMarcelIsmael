package com.utm.mayte_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utm.mayte_api.model.Ticket;
import com.utm.mayte_api.service.TicketService;

import jakarta.validation.Valid;

/**
 * Controlador REST para la gestión de Tickets.
 *
 * Endpoints:
 *
 * GET     /tickets       -> Listar tickets
 * GET     /tickets/{id}  -> Buscar ticket por ID
 * POST    /tickets       -> Crear ticket
 * PUT     /tickets/{id}  -> Actualizar ticket
 * DELETE  /tickets/{id}  -> Eliminar ticket
 */

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    // Obtener todos los tickets
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


    // Crear ticket
    @PostMapping
    public ResponseEntity<Ticket> crear(
            @Valid @RequestBody Ticket ticket) {

        Ticket nuevoTicket = ticketService.crear(ticket);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoTicket);
    }


    // Actualizar ticket
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Ticket ticket) {

        Ticket actualizado = ticketService.actualizar(id, ticket);

        return ResponseEntity.ok(actualizado);
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