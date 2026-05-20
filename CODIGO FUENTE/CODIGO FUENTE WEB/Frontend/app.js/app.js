// Base de datos temporal del sistema
let vehiculos = [];
let reservas = [];

// ==========================================
// 1. GESTIÓN DE ENTRADAS EN VIVO
// ==========================================
function registrarVehiculo(){
    let placa = document.getElementById("placa").value.trim().toUpperCase();
    let tipo = document.getElementById("tipo").value;
    let horaIngreso = new Date();

    if(!placa){
        alert("⚠️ Por favor ingrese una placa válida.");
        return;
    }

    let vehiculo = {
        placa: placa,
        tipo: tipo,
        horaIngreso: horaIngreso
    };

    vehiculos.push(vehiculo);
    
    document.getElementById("resultado").innerHTML += `
        <p style="color: green;">🔹 <strong>[ENTRADA]</strong> Vehículo autorizado: <strong>${placa}</strong> (${tipo}) - Ingreso: ${horaIngreso.toLocaleTimeString()}</p>
    `;
    document.getElementById("placa").value = "";
}

// ==========================================
// 2. GESTIÓN DE SALIDAS Y CÁLCULO DE PAGOS
// ==========================================
function registrarSalida(){
    let placaSalida = document.getElementById("placaSalida").value.trim().toUpperCase();
    let vehiculoEncontrado = vehiculos.find(v => v.placa === placaSalida);

    if(vehiculoEncontrado){
        let horaSalida = new Date();
        // Simulación: calculamos minutos transcurridos. Si da 0, forzamos 1 minuto para ver el dinero.
        let tiempo = Math.ceil((horaSalida - vehiculoEncontrado.horaIngreso) / 60000);
        if(tiempo <= 0) tiempo = 1; 
        
        let tarifa = 100; // $100 pesos por minuto
        let totalPagar = tiempo * tarifa;

        document.getElementById("resultado").innerHTML += `
            <div class="ticket">
                <h3>🧾 TICKET DE SALIDA - STAR PARKING</h3>
                <p><strong>Vehículo:</strong> ${vehiculoEncontrado.placa} (${vehiculoEncontrado.tipo})</p>
                <p><strong>Tiempo ocupado:</strong> ${tiempo} minuto(s)</p>
                <p><strong>Tarifa por minuto:</strong> $${tarifa}</p>
                <p style="font-size: 18px; color: #d9534f;"><strong>TOTAL PAGADO:</strong> $${totalPagar} COP</p>
                <p style="font-size: 12px; color: gray;">Transacción procesada correctamente exitosa.</p>
            </div>
        `;
        // Sacar del arreglo de parqueados
        vehiculos = vehiculos.filter(v => v.placa !== placaSalida);
        document.getElementById("placaSalida").value = "";
    } else {
        alert("❌ El vehículo con esa placa no se encuentra en el parqueadero.");
    }
}

// ==========================================
// 3. NUEVA FUNCIÓN: RESERVA ANTICIPADA Y PAGO EN LÍNEA SIMULADO
// ==========================================
function crearReserva(){
    let nombre = document.getElementById("nombreReserva").value.trim();
    let placa = document.getElementById("placaReserva").value.trim().toUpperCase();
    let espacio = document.getElementById("espacioReserva").value;

    if(!nombre || !placa){
        alert("⚠️ Complete el nombre y la placa para realizar la reserva anticipada.");
        return;
    }

    // Simulación de Pago Exitoso en línea (API de pasarela como PayU / MercadoPago)
    let valorReserva = 5000; // Tarifa fija por apartar cupo

    let nuevaReserva = {
        nombre: nombre,
        placa: placa,
        espacio: espacio,
        pagoEstatus: "Aprobado",
        monto: valorReserva
    };

    reservas.push(nuevaReserva);

    document.getElementById("resultado").innerHTML += `
        <div class="ticket" style="border-color: #ffc107; background-color: #fffdf5;">
            <h3>💳 RESERVA CONFIRMADA Y PAGADA EN LÍNEA</h3>
            <p><strong>Cliente:</strong> ${nombre}</p>
            <p><strong>Placa Reservada:</strong> ${placa}</p>
            <p><strong>Espacio Asignado:</strong> Celda ${espacio}</p>
            <p><strong>Pasarela de Pago:</strong> Tarjeta de Crédito / PSE</p>
            <p style="color: #28a745;"><strong>Estado Pago:</strong> Transacción Autenticada ($${valorReserva} COP)</p>
        </div>
    `;

    // Limpiar campos
    document.getElementById("nombreReserva").value = "";
    document.getElementById("placaReserva").value = "";
}