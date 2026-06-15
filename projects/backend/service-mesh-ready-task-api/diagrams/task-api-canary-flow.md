# Task API Canary Flow

This is a conceptual canary flow for a future dependency.

```text
task-api
  -> task-api sidecar
  -> 90 percent notification-service v1
  -> 10 percent notification-service v2
```

## Review Before Increasing Traffic

- Compare v1 and v2 error rates.
- Compare latency percentiles.
- Check application logs.
- Check user-visible behavior.
- Confirm rollback steps.

## Safety Note

Canary percentages here are examples for reasoning only. They are not production policy.

