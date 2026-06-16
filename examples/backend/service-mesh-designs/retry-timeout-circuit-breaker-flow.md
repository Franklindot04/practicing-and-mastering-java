# Retry, Timeout, And Circuit Breaker Flow

This flow shows reliability policy around an internal dependency call.

```text
task-api
  -> local timeout
  -> sidecar route policy
  -> notification-service
```

## Example Reasoning

1. The user-facing request has a total deadline.
2. `task-api` sets a client timeout shorter than that deadline.
3. The mesh policy avoids retrying unsafe non-idempotent operations.
4. Circuit breaking reduces traffic to a failing dependency.
5. The application returns a clear response when notification work cannot finish.

## Review Question

If both application code and mesh policy retry the same request, how many total attempts might happen?

