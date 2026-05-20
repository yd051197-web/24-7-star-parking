-- ==========================================
-- BASE DE DATOS
-- PROYECTO 24/7 STAR PARKING
-- ==========================================

-- Crear base de datos

CREATE DATABASE starparking;

-- Seleccionar base datos

USE starparking;

-- ==========================================
-- TABLA USUARIOS
-- ==========================================

CREATE TABLE usuarios(

    id INT PRIMARY KEY AUTO_INCREMENT,

    nombre VARCHAR(100),

    correo VARCHAR(100),

    contraseña VARCHAR(100),

    rol VARCHAR(50)

);

-- ==========================================
-- TABLA VEHICULOS
-- ==========================================

CREATE TABLE vehiculos(

    id INT PRIMARY KEY AUTO_INCREMENT,

    placa VARCHAR(10),

    tipo VARCHAR(50),

    horaIngreso VARCHAR(50),

    horaSalida VARCHAR(50)

);

-- ==========================================
-- TABLA PAGOS
-- ==========================================

CREATE TABLE pagos(

    id INT PRIMARY KEY AUTO_INCREMENT,

    valor DOUBLE,

    fechaPago VARCHAR(50),

    vehiculoId INT,

    FOREIGN KEY(vehiculoId)
    REFERENCES vehiculos(id)

);

-- ==========================================
-- TABLA ESPACIOS
-- ==========================================

CREATE TABLE espacios(

    id INT PRIMARY KEY AUTO_INCREMENT,

    numeroEspacio INT,

    estado VARCHAR(50)

);

-- ==========================================
-- DATOS PRUEBA
-- ==========================================

INSERT INTO usuarios(nombre, correo, contraseña, rol)
VALUES(
'Administrador',
'admin@starparking.com',
'123456',
'ADMIN'
);

INSERT INTO vehiculos(placa, tipo, horaIngreso, horaSalida)
VALUES(
'ABC123',
'Automóvil',
'08:00 AM',
'10:00 AM'
);

INSERT INTO espacios(numeroEspacio, estado)
VALUES(
1,
'Disponible'
);