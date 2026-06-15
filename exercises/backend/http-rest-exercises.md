# HTTP And REST Exercises

## Exercise 1: Choose Methods And Status Codes

Difficulty: Beginner

Concepts practiced: HTTP methods, status codes, idempotency

Problem statement: For each operation, choose the best HTTP method and success status code.

- List all books.
- Create a book.
- Replace book `15`.
- Delete book `15`.
- Find a book that does not exist.

Hints:

- Use `GET` for reading.
- Use `201 Created` when a new resource is created.
- Use `204 No Content` when a delete succeeds without a body.

Stretch challenge: Explain which operations are idempotent.

## Exercise 2: Design Task Endpoints

Difficulty: Beginner

Concepts practiced: Resource naming, path variables, query parameters

Problem statement: Design endpoints for a task API that can list tasks, filter incomplete tasks, find one task by id, create a task, update a task, and delete a task.

Hints:

- Prefer `/tasks` over `/getTasks`.
- Use path variables for resource ids.
- Use query parameters for filters.

Stretch challenge: Add one endpoint for searching tasks by a word in the title.

## Exercise 3: Identify Request Pieces

Difficulty: Beginner

Concepts practiced: Headers, query parameters, request bodies

Problem statement: Given `POST /tasks?notify=true` with JSON body `{"title":"Review REST"}`, identify the method, path, query parameter, and body field.

Hints:

- The query string starts after `?`.
- JSON body data is not the same as a query parameter.

Stretch challenge: Add two useful headers to the request.
