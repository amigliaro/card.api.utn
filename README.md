# card.api.utn — Card API

Microservicio REST para la **gestión de tarjetas** (`com.card:api`), desarrollado como parte del trabajo práctico de sistemas distribuidos / microservicios de la UTN. Expone endpoints HTTP para administrar la información de tarjetas, persistiendo los datos en una base de datos relacional y documentando la API con OpenAPI/Swagger.

## Stack Tecnológico

- **Java** 21
- **Spring Boot** 4.0.6
- **Spring Cloud** 2025.1.1 (dependencias importadas vía BOM)
- **Maven** (incluye Maven Wrapper — no es necesario tener Maven instalado localmente)

### Dependencias principales

| Dependencia | Propósito |
|---|---|
| `spring-boot-starter-data-jpa` | Persistencia de datos con Spring Data JPA |
| `spring-boot-starter-webmvc` | Exposición de la API REST (Spring MVC) |
| `spring-boot-starter-webflux` | Soporte reactivo / WebClient |
| `spring-cloud-starter-openfeign` | Cliente HTTP declarativo para comunicarse con otros microservicios |
| `springdoc-openapi-starter-webmvc-ui` | Documentación interactiva de la API (Swagger UI) |
| `mysql-connector-j` *(runtime)* | Driver de conexión a MySQL |
| `lombok` | Reducción de código boilerplate (getters/setters, constructores, etc.) |
| `h2` *(test)* | Base de datos en memoria para tests |
| `spring-boot-starter-data-jpa-test`, `spring-boot-starter-webmvc-test`, `spring-boot-starter-test` *(test)* | Soporte para testing |

## Requisitos previos

- JDK 21+
- Una instancia de **MySQL** en ejecución (para el entorno productivo/desarrollo; los tests usan H2 en memoria)
- Opcional: los demás microservicios del sistema (Eureka Server, Config Server, etc.) si esta API depende de ellos mediante OpenFeign

## Cómo empezar

Cloná el repositorio:

```bash
git clone https://github.com/amigliaro/card.api.utn.git
cd card.api.utn
```

Compilá el proyecto usando el Maven Wrapper incluido:

```bash
./mvnw clean install
```

Ejecutá la aplicación:

```bash
./mvnw spring-boot:run
```

En Windows, usá `mvnw.cmd` en lugar de `./mvnw`.

## Configuración

La configuración de la aplicación se encuentra en `src/main/resources`. Una configuración típica incluye:

```properties
server.port=8081
spring.application.name=card.api
spring.datasource.url=jdbc:mysql://localhost:3306/utn
spring.datasource.username=root
spring.datasource.password=<tu_contraseña>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

dolar.api.url=https://dolarapi.com/v1
```

> ⚠️ Las credenciales de base de datos están actualmente hardcodeadas en `application.properties`. Se recomienda externalizarlas mediante variables de entorno o el Config Server (`config-repo.utn`) antes de subir el proyecto a un entorno compartido o productivo. Para los tests, la aplicación usa H2 en memoria en lugar de MySQL.

## Endpoints

Todos los endpoints de gestión de tarjetas están expuestos bajo el path base `/tarjetas`.

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/tarjetas` | Devuelve el listado completo de tarjetas registradas. |
| `GET` | `/tarjetas/{idTarjeta}` | Devuelve una tarjeta puntual según su ID. Responde `404 Not Found` si no existe. |
| `POST` | `/tarjetas` | Crea una nueva tarjeta a partir del cuerpo enviado en la request. |
| `PUT` | `/tarjetas/{idTarjeta}` | Actualiza los datos de una tarjeta existente (actualización parcial de los campos enviados). Responde `404 Not Found` si no existe. |
| `DELETE` | `/tarjetas/{idTarjeta}` | Elimina una tarjeta según su ID. Responde `404 Not Found` si no existe. |

### Modelo `Card`

El recurso `Card` (tabla `card`) contiene los siguientes campos:

| Campo | Tipo | Descripción |
|---|---|---|
| `cardId` | `Long` | Identificador único de la tarjeta (autogenerado). |
| `marca` | `String` | Marca de la tarjeta (ej. Visa, Mastercard). |
| `tipoTarjeta` | `String` | Tipo de tarjeta (ej. crédito, débito). |
| `nroTarjeta` | `String` | Número de tarjeta. |
| `fechaVencimiento` | `String` | Fecha de vencimiento de la tarjeta. |
| `CVC` | `String` | Código de seguridad de la tarjeta. |
| `limiteCredito` | `Double` | Límite de crédito asociado. |
| `activa` | `Boolean` | Indica si la tarjeta está activa. |
| `fechaCreacion` | `LocalDate` | Fecha de alta del registro. |
| `fechaModificacion` | `LocalDate` | Fecha de la última modificación. |

### Integración con servicio externo (cotización del dólar)

El proyecto incluye un cliente **OpenFeign** (`DolarClient`) que consume la API pública [DolarApi](https://dolarapi.com) para obtener la cotización oficial del dólar (`GET /dolares/oficial`), configurable mediante la propiedad `dolar.api.url`.

### Manejo de errores

La API centraliza el manejo de excepciones mediante un `@RestControllerAdvice` que devuelve respuestas consistentes:

| Excepción | Código HTTP | Cuándo ocurre |
|---|---|---|
| `NotFoundException` | `404 Not Found` | La tarjeta solicitada no existe. |
| `ExternalApiException` | `503 Service Unavailable` | Falla la comunicación con un servicio externo (ej. la API del dólar). |
| `Exception` (genérica) | `500 Internal Server Error` | Cualquier otro error no controlado. |

Todas las respuestas de error siguen el formato:

```json
{
  "codigo": 404,
  "mensaje": "No se encontró la tarjeta solicitada"
}
```

## Documentación de la API (Swagger)

Gracias a `springdoc-openapi`, una vez levantada la aplicación podés acceder a la documentación interactiva en:

```
http://localhost:8080/swagger-ui.html
```

y a la especificación OpenAPI en formato JSON en:

```
http://localhost:8080/v3/api-docs
```

## Ejecutar los tests

```bash
./mvnw test
```

## Estructura del proyecto

```
card.api.utn/
├── .mvn/wrapper/        # Archivos de Maven Wrapper
├── src/                 # Código fuente de la aplicación
├── mvnw / mvnw.cmd      # Scripts de Maven Wrapper
├── pom.xml              # Dependencias y configuración de build
└── README.md
```

## Proyectos relacionados

- [eureka.server.utn](https://github.com/amigliaro/eureka.server.utn) — Servidor de registro y descubrimiento (Eureka)
- [config-repo.utn](https://github.com/amigliaro/config-repo.utn) — Servidor de configuración centralizada (Config Server)

## Licencia

Todavía no se ha especificado una licencia para este proyecto.
