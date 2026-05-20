package CODIGO_FUENTE.Backend.service;

import org.springframework.stereotype.Service;

// Servicio encargado de la lógica de negocio con Spring Boot
@Service
public class VehiculoService {

    public void validarVehiculo(){
        System.out.println("Vehículo validado en Spring Boot");
    }

    public void calcularTiempo(){
        System.out.println("Tiempo calculado");
    }
}