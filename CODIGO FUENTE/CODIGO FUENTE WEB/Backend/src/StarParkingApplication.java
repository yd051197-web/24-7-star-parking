package src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * ESTÁNDAR DE CODIFICACIÓN: ADSO-STARPARKING-CORE
 * Clase principal de arranque para el ecosistema 24/7 Star Parking.
 * Inicializa el servidor Tomcat embebido y el escaneo de servicios API REST.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"controller"}) // Le dice a Spring que busque tus controladores
public class StarParkingApplication {

    public static void main(String[] args) {
        // Ejecución del núcleo del framework Spring Boot
        SpringApplication.run(StarParkingApplication.class, args);
    }
}