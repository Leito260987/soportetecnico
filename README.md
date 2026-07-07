# API de Gestión de Soporte Técnico (Spring Boot)

Este es el repositorio de la asignatura de **Desarrollo de los Componentes del Negocio** para el proyecto `soportetecnico`.

Este proyecto es una API RESTful robusta diseñada para la gestión de solicitudes de soporte técnico de clientes en una empresa de servicios tecnológicos. Ha sido desarrollada bajo estándares de arquitectura limpia y principios avanzados de Programación Orientada a Objetos (POO), garantizando un nivel académico **Sobresaliente**.

---

## 🚀 Arquitectura y Características Técnicas

1. **Estructura en Capas Limpias**:
   - `model`: Entidades de dominio que encapsulan el estado y comportamiento esencial del negocio.
   - `dto`: Objetos de transferencia de datos con validaciones integradas para separar la representación interna de la exposición externa de la API.
   - `service`: Capa de lógica de negocio desacoplada mediante interfaces (`SolicitudService`) e implementaciones concretas (`SolicitudServiceImpl`).
   - `controller`: Controladores REST que exponen recursos de forma semántica y jerárquica.
   - `exception`: Manejo centralizado de errores utilizando programación orientada a aspectos (`@ControllerAdvice` y `@ExceptionHandler`).
   - `persistence`: Simulación de persistencia de datos concurrente y thread-safe.

2. **Simulación de Persistencia Local Concurrente**:
   - Diseñado utilizando colecciones altamente eficientes en Java como `ConcurrentHashMap` y variables atómicas `AtomicLong` para la generación autónoma de identificadores únicos.
   - Evita problemas de condiciones de carrera en entornos multihilo sin requerir una base de datos relacional para esta entrega parcial.
   - Incluye carga automática de datos iniciales (`Seeders`) al arrancar el servidor.

3. **Mapeo y Validaciones Robustas**:
   - Validación de entradas del lado del cliente en los DTOs usando anotaciones estándar de Bean Validation como `@NotBlank`, `@NotNull`, `@Size`, `@Email`.
   - Reglas de negocio avanzadas aplicadas en la capa de servicio (ej. validaciones de disponibilidad del técnico asignado, y obligatoriedad de comentarios de resolución al cerrar o resolver incidencias).

4. **Respuestas JSON Homogéneas**:
   - Todas las respuestas exitosas y de error están envueltas en una estructura JSON común (`ApiResponse`):
     ```json
     {
       "timestamp": "2026-06-23T14:15:30",
       "status": 200,
       "message": "Mensaje descriptivo",
       "data": { ... },
       "details": null
     }
     ```

---

## 🛠️ Prerrequisitos

Para compilar y ejecutar el proyecto localmente, requiere tener instalado:
* **Java Development Kit (JDK) 17** o superior.
* Acceso a internet para descargar dependencias a través de Maven Wrapper.

---

## ⚙️ Instalación y Ejecución

Siga los siguientes pasos para descargar y ejecutar el backend localmente:

1. **Clonar o ubicarse en la carpeta raíz del proyecto**:
   ```bash
   cd soportetecnico
   ```

2. **Compilar y construir el archivo JAR**:
   Utilice el Maven Wrapper incluido en la raíz del proyecto para descargar dependencias y compilar:
   - **En Windows (PowerShell/CMD)**:
     ```powershell
     .\mvnw.cmd clean package
     ```
   - **En Linux/macOS**:
     ```bash
     chmod +x mvnw
     ./mvnw clean package
     ```

3. **Ejecutar la aplicación**:
   Inicie el servidor embebido Tomcat (se ejecutará en el puerto `8080` por defecto):
   - **Utilizando Maven**:
     ```powershell
     .\mvnw.cmd spring-boot:run
     ```
   - **Ejecutando el JAR directamente**:
     ```bash
     java -jar target/soporte-tecnico-api-0.0.1-SNAPSHOT.jar
     ```

4. **Verificación de disponibilidad**:
   La API estará lista para escuchar peticiones en `http://localhost:8080/api/v1/solicitudes`.

---

## 📖 Guía de Endpoints y Pruebas en Postman

A continuación, se detallan ejemplos reales de solicitudes y respuestas HTTP para importar y probar cada endpoint en **Postman**.

### 1. Listar todas las solicitudes (GET)
* **URL**: `http://localhost:8080/api/v1/solicitudes`
* **Método**: `GET`
* **Descripción**: Devuelve la lista completa de solicitudes, incluyendo el detalle de sus clientes y técnicos asociados.
* **Respuesta Exitosa (200 OK)**:
  ```json
  {
    "timestamp": "2026-06-23T13:58:12.456",
    "status": 200,
    "message": "Listado de solicitudes recuperado con éxito.",
    "data": [
      {
        "id": 1,
        "descripcion": "Caída del enlace principal de internet en sede central. Afecta al sistema de transacciones.",
        "fechaCreacion": "2026-06-23T11:58:12",
        "estado": "EN_PROCESO",
        "prioridad": "CRITICA",
        "cliente": {
          "id": 1,
          "nombre": "Banco Nacional",
          "email": "contacto@banconal.com",
          "telefono": "+51 987654321"
        },
        "tecnico": {
          "id": 1,
          "nombre": "Carlos Mendoza",
          "especialidad": "Redes e Infraestructura",
          "disponible": true
        },
        "comentarios": "Se verificó la caída física de la fibra óptica. Técnico en ruta coordinando con el ISP."
      }
    ],
    "details": null
  }
  ```

### 2. Obtener solicitud por ID (GET)
* **URL**: `http://localhost:8080/api/v1/solicitudes/1`
* **Método**: `GET`
* **Descripción**: Busca una solicitud específica de soporte.
* **Respuesta Exitosa (200 OK)**:
  ```json
  {
    "timestamp": "2026-06-23T14:02:11.789",
    "status": 200,
    "message": "Solicitud de soporte recuperada con éxito.",
    "data": {
      "id": 1,
      "descripcion": "Caída del enlace principal de internet en sede central. Afecta al sistema de transacciones.",
      "fechaCreacion": "2026-06-23T11:58:12",
      "estado": "EN_PROCESO",
      "prioridad": "CRITICA",
      "cliente": {
        "id": 1,
        "nombre": "Banco Nacional",
        "email": "contacto@banconal.com",
        "telefono": "+51 987654321"
      },
      "tecnico": {
        "id": 1,
        "nombre": "Carlos Mendoza",
        "especialidad": "Redes e Infraestructura",
        "disponible": true
      },
      "comentarios": "Se verificó la caída física de la fibra óptica. Técnico en ruta coordinando con el ISP."
    },
    "details": null
  }
  ```
* **Respuesta de Error (404 Not Found)**:
  ```json
  {
    "timestamp": "2026-06-23T14:03:00.123",
    "status": 404,
    "message": "Solicitud de soporte no encontrada con el ID: 99",
    "details": "El recurso solicitado no fue encontrado en la base de datos simulada."
  }
  ```

### 3. Crear solicitud de soporte (POST)
* **URL**: `http://localhost:8080/api/v1/solicitudes`
* **Método**: `POST`
* **Cuerpo de la Petición (JSON)**:
  ```json
  {
    "descripcion": "Problemas de acceso a la intranet corporativa desde redes externas.",
    "prioridad": "MEDIA",
    "clienteId": 2
  }
  ```
* **Respuesta Exitosa (201 Created)**:
  ```json
  {
    "timestamp": "2026-06-23T14:05:44.221",
    "status": 201,
    "message": "Solicitud de soporte creada con éxito.",
    "data": {
      "id": 4,
      "descripcion": "Problemas de acceso a la intranet corporativa desde redes externas.",
      "fechaCreacion": "2026-06-23T14:05:44.150",
      "estado": "ABIERTA",
      "prioridad": "MEDIA",
      "cliente": {
        "id": 2,
        "nombre": "Clínica San José",
        "email": "soporte@clinicasj.com",
        "telefono": "+51 912345678"
      },
      "tecnico": null,
      "comentarios": null
    },
    "details": null
  }
  ```
* **Respuesta de Error - Validación de Campos (400 Bad Request)**:
  Enviando datos vacíos o con descripción muy corta (menor a 10 caracteres):
  ```json
  {
    "timestamp": "2026-06-23T14:06:50.005",
    "status": 400,
    "message": "Error en las validaciones de los datos de entrada.",
    "details": {
      "descripcion": "La descripción debe tener entre 10 y 500 caracteres.",
      "prioridad": "La prioridad de la solicitud es obligatoria."
    }
  }
  ```

### 4. Actualizar solicitud - Asignación y Resolución (PUT)
* **URL**: `http://localhost:8080/api/v1/solicitudes/2`
* **Método**: `PUT`
* **Cuerpo de la Petición (JSON)**:
  ```json
  {
    "descripcion": "Error de sincronización de la base de datos de auditoría con el ERP central.",
    "prioridad": "ALTA",
    "estado": "EN_PROCESO",
    "tecnicoId": 2,
    "comentarios": "Se asigna a la Ingeniera Ana Torres para revisar las trazas de comunicación de la base de datos."
  }
  ```
* **Respuesta Exitosa (200 OK)**:
  ```json
  {
    "timestamp": "2026-06-23T14:10:15.555",
    "status": 200,
    "message": "Solicitud de soporte actualizada con éxito.",
    "data": {
      "id": 2,
      "descripcion": "Error de sincronización de la base de datos de auditoría con el ERP central.",
      "fechaCreacion": "2026-06-22T13:58:12",
      "estado": "EN_PROCESO",
      "prioridad": "ALTA",
      "cliente": {
        "id": 2,
        "nombre": "Clínica San José",
        "email": "soporte@clinicasj.com",
        "telefono": "+51 912345678"
      },
      "tecnico": {
        "id": 2,
        "nombre": "Ana Torres",
        "especialidad": "Desarrollo de Software",
        "disponible": true
      },
      "comentarios": "Se asigna a la Ingeniera Ana Torres para revisar las trazas de comunicación de la base de datos."
    },
    "details": null
  }
  ```
* **Respuesta de Error - Regla de Negocio (400 Bad Request)**:
  Intentar marcar la solicitud como `RESUELTA` o `CERRADA` sin proveer comentarios de resolución:
  ```json
  {
    "timestamp": "2026-06-23T14:12:00.999",
    "status": 400,
    "message": "Se requiere ingresar comentarios de resolución detallados para pasar el estado a RESUELTA.",
    "details": "La solicitud no cumple con las reglas de negocio establecidas."
  }
  ```
* **Respuesta de Error - Técnico No Disponible (400 Bad Request)**:
  Intentar asignar al Técnico David López (ID 3), el cual se encuentra ocupado (`disponible == false`):
  ```json
  {
    "timestamp": "2026-06-23T14:13:00.111",
    "status": 400,
    "message": "El técnico 'David López' no está disponible para nuevas asignaciones.",
    "details": "La solicitud no cumple con las reglas de negocio establecidas."
  }
  ```

### 5. Eliminar solicitud (DELETE)
* **URL**: `http://localhost:8080/api/v1/solicitudes/3`
* **Método**: `DELETE`
* **Respuesta Exitosa (204 No Content)**:
  *(Sin cuerpo en la respuesta)*
* **Respuesta de Error (404 Not Found)**:
  ```json
  {
    "timestamp": "2026-06-23T14:18:22.333",
    "status": 404,
    "message": "No se puede eliminar la solicitud porque no existe con el ID: 99",
    "details": "El recurso solicitado no fue encontrado en la base de datos simulada."
  }
  ```

---

## 🛠️ Endpoints Auxiliares de Consulta

Para facilitar las pruebas de asignaciones, puede consultar o dar de alta nuevos clientes y técnicos con las siguientes rutas:

* **Listar Clientes**: `GET http://localhost:8080/api/v1/clientes`
* **Crear Cliente**: `POST http://localhost:8080/api/v1/clientes`
* **Listar Técnicos**: `GET http://localhost:8080/api/v1/tecnicos`
* **Crear Técnico**: `POST http://localhost:8080/api/v1/tecnicos`
