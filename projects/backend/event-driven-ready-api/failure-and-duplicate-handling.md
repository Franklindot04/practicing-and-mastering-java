# Failure And Duplicate Handling

Event-driven systems must expect delayed work, failed consumers, and duplicate events.

## Failure Types

- Producer validation failure.
- Event record creation failure.
- Consumer processing failure.
- Temporary dependency failure inside a consumer.
- Unknown outcome after a timeout.

## Duplicate Handling

Consumers should plan how to handle the same event more than once.

```text
TaskCompleted eventId=abc
  |
  v
Notification consumer
  |
  +--> Has notification for eventId abc already been created?
```

Possible techniques:

- Store processed event identifiers.
- Use natural uniqueness rules.
- Make updates idempotent.
- Treat duplicate processing as success when the intended result already exists.

## Failure Questions

- Which failures should be retried?
- Which failures should stop and wait for human review?
- What information is needed to investigate a failed event?
- How long can a consumer be behind before users notice?
- Can retries create duplicate side effects?
