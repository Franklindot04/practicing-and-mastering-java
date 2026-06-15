# Secured Spring Boot Task API

This project is an educational Spring Boot API that adds basic authentication and role-based authorization to a persistent task API.

It uses Spring Security, Spring Data JPA, H2, validation, and demo-only users. It is not production-ready.

## Concepts Practiced

- HTTP Basic authentication for a simple API demo
- BCrypt password encoding
- Public vs protected routes
- Role-based authorization
- Spring Security filter chain
- Spring Data JPA task persistence with H2
- DTO/entity separation
- Safe error responses

## Demo Users

These users are created in code for local learning only:

| Username | Password | Role |
| --- | --- | --- |
| `user@example.com` | `demo-user-password` | `USER` |
| `admin@example.com` | `demo-admin-password` | `ADMIN` |

Do not use these credentials in real systems.

## Run

```bash
mvn spring-boot:run
```

## Test

```bash
mvn test
```

## Example Requests

Public health route:

```bash
curl -i http://localhost:8080/api/health
```

Create a task as a user:

```bash
curl -i -u user@example.com:demo-user-password \
  -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring Security","description":"Start simple"}'
```

Read the demo admin route:

```bash
curl -i -u admin@example.com:demo-admin-password http://localhost:8080/api/admin/demo
```

## What Is Simplified

- HTTP Basic is used instead of JWT to keep the first secured project clear.
- Demo users are in memory instead of stored as real accounts.
- H2 is an in-memory learning database.
- CSRF is disabled for this stateless API demo.
- No Docker, deployment, production secret management, or refresh tokens are included.

## Possible Improvements

- Move users to the database.
- Add ownership checks so users can manage only their own tasks.
- Add controller tests for security rules.
- Add JWT later with a proven library and real secret management.
- Harden configuration before any production use.
