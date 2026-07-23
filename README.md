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

```yaml
server:
  port: 8080          # puerto de la API (ajustar según el entorno)

spring:
  application:
    name: card-api
  datasource:
    url: jdbc:mysql://localhost:3306/card_db
    username: <usuario>
    password: <contraseña>
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

> Reemplazá las credenciales y la URL de conexión según tu entorno. Para los tests, la aplicación puede configurarse para usar H2 en memoria en lugar de MySQL.

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
