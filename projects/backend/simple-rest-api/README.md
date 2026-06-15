# Simple Spring Boot REST API

This project is a beginner-friendly Spring Boot REST API skeleton for a task resource. It uses an in-memory repository so you can focus on controllers, DTOs, services, validation, and error responses before adding a real database.

## Concepts Practiced

- Spring Boot application entry point
- REST controller endpoints
- Request and response DTOs
- Service-layer business logic
- In-memory repository
- Basic validation
- Consistent error responses
- Service tests without starting a server

## Run

From this project folder:

```bash
mvn spring-boot:run
```

The API starts on `http://localhost:8080` by default.

## Endpoints

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/api/tasks` | List all tasks |
| `GET` | `/api/tasks/{id}` | Find one task |
| `POST` | `/api/tasks` | Create a task |
| `PUT` | `/api/tasks/{id}` | Replace a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |

## Example Requests

Create a task:

```bash
curl -i -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Read Spring Boot intro","description":"Focus on layers"}'
```

List tasks:

```bash
curl -i http://localhost:8080/api/tasks
```

Update a task:

```bash
curl -i -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Practice REST endpoints","description":"Use curl","completed":true}'
```

Delete a task:

```bash
curl -i -X DELETE http://localhost:8080/api/tasks/1
```

## Project Layers

- `SimpleRestApiApplication`: starts Spring Boot.
- `TaskController`: translates HTTP requests into service calls.
- `TaskService`: owns task rules and workflow.
- `InMemoryTaskRepository`: stores tasks in memory for practice.
- DTO records: define request and response shapes.
- `ApiExceptionHandler`: turns validation and not-found errors into responses.

## What To Improve Later

- Add a real database after repository basics are clear.
- Add pagination and filtering for list endpoints.
- Add more validation rules.
- Add controller tests.
- Add authentication only after the basic API flow feels comfortable.

Do not store secrets, tokens, or database passwords in source code.
