# Operational Considerations

Event-driven readiness is not complete without operations planning.

## What To Monitor Later

- Events waiting to be published or processed.
- Oldest unprocessed event age.
- Consumer success and failure counts.
- Duplicate event detections.
- Retry counts.
- Poison event counts.
- Consumer processing latency.

## Investigation Workflow

```text
User reports missing notification
  |
  v
Find request correlation id
  |
  v
Find TaskCompleted event
  |
  v
Check notification consumer result
  |
  v
Retry, correct data, or document known failure
```

## Operational Questions

- Who owns each consumer in production?
- What alert means user-visible work is delayed?
- What event data is safe to log?
- How are poison events isolated for review?
- How are old event records retained or cleaned up?

## Learning Rule

An event flow is not ready just because the happy path is documented. It also needs a way to notice, explain, and recover from failure.
