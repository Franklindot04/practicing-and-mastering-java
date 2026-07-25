# Architecture Overview

The service design separates domain events from broker adapters.

```text
Task domain event
  |
  v
MessageEnvelope
  |
  v
MessageProducerPort
  |
  +--> Kafka adapter later
  +--> RabbitMQ adapter later
```

Consumers follow the reverse path:

```text
Broker adapter later
  |
  v
MessageEnvelope
  |
  v
MessageConsumerPort
  |
  v
Domain handler
```

## Design Choices

- Message envelopes carry ids, correlation ids, timestamps, types, and payloads.
- Serialization is a boundary, not a domain concern.
- Retry and dead-letter behavior are explicit.
- Idempotency storage is an interface so it can later use a database table.
- Observability captures message type, correlation id, outcome, and attempts.
