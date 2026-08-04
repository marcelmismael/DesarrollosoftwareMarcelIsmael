package com.utm.mayte_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de arranque de la aplicación Spring Boot.
 * Debe ubicarse en la raíz del paquete com.utm.mayte_api
 * para que Spring pueda escanear automáticamente los subpaquetes:
 * controller, service, repository, model, config y exception.
 */
@SpringBootApplication
public class MayteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MayteApiApplication.class, args);
    }
}
