package com.utm.mayte_api.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.utm.mayte_api.model.Categoria;
import com.utm.mayte_api.model.Estado;
import com.utm.mayte_api.model.Prioridad;
import com.utm.mayte_api.model.Ticket;
import com.utm.mayte_api.repository.TicketRepository;

@Component
public class DatosIniciales implements CommandLineRunner {

    private final TicketRepository repository;

    public DatosIniciales(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        repository.save(new Ticket(
                "Falla de red",
                "Sin conexión al servidor",
                Categoria.RED,
                Prioridad.ALTA,
                Estado.ABIERTO
        ));

        repository.save(new Ticket(
                "Error de software",
                "La aplicación no inicia",
                Categoria.SOFTWARE,
                Prioridad.MEDIA,
                Estado.ABIERTO
        ));

        System.out.println("Datos iniciales cargados correctamente.");
    }
}