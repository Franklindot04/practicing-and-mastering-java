# Messaging and Streaming Order Pipeline

This project demonstrates a realistic Java order-processing pipeline without requiring Kafka, RabbitMQ, JMS providers, Docker, or network access for default tests.

## Workflow

1. Order submitted
2. Order validated
3. Inventory reserved
4. Payment processed
5. Shipment prepared
6. Order status projection updated
7. Analytics aggregation updated
8. Notification message produced

## Concepts

- typed message contracts and envelope validation
- partition keys, routing keys, consumer-group assignments, offsets, acknowledgements, and publisher confirms
- idempotent consumption, duplicate delivery, redelivery after failure, retry exhaustion, dead-letter records, and replay
- RabbitMQ-style direct/topic/fanout routing and dead-letter routing
- JMS queue/topic comparison while keeping Kafka semantics separate
- schema evolution with tolerant readers and incompatible version rejection
- deterministic stream aggregation with late-event accounting

## Run

```bash
mvn test
```

Default tests are deterministic and use in-memory simulations only. There is no optional integration-test profile in this project. Real broker tests would require separated infrastructure, explicit commands, and operational limits; an in-memory simulation is not production readiness.
