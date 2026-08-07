package com.utm.mayte_api.service;

import com.utm.mayte_api.exception.TicketNotFoundException;
import com.utm.mayte_api.model.Ticket;
import com.utm.mayte_api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Contiene la logica de negocio del CRUD de tickets. El controller delega
 * aqui en lugar de hablar directamente con el repositorio; esto separa
 * "que expone la API" de "como se resuelve".
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> listarTodos() {
        return ticketRepository.findAll();
    }

    public Ticket buscarPorId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    public Ticket crear(@NonNull Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket actualizar(Long id, Ticket datosNuevos) {
        Ticket existente = buscarPorId(id);
        existente.setTitulo(datosNuevos.getTitulo());
        existente.setDescripcion(datosNuevos.getDescripcion());
        existente.setCategoria(datosNuevos.getCategoria());
        existente.setPrioridad(datosNuevos.getPrioridad());
        existente.setEstado(datosNuevos.getEstado());
        return ticketRepository.save(existente);
    }

    public void eliminar(Long id) {
        var existente = buscarPorId(id);
        ticketRepository.delete(existente);
    }
}
