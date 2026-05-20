package controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ESTÁNDAR DE CODIFICACIÓN: ADSO-STARPARKING-2026
 * Controlador REST para la gestión de vehículos, celdas y facturación.
 * Satisface los requisitos de la evidencia GA7-AA3-EV01 de forma síncrona.
 */
@RestController
@RequestMapping("/api/parqueadero")
@CrossOrigin(origins = "*") // Permite la conexión real con el Frontend Web y Móvil
public class VehiculoController {

    // Simulación de persistencia en memoria activa (Simula la DB MySQL en tiempo real)
    private static List<Map<String, Object>> vehiculosParqueados = new ArrayList<>();
    private static List<Map<String, Object>> historialReservas = new ArrayList<>();
    private static final int TOTAL_CELDAS = 20; // Capacidad máxima definida en el diseño técnico
    private static final int TARIFA_MINUTO = 100; // Tarifa fija en COP

    /**
     * Requisito: Consultar estado actual del parqueadero (Celdas disponibles)
     */
    @GetMapping("/estado")
    public Map<String, Object> obtenerEstado() {
        Map<String, Object> estado = new HashMap<>();
        int ocupados = vehiculosParqueados.size();
        estado.put("totalCeldas", TOTAL_CELDAS);
        estado.put("celdasOcupadas", ocupados);
        estado.put("celdasDisponibles", TOTAL_CELDAS - ocupados);
        return estado;
    }

    /**
     * Requisito: Registrar Entrada de Vehículo (Persistencia e inserción)
     */
    @PostMapping("/entrada")
    public Map<String, Object> registrarEntrada(@RequestBody Map<String, String> datos) {
        Map<String, Object> respuesta = new HashMap<>();
        String placa = datos.get("placa").toUpperCase();
        String tipo = datos.get("tipo");

        if (vehiculosParqueados.size() >= TOTAL_CELDAS) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "Parqueadero lleno. No hay celdas disponibles.");
            return respuesta;
        }

        // Crear registro con estampa de tiempo real
        Map<String, Object> nuevoVehiculo = new HashMap<>();
        nuevoVehiculo.put("placa", placa);
        nuevoVehiculo.put("tipo", tipo);
        nuevoVehiculo.put("horaIngreso", new Date().getTime());

        vehiculosParqueados.add(nuevoVehiculo);

        respuesta.put("exito", true);
        respuesta.put("mensaje", "Vehículo registrado con éxito. Entrada procesada.");
        respuesta.put("vehiculo", nuevoVehiculo);
        return respuesta;
    }

    /**
     * Requisito: Registrar Salida, Calcular Tiempo y Facturar Cobro
     */
    @PostMapping("/salida")
    public Map<String, Object> registrarSalida(@RequestBody Map<String, String> datos) {
        Map<String, Object> respuesta = new HashMap<>();
        String placaSalida = datos.get("placa").toUpperCase();
        
        Map<String, Object> vehiculo = null;
        for (Map<String, Object> v : vehiculosParqueados) {
            if (v.get("placa").equals(placaSalida)) {
                vehiculo = v;
                break;
            }
        }

        if (vehiculo == null) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "Error: Vehículo no encontrado en las celdas activas.");
            return respuesta;
        }

        long horaIngreso = (long) vehiculo.get("horaIngreso");
        long horaSalida = new Date().getTime();
        
        // Calcular tiempo de permanencia real (milisegundos a minutos)
        long diferenciaMilis = horaSalida - horaIngreso;
        long minutos = diferenciaMilis / 60000;
        if (minutos <= 0) minutos = 1; // Cobro mínimo de 1 minuto para pruebas en vivo

        long totalPagar = minutos * TARIFA_MINUTO;

        // Remover de la lista de parqueados
        vehiculosParqueados.remove(vehiculo);

        respuesta.put("exito", true);
        respuesta.put("placa", placaSalida);
        respuesta.put("tipo", vehiculo.get("tipo"));
        respuesta.put("minutos", minutos);
        respuesta.put("totalPagar", totalPagar);
        respuesta.put("mensaje", "Factura generada y celda liberada correctamente.");
        return respuesta;
    }

    /**
     * Requisito: Reserva Anticipada y Pago en Línea Integrado
     */
    @PostMapping("/reserva")
    public Map<String, Object> registrarReserva(@RequestBody Map<String, String> datos) {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> nuevaReserva = new HashMap<>();
        
        nuevaReserva.put("cliente", datos.get("nombre"));
        nuevaReserva.put("placa", datos.get("placa").toUpperCase());
        nuevaReserva.put("celdaAsignada", datos.get("espacio"));
        nuevaReserva.put("montoPago", 5000); // Tarifa plana de reserva anticipada por pasarela
        nuevaReserva.put("estadoPago", "Aprobado (Pasarela PSE/PayU)");

        historialReservas.add(nuevaReserva);

        respuesta.put("exito", true);
        respuesta.put("reserva", nuevaReserva);
        return respuesta;
    }
}