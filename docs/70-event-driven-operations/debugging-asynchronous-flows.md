# Debugging Asynchronous Flows

Debugging asynchronous flows requires checking time, state, and consumer progress.

## Debugging Path

```text
Symptom
  |
  v
Find related entity
  |
  v
Find event
  |
  v
Find consumer result
  |
  v
Decide retry, correction, or code fix
```

## Common Questions

- Was the event created?
- Was the event made available to consumers?
- Did the expected consumer receive it?
- Did the consumer fail or skip it?
- Was the event processed twice?
- Is the visible state stale rather than wrong?

## Debugging Mindset

Avoid assuming that the last visible component is where the problem started. The event may be missing, delayed, malformed, duplicated, or rejected by a consumer.

## Useful Evidence

- Event ID.
- Correlation ID.
- Entity ID.
- Event creation time.
- Consumer attempt time.
- Failure reason.
- Current state of the affected entity.
