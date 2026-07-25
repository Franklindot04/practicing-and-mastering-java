# Strengths Tradeoffs And Mistakes

Kafka is powerful, but it is not the right tool for every asynchronous problem.

## Strengths

- Durable event streams.
- Replayable history within retention.
- High-throughput publishing and consuming.
- Independent consumer groups.
- Partitioned scaling.

## Tradeoffs

- More operational complexity than an in-process queue.
- Partitioning requires careful key design.
- Ordering is per partition, not global.
- Consumers must manage offsets and duplicates.
- Record schemas need compatibility discipline.

## When Kafka Is Not Appropriate

Kafka may be unnecessary when:

- A simple synchronous call is clearer.
- A small queue is enough for background work.
- The team does not need replayable streams.
- The team cannot operate the broker responsibly.
- Strict per-message routing is more important than append-only stream processing.

## Common Mistakes

- Treating Kafka like a global ordered list.
- Publishing unstable payloads without versioning.
- Committing offsets before durable processing.
- Using random keys when entity ordering matters.
- Replaying consumers that perform unsafe side effects.

## Review Questions

1. What makes Kafka different from a basic queue?
2. Why is key choice important?
3. Name one situation where Kafka adds needless complexity.
