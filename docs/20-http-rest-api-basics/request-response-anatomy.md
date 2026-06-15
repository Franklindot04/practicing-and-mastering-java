# Request And Response Anatomy

An HTTP API conversation has two parts: the request from the client and the response from the server.

## Request Shape

```text
POST /tasks?notify=true HTTP/1.1
Content-Type: application/json
Accept: application/json

{
  "title": "Review Java notes",
  "priority": "HIGH"
}
```

Important pieces:

- Method: `POST`
- Path: `/tasks`
- Query parameter: `notify=true`
- Headers: `Content-Type` and `Accept`
- Body: JSON data for the new task

## Response Shape

```text
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 42,
  "title": "Review Java notes",
  "priority": "HIGH",
  "completed": false
}
```

Important pieces:

- Status code: `201 Created`
- Headers: response metadata
- Body: JSON data returned to the client

## Path Variables

Path variables identify a specific resource.

```text
GET /tasks/42
DELETE /tasks/42
```

In Spring Boot, these later become controller parameters such as `@PathVariable long id`.

## Query Parameters

Query parameters usually filter, search, sort, or paginate.

```text
GET /tasks?completed=false&sort=createdAt
```

In Spring Boot, these later become controller parameters such as `@RequestParam boolean completed`.

## JSON Bodies

Use request bodies for data that creates or updates a resource.

Good request body:

```json
{
  "title": "Read Spring Boot intro",
  "priority": "MEDIUM"
}
```

Avoid exposing internal fields such as database IDs clients should not choose, passwords, stack traces, or implementation-only flags.

## Error Response Shape

A consistent error response helps clients understand what failed.

```json
{
  "status": 400,
  "message": "Title is required",
  "path": "/tasks"
}
```

Before moving on, you should be able to point to the method, path, headers, parameters, body, and status in a sample API exchange.
