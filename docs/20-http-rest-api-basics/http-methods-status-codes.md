# HTTP Methods And Status Codes

HTTP methods describe the kind of operation a client wants to perform. Status codes describe the result.

## Common HTTP Methods

| Method | Use It For | Safe? | Idempotent? |
| --- | --- | --- | --- |
| `GET` | Read data | Yes | Yes |
| `POST` | Create a new resource or start an operation | No | Usually no |
| `PUT` | Replace a resource at a known id | No | Yes |
| `PATCH` | Partially update a resource | No | Usually no |
| `DELETE` | Remove a resource | No | Yes |

Safe means the method should not change server state. Idempotent means repeating the same request should have the same final effect.

## Common Status Codes

| Code | Meaning | Example |
| --- | --- | --- |
| `200 OK` | Request succeeded with a response body | List tasks |
| `201 Created` | Resource was created | Create task |
| `204 No Content` | Request succeeded without a response body | Delete task |
| `400 Bad Request` | Request data is invalid | Missing title |
| `401 Unauthorized` | Authentication is missing or invalid | No login token |
| `403 Forbidden` | Client is known but not allowed | User cannot edit this task |
| `404 Not Found` | Resource does not exist | Task id not found |
| `409 Conflict` | Request conflicts with current state | Duplicate unique value |
| `500 Internal Server Error` | Unexpected server failure | Unhandled exception |

## Choosing A Response

Ask these questions:

1. Did the request change data?
2. Was a new resource created?
3. Did the client send invalid data?
4. Is the requested resource missing?
5. Is the server hiding an unexpected failure?

## Practice

For each operation, choose a method and likely success status:

- Create a book.
- Find a book by id.
- Replace a task title and completion state.
- Delete a product.
- Search users by email domain.

Before moving on, you should be able to explain why `GET /tasks` and `POST /tasks` can share a path but do different work.
