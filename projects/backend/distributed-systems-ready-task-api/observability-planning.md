# Observability Planning

Distributed systems require enough observability to follow work across boundaries.

## Signals To Plan

- Request IDs and correlation IDs.
- Logs that show boundary decisions without exposing secrets.
- Metrics for dependency latency, timeouts, retries, and failures.
- Traces that show service-to-service paths.
- Business status records for long-running workflows.

## Trace Sketch

```text
request-id abc

Client -> Task API -> Notification boundary
                 \-> Task data store
```

Each step should be explainable after the request finishes.

## Prior Stage Connection

Production readiness introduced logging, health, and observability basics. Service mesh notes introduced tracing concepts across service-to-service traffic. Distributed readiness combines those ideas with workflow state and failure investigation.

## Review Questions

- Can an operator find all logs for one user request?
- Can retry attempts be counted?
- Can duplicate requests be identified?
- Can delayed workflows be listed by status?
- Can dependency failure be separated from validation failure?

