# Outbox Pattern

The outbox pattern stores an event record in the same durable boundary as the business state change.

Instead of saving state and immediately publishing externally, the application writes both the business row and an outbox row in one local transaction.

## Concept

```text
Business transaction
  |
  +--> Save order
  |
  +--> Save OrderCreated outbox record
```

Later, a publisher reads unpublished outbox records and makes them available to consumers.

## Why It Helps

The state change and the intent to publish are saved together. If the transaction commits, both the order and the outbox record exist. If it rolls back, neither exists.

## What It Does Not Remove

The outbox pattern does not remove every problem.

- Publishing may still fail later.
- Consumers may still receive duplicates.
- Outbox records need monitoring and cleanup policies.
- Ordering and idempotency still need design.

## Design Questions

- What event should be written to the outbox?
- What marks an outbox record as published?
- How are failed publish attempts retried?
- How long should published records be retained?
