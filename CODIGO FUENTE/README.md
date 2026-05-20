# 24/7 Star Parking

Sistema de gestión de parqueadero desarrollado como proyecto formativo para el programa ADSO - SENA (Sede Urrao, Antioquia).

## 🛠️ Tecnologías Utilizadas

- **Frontend:** HTML5, CSS3, JavaScript Moderno (ECMAScript 6)
- **Backend:** Java, Spring Boot (API REST)
- **Base de Datos:** MySQL
- **Control de Versiones:** Git y GitHub

## 🚀 Funcionalidades Principales

- **Módulo de Taquilla (Web):** Registro y control centralizado de celdas disponibles.
- **Módulo de Operario (Móvil):** Registro de entrada de vehículos desde patio en tiempo real.
- **Sincronización Asíncrona:** Comunicación inmediata entre ambas plataformas usando `LocalStorage` y eventos de escucha del navegador.
- **Liquidación Automatizada:** Cálculo exacto del tiempo de parqueo en minutos y cobro en pesos colombianos (COP).
- **Control de Recaudo:** Generación de tickets digitales con números de recibo aleatorios.

## 📂 Estructura del Proyecto

El repositorio está organizado bajo los estándares del SENA:
- `DOCUMENTACION/` -> Informes técnicos y capturas de pruebas de funcionamiento.
- `CODIGO_FUENTE/` -> Código fuente modularizado del Backend (Java) y Frontend (Web/Móvil).
- `DATABASE/` -> Script `.sql` para la migración y montaje de la base de datos.

## 💻 Instrucciones de Ejecución Local

1. Clona el repositorio o descarga el archivo ZIP.
2. Para ver los módulos Web y Móvil interactuando en tiempo real, abre de forma simultánea en tu navegador los archivos:
   - `CODIGO_FUENTE/CODIGO_FUENTE_WEB/Frontend/index.html` (Terminal de Taquilla)
   - `CODIGO_FUENTE/CODIGO_FUENTE_WEB/Frontend/app_movil.html` (App del Operario)

## 👤 Autor

**Yeison Adolfo Diaz Tapasco** Aprendiz SENA – ADSO