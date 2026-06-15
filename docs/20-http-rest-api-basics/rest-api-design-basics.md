# REST API Design Basics

REST APIs are easier to use when they are predictable. A client should be able to guess common endpoints after learning one resource.

## Use Resource Names

Prefer nouns:

```text
GET /tasks
POST /tasks
GET /tasks/42
PUT /tasks/42
DELETE /tasks/42
```

Avoid action-heavy paths:

```text
POST /createTask
GET /getTaskById
POST /deleteTask
```

The HTTP method already says what kind of action is happening.

## Use DTOs At The Boundary

A DTO is a data transfer object. It describes what the API accepts or returns.

Use DTOs so the public API does not accidentally expose internal model details.

Example DTOs:

- `CreateTaskRequest`
- `UpdateTaskRequest`
- `TaskResponse`
- `ErrorResponse`

## Keep Business Rules In Services

Controllers should translate HTTP requests into method calls. Services should make decisions.

Good service responsibilities:

- Validate whether a title is usable.
- Decide whether a missing id is an error.
- Apply default values.
- Coordinate repository calls.

## Make Errors Consistent

Plan common errors before writing many endpoints:

- Invalid input: `400 Bad Request`
- Missing resource: `404 Not Found`
- Duplicate value: `409 Conflict`
- Unexpected failure: `500 Internal Server Error`

## Design Checklist

- [ ] Does each endpoint manage a clear resource?
- [ ] Does the method match the operation?
- [ ] Does the success status match the outcome?
- [ ] Are request and response DTOs separate from internal models?
- [ ] Are validation errors easy to understand?
- [ ] Is service logic testable without HTTP?

Before moving on, you should be able to sketch endpoints for one resource and explain where controller, DTO, service, and repository code belongs.
