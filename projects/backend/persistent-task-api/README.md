# Persistent Spring Boot Task API

This project builds on the simple in-memory REST API and adds persistence with Spring Data JPA and an H2 in-memory database.

It does not require an external database server and does not use real credentials.

## Concepts Practiced

- Spring Boot REST controllers
- DTO/entity separation
- Bean validation
- Spring Data JPA repositories
- H2 in-memory database
- Service-layer transaction boundaries
- Not-found and validation error responses
- Repository-backed service tests

## How It Differs From The In-Memory API

The earlier simple REST API stored tasks in a `Map`. This project stores tasks through a JPA entity and `TaskRepository`. H2 keeps the database in memory while the app runs, so every restart begins with a clean local database.

## Run

From this project folder:

```bash
mvn spring-boot:run
```

## Test

```bash
mvn test
```

## Endpoints

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/api/tasks` | List all tasks |
| `GET` | `/api/tasks/{id}` | Find one task |
| `POST` | `/api/tasks` | Create a task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |

## Example Requests

```bash
curl -i -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring Data JPA","description":"Start with H2"}'
```

```bash
curl -i http://localhost:8080/api/tasks
```

```bash
curl -i -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Finish persistence project","description":"Add tests","completed":true}'
```

```bash
curl -i -X DELETE http://localhost:8080/api/tasks/1
```

## H2 Explanation

H2 is a lightweight database that can run in memory. It is useful for learning and tests because no external database setup is required.

This project uses safe local settings only. Do not copy production credentials into source code.

## Layers

- `TaskController`: HTTP endpoints.
- `CreateTaskRequest` and `UpdateTaskRequest`: request DTOs.
- `TaskResponse`: response DTO.
- `TaskEntity`: persisted JPA entity.
- `TaskRepository`: Spring Data JPA repository.
- `TaskService`: validation workflow, transactions, entity/DTO mapping.
- `ApiExceptionHandler`: consistent error responses.

## Possible Improvements

- Add pagination and filtering.
- Add created/updated timestamps.
- Add controller tests.
- Add real database configuration later through safe environment-specific settings.
- Add authentication only after persistence basics are comfortable.
