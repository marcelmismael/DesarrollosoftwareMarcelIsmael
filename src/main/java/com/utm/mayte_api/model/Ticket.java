package com.utm.mayte_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity
@Table(name = "tickets")
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;


    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;


    @NotNull(message = "La categoria es obligatoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;


    @NotNull(message = "La prioridad es obligatoria")
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;


    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private Estado estado;


    private LocalDateTime fechaCreacion;



    // Constructor vacío requerido por JPA
    public Ticket() {

    }



    // Constructor completo

    public Ticket(String titulo,
                  String descripcion,
                  Categoria categoria,
                  Prioridad prioridad,
                  Estado estado) {


        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaCreacion = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }



    public Prioridad getPrioridad() {
        return prioridad;
    }


    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }



    public Estado getEstado() {
        return estado;
    }


    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }


    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}