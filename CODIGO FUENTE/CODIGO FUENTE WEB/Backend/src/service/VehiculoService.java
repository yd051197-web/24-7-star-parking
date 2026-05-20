package service;

import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    public void validarVehiculo(){
        System.out.println("Vehículo validado en Spring Boot");
    }

    public void calcularTiempo(){
        System.out.println("Tiempo calculado");
    }
}