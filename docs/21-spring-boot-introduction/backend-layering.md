# Backend Layering

Layering keeps a backend easier to change and test. Each layer has a job.

## Controller Layer

The controller handles HTTP.

Typical responsibilities:

- Map routes to Java methods.
- Read path variables, query parameters, and request bodies.
- Call a service.
- Return response DTOs and status codes.

Avoid putting business rules here when they can live in a service.

## DTO Layer

DTOs describe API input and output.

Examples:

- `CreateTaskRequest`
- `UpdateTaskRequest`
- `TaskResponse`
- `ErrorResponse`

DTOs protect clients from internal model changes.

## Service Layer

The service contains business rules.

Typical responsibilities:

- Validate workflow decisions.
- Apply defaults.
- Coordinate repository calls.
- Decide what happens when data is missing.

Services should be testable without starting a web server.

## Repository Layer

The repository hides storage details.

In early projects, this might be an in-memory map. Later, it might use Spring Data JPA and a real database.

The service should not need to know how data is stored.

## Simple Flow

```text
HTTP request
  -> Controller
  -> Service
  -> Repository
  -> Service
  -> Controller
  -> HTTP response
```

## Design Checklist

- [ ] Can the controller be read quickly?
- [ ] Can the service be tested without HTTP?
- [ ] Does the repository hide storage details?
- [ ] Are request and response DTOs separate from internal models?
- [ ] Are error responses consistent?
