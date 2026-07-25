# Messaging-Ready Service Design

This project prepares a Java backend service for future Kafka or RabbitMQ adapters without requiring a running broker.

It is intentionally planning-focused. The code compiles and tests pure Java boundaries only. It does not contain hostnames, credentials, Docker Compose, Kubernetes manifests, cloud resources, or production deployment configuration.

## Goals

- Keep broker details outside domain logic.
- Define producer and consumer boundaries.
- Model message envelopes, correlation ids, retries, dead letters, idempotency, and observability.
- Leave clear placeholders for future Kafka or RabbitMQ adapters.

## Architecture

```text
Domain service
  |
  v
DomainEventMapper
  |
  v
MessageProducerPort
  |
  +--> future Kafka adapter
  |
  +--> future RabbitMQ adapter

MessageConsumerPort
  |
  v
MessageHandler
  |
  +--> IdempotencyStore
  +--> RetryPolicy
  +--> DeadLetterPublisher
  +--> ConsumerObservation
```

## Package Design

- `messaging`: message envelope, ports, retry policy, dead-letter types, idempotency abstraction, and observability model.
- `task`: small domain event mapping example.

## Limitations

- No real broker connection.
- No durable storage implementation.
- No deployment configuration.
- No production security or tuning defaults.

## Run Tests

```sh
mvn test
```
