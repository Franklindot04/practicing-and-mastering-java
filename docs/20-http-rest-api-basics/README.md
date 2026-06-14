# HTTP REST API Basics

HTTP is the request-response protocol most backend APIs use. REST is a style for designing APIs around resources and predictable operations.

This section prepares you to read and design simple Java backend APIs before adding a framework.

## Study Order

1. [HTTP Methods And Status Codes](http-methods-status-codes.md)
2. [Request And Response Anatomy](request-response-anatomy.md)
3. [REST API Design Basics](rest-api-design-basics.md)

## Key Vocabulary

- Resource: something the API manages, such as tasks, books, users, or products.
- Endpoint: a URL plus an HTTP method.
- Request body: data sent by the client.
- Response body: data returned by the server.
- Header: metadata about the request or response.
- Query parameter: optional filtering or sorting information in the URL.
- Path variable: an identifier embedded in the URL path.
- JSON: a common text format for API data.

## Example Resource

A task API might expose these endpoints:

| Action | Method | Path |
| --- | --- | --- |
| List tasks | `GET` | `/tasks` |
| Find one task | `GET` | `/tasks/{id}` |
| Create task | `POST` | `/tasks` |
| Replace task | `PUT` | `/tasks/{id}` |
| Delete task | `DELETE` | `/tasks/{id}` |

These shapes map well to a Spring Boot controller later, but the ideas are framework-independent.

## Common Beginner Mistakes

- Using `GET` for actions that change data.
- Returning `200 OK` for every outcome.
- Mixing internal model fields into public response bodies.
- Duplicating the same validation rules in every controller method.
- Making endpoint names verbs instead of resources, such as `/createTask`.

Before moving on, you should be able to choose an HTTP method and status code for a simple resource operation.
