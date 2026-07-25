# Keys Ordering Retention And Replay

Kafka keys help decide where records go.

## Message Keys

Records with the same key usually go to the same partition.

```text
key task-123:
  TaskCreated
  TaskAssigned
  TaskClosed
```

Using the same key helps preserve order for one entity.

## Ordering Within Partitions

Kafka preserves order within a partition.

Kafka does not promise a single total order across all partitions in a topic.

## Replay

Replay means reading older records again.

Replay can rebuild a projection, re-run analytics, or recover a consumer that lost local state.

Replay is dangerous when consumers perform non-idempotent side effects such as sending emails, charging cards, or calling external APIs.

## Simple Java Boundary

A Java application should keep broker records separate from domain handling:

```text
Kafka record
  |
  v
Deserializer
  |
  v
Domain event handler
```

This makes testing easier and keeps Kafka-specific details out of core business logic.

## Review Questions

1. Why would records for the same task use the same key?
2. What does Kafka order and what does it not order?
3. Why must replay-safe consumers be idempotent?
