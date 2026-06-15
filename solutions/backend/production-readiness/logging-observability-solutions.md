# Logging And Observability Solutions

## Exercise 1

Reasonable levels:

- App started: `INFO`
- Invalid task title: `INFO` or `WARN`, depending on volume and context
- Database unavailable: `ERROR`
- Task created: `INFO`
- Detailed request parsing: `DEBUG`

Structured example:

```text
event=task.created taskId=42
```

## Exercise 2

Remove passwords and raw tokens entirely. Mask or avoid email depending on policy. IP address can be useful for abuse investigation, but treat it as sensitive operational data.

Safe example:

```text
event=login.failed emailHash=abc123 reason=bad_credentials
```

## Exercise 3

A simple health response can include:

```json
{
  "status": "UP",
  "components": {
    "application": "UP",
    "database": "UP"
  }
}
```

If the database is required for serving requests, a database outage should make readiness fail. The process may still be live, but it is not ready to handle normal traffic.
