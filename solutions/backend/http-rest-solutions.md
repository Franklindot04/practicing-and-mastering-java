# HTTP And REST Solutions

## Exercise 1

- List all books: `GET /books`, success `200 OK`.
- Create a book: `POST /books`, success `201 Created`.
- Replace book `15`: `PUT /books/15`, success `200 OK` or `204 No Content`.
- Delete book `15`: `DELETE /books/15`, success `204 No Content`.
- Find a missing book: `GET /books/{id}`, error `404 Not Found`.

`GET`, `PUT`, and `DELETE` are idempotent when designed normally. `POST` create is usually not idempotent.

## Exercise 2

One clean task API:

- `GET /tasks`
- `GET /tasks?completed=false`
- `GET /tasks/{id}`
- `POST /tasks`
- `PUT /tasks/{id}`
- `DELETE /tasks/{id}`

Search can be modeled as `GET /tasks?query=java`.

## Exercise 3

For `POST /tasks?notify=true`:

- Method: `POST`
- Path: `/tasks`
- Query parameter: `notify=true`
- Body field: `title`

Useful headers include `Content-Type: application/json` and `Accept: application/json`.
