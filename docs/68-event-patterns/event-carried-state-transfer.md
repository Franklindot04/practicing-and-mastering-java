# Event-Carried State Transfer

Event-carried state transfer means the event includes enough data for consumers to update their own local view without immediately calling the producer.

## Example

```text
TaskCompleted
  taskId: 42
  title: Write release notes
  completedAt: 2026-07-25T10:15:00Z
  completedByUserId: 15
```

The reporting consumer can update a read model using only the event.

## Why Use It

- Consumers can work when the producer is not immediately available.
- Read models can be updated efficiently.
- Consumer processing may require fewer synchronous calls.

## Tradeoffs

- Payloads are larger.
- More data becomes part of the event contract.
- Sensitive data must be handled carefully.
- Consumers may hold stale local copies until later events arrive.

## Questions

- Which fields are truly needed by consumers?
- Is the copied data allowed to live in consumer storage?
- How will corrections or later updates be represented?
