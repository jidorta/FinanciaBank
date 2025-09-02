# FinanciaBank API

Proyecto de ejemplo de API bancaria construido con **Java 20**, **Spring Boot**, **Spring Security** y **JWT**. Permite gestionar usuarios, cuentas bancarias, transferencias y notificaciones.

---

## Tecnologías

- Java 20
- Spring Boot
- Spring Security con JWT
- PostgreSQL
- Maven
- Lombok

---

## Funcionalidades

- Registro y login de usuarios con JWT.
- Gestión de cuentas bancarias:
  - Crear cuentas
  - Consultar cuentas propias
  - Realizar depósitos
- Transferencias entre cuentas
- Gestión de notificaciones
- Control de acceso basado en roles (`USER` y `ADMIN`)

---

## Endpoints principales

### Autenticación

| Método | Endpoint | Descripción |
|--------|----------|------------|
| POST | `/api/auth/register` | Registrar usuario y obtener token JWT |
| POST | `/api/auth/login` | Login y obtener token JWT |

### Cuentas bancarias

| Método | Endpoint | Descripción |
|--------|----------|------------|
| GET | `/api/cuenta-bancaria` | Obtener cuentas del usuario autenticado |
| POST | `/api/cuenta-bancaria` | Crear nueva cuenta para el usuario autenticado |
| POST | `/api/cuenta-bancaria/deposito?cuentaId={id}&monto={monto}` | Realizar un depósito en la cuenta indicada |

### Transferencias

| Método | Endpoint | Descripción |
|--------|----------|------------|
| GET | `/api/transferencias` | Historial de transferencias del usuario autenticado |
| POST | `/api/transferencias` | Realizar transferencia entre cuentas |

### Notificaciones

| Método | Endpoint | Descripción |
|--------|----------|------------|
| GET | `/notifications` | Listar todas las notificaciones |
| POST | `/notifications` | Crear notificación |

---

## Seguridad

- JWT para autenticación
- Roles: `USER` y `ADMIN`
- Endpoints protegidos con Spring Security

---

## Configuración

1. Configurar PostgreSQL y crear la base de datos.
2. Editar `application.properties` con la URL, usuario y contraseña de PostgreSQL y la clave JWT.
3. Ejecutar la aplicación:

```bash
mvn spring-boot:run