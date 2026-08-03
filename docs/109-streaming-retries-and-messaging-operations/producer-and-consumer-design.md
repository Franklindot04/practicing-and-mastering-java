# Producer And Consumer Design

## Producer Responsibilities

Producers serialize a contract, choose routing metadata, send to a broker, and interpret the result. They may send synchronously for simple command-line tools or asynchronously for throughput-sensitive services.

Production producers need:

- timeouts and bounded retries
- callbacks or result handling for failed sends
- idempotent publication strategy where the broker supports it
- message IDs for duplicate detection downstream
- partition keys or routing keys
- batching, buffering, and compression decisions
- graceful shutdown that flushes known in-flight sends
- metrics for success, latency, errors, and uncertain outcomes

An uncertain send is not the same as a failed send. If the client times out while waiting for a broker confirmation, retrying can publish a duplicate.

## Consumer Responsibilities

Consumers receive messages, validate contracts, handle business work, record idempotency, and acknowledge or commit progress at the right time. Poll-based consumers, such as Kafka consumers, differ from push-style consumers, such as many queue consumers, but both must control side effects.

Consumers need:

- idempotency keys or inbox records
- manual acknowledgement or manual offset commit where correctness requires it
- batch handling with partial-failure rules
- concurrency limits and prefetch or poll-size tuning
- graceful shutdown behavior
- poison-message handling
- timeout and cancellation policy for long-running handlers
- rebalance or redelivery awareness
- lag and processing-latency metrics

Acknowledging after processing can redeliver duplicates after a crash. Acknowledging before processing can lose work. The usual Java answer is durable idempotency plus deliberate acknowledgement.

## Ordering

Ordering is scoped. Kafka gives per-partition order. RabbitMQ queues give queue delivery order, but multiple consumers, redelivery, retry routing, and negative acknowledgements can alter observed processing order. Design ordering around a business key and document what can run independently.

## Outbox Integration

The transactional outbox reduces dual-write failures when local state changes and message publication must both happen. It does not remove duplicate delivery. The relay can publish the same outbox row more than once, so consumers still need idempotency.
