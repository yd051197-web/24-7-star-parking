package CODIGO_FUENTE.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import CODIGO_FUENTE.Backend.service.VehiculoService;

// Version backend vehículos con Spring Boot
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/validar")
    public void registrarVehiculo() {
        vehiculoService.validarVehiculo();
    }
}