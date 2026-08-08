package com.utm.mayte_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utm.mayte_api.model.Ticket;

/**
 * Repositorio de acceso a datos para la entidad Ticket.
 * Spring Data JPA genera automaticamente la implementacion en tiempo de
 * ejecucion (findAll, findById, save, deleteById, etc.), por lo que aqui
 * solo se declara el contrato.
 */

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
