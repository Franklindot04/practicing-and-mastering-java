# Networking And JDBC Solutions

## HTTP Status Reader

Use `HttpClient` with timeouts and catch expected network failures. Restore interrupt status if catching `InterruptedException`.

## Socket Echo Design

The server listens with `ServerSocket`, accepts a client, reads input, and writes a response. A real server needs resource handling, timeouts, and concurrency limits.

## Prepared Statement Shape

Use SQL placeholders and bind values with `PreparedStatement#setString`. Credentials should come from environment variables, deployment configuration, or a secret manager.
