# Observability Tracing Flow

This flow shows how a trace can connect work across services.

```text
trace id abc123
  -> api gateway span
  -> task-api span
  -> notification-service span
  -> database span or dependency span
```

## Useful Trace Questions

- Which span is slow?
- Did the request retry?
- Did trace context cross service boundaries?
- Which deployment version handled the request?
- Which error message appears in application logs for the same trace?

## Reminder

Traces are strongest when paired with metrics, logs, and deployment history.

