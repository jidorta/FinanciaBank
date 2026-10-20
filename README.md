# FinanciaBank API

API REST de ejemplo para la gestión de operaciones bancarias, desarrollada con **Java 17, Spring Boot, Spring Security, JWT y PostgreSQL**.

El proyecto está orientado a practicar el desarrollo backend con Java, persistencia de datos, autenticación basada en tokens y autorización mediante roles.

## Tecnologías

- Java 17
- Spring Boot 3.5.3
- Spring Web
- Spring Security
- JSON Web Tokens (JWT)
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Lombok
- Bean Validation

## Funcionalidades

- Registro e inicio de sesión de usuarios.
- Generación y validación de tokens JWT.
- Persistencia de datos con PostgreSQL y Spring Data JPA.
- Autorización basada en roles (`USER` y `ADMIN`).
- Gestión de cuentas bancarias y depósitos.
- Transferencias entre cuentas.
- Gestión de notificaciones.

## Endpoints principales

### Autenticación

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/api/auth/register` | Registrar un usuario y obtener un token JWT. |
| `POST` | `/api/auth/login` | Iniciar sesión y obtener un token JWT. |

### Cuentas bancarias

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/api/cuenta-bancaria` | Consultar las cuentas del usuario autenticado. |
| `POST` | `/api/cuenta-bancaria` | Crear una cuenta bancaria. |
| `POST` | `/api/cuenta-bancaria/deposito?cuentaId={id}&monto={monto}` | Realizar un depósito. |

### Transferencias

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/api/transferencias` | Consultar el historial de transferencias. |
| `POST` | `/api/transferencias` | Realizar una transferencia. |

### Notificaciones

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/notifications` | Consultar notificaciones. |
| `POST` | `/notifications` | Crear una notificación. |

## Seguridad

- Autenticación mediante JWT.
- Contraseñas almacenadas utilizando un codificador de contraseñas.
- Control de acceso mediante roles.
- Protección de endpoints con Spring Security.

## Requisitos previos

- JDK 17.
- Maven, o el Maven Wrapper si está incluido en el repositorio.
- PostgreSQL.

## Configuración y ejecución

1. Crear la base de datos PostgreSQL `bankingDB`.
2. Configurar las propiedades de conexión en `application.properties`.
3. Configurar la clave JWT mediante el mecanismo de configuración utilizado por el proyecto. No publicar claves reales ni credenciales personales en el repositorio.
4. Ejecutar la aplicación desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`, salvo que se configure otro puerto.

## Pruebas

Los endpoints pueden probarse con Postman enviando las solicitudes HTTP correspondientes. Para acceder a los endpoints protegidos, incluir el token JWT en la cabecera:

```http
Authorization: Bearer <token>
```

## Estado del proyecto

Proyecto personal de práctica de desarrollo backend con Java y Spring Boot. Las funcionalidades y los endpoints documentados deben contrastarse con la implementación actual del repositorio.