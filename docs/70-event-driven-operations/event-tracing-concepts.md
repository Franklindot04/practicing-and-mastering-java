# Event Tracing Concepts

Event tracing connects one business action across requests, events, and consumers.

## Why Tracing Matters

Asynchronous flows happen over time. Without trace information, teams may see separate logs but not understand that they belong to the same user action.

## Conceptual Trace

```text
HTTP request
  correlationId=abc
  |
  v
TaskCreated
  correlationId=abc
  |
  +--> Notification consumer logs correlationId=abc
  |
  +--> Reporting consumer logs correlationId=abc
```

## Useful Trace Fields

- Request identifier.
- Correlation identifier.
- Event identifier.
- Event type.
- Entity identifier.
- Consumer name.
- Processing result.

## Questions

- Can one user action be followed across all related logs?
- Can a failed consumer be connected back to the original event?
- Can duplicate handling be seen in the trace?
- Can delayed work be distinguished from missing work?
