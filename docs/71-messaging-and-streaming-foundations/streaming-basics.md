# Streaming Basics

Streaming is a style of communication where records are appended to an ordered log or stream and consumers read those records over time.

Messaging often focuses on delivering work. Streaming often focuses on retaining a history of records that can be read, processed, and sometimes replayed.

## Simple Stream

```text
Order events stream

[OrderCreated] [OrderPaid] [OrderPacked] [OrderShipped]
       ^              ^
       |              |
   consumer A     consumer B
```

Consumers can process new records as they arrive. Some streaming systems also let consumers start from an earlier position.

## Messaging Versus Streaming

Messaging:

- Often used for tasks, notifications, and asynchronous work.
- Often removes a message from a queue after successful processing.
- Often emphasizes routing and work distribution.

Streaming:

- Often used for event logs, analytics pipelines, and replayable history.
- Often keeps records for a retention period.
- Often emphasizes ordered partitions and consumer positions.

## Java Backend Examples

- A task API publishes `TaskCreated` records for audit and reporting.
- A billing service emits payment status changes for downstream projections.
- A moderation service processes a stream of submitted comments.

## Common Misconceptions

- A stream is not just a faster queue.
- Replay is powerful, but replaying unsafe handlers can duplicate side effects.
- Retention does not mean every record should contain every piece of data forever.

## Review Questions

1. Why might replay be useful?
2. Why can replay be risky?
3. What kind of Java backend feature might read a stream?
