# Java Messaging Foundations

This standalone Maven example demonstrates Java messaging concepts without requiring Kafka, RabbitMQ, JMS providers, Docker, or network access for default tests.

## What It Demonstrates

- broker-neutral message envelopes, headers, acknowledgements, negative acknowledgements, retry metadata, and dead-letter records
- Kafka-style producer and consumer adapter plans with keyed records, manual commits, consumer groups, serializers, and idempotent handling
- RabbitMQ-style exchange, queue, binding, routing key, publisher confirm, manual acknowledgement, negative acknowledgement, prefetch, and dead-letter routing
- JMS-style queue/topic send plans, selector representation, acknowledgement modes, and local transaction boundaries
- tolerant schema evolution with additive fields and incompatible change rejection
- deterministic stream processing with filtering, mapping, grouping, event-time windows, and late-event handling

## Run

```bash
mvn test
```

Default tests use fakes and deterministic data only. There is no integration-test profile in this module. Real broker integration tests would require optional broker infrastructure and separate adapter tests; an in-memory simulation is not a real broker and does not prove production readiness.
