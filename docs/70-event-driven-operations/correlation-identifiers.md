# Correlation Identifiers

A correlation identifier links related work across boundaries.

## Example

```text
Request: POST /tasks
correlationId: req-123
  |
  v
TaskCreated
correlationId: req-123
  |
  +--> Notification consumer
      correlationId: req-123
```

## Why It Helps

- Logs from different components can be searched together.
- Operators can follow delayed work.
- Developers can debug one business action across time.
- Support teams can connect user reports to internal evidence.

## Good Habits

- Create a correlation ID at the boundary if one is missing.
- Copy it into event metadata.
- Include it in consumer logs.
- Avoid using sensitive data as the correlation ID.
- Keep event ID and correlation ID separate.

## Difference From Event ID

An event ID identifies one event.

A correlation ID identifies a broader chain of related work.
