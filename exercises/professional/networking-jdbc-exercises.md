# Networking And JDBC Exercises

## Exercise 1: HTTP Status Reader

Difficulty: Professional foundation

Concepts practiced: `HttpClient`, timeouts, error handling

Problem statement: send a GET request to a URL and print the status code. Handle network errors with a friendly message.

Hints:

- Set request and connect timeouts.
- Catch `IOException` and `InterruptedException`.

Stretch challenge: print only the first 100 characters of the response body.

## Exercise 2: Socket Echo Design

Difficulty: Professional foundation

Concepts practiced: sockets, client/server thinking

Problem statement: sketch a local echo server and client. Explain how the client connects and how the server responds.

Hints:

- Use `ServerSocket` for the server.
- Use `Socket` for the client.

Stretch challenge: support more than one client with an executor.

## Exercise 3: Prepared Statement Shape

Difficulty: Professional foundation

Concepts practiced: JDBC, safe SQL, external config

Problem statement: write the method shape for finding a user by email with a prepared statement.

Hints:

- Do not concatenate the email into SQL.
- Do not hard-code credentials.

Stretch challenge: explain where database configuration should come from in a deployed app.
