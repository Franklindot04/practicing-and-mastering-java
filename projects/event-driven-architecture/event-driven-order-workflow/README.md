# Event-Driven Order Workflow Simulator

This project is a deterministic Stage 26 simulator for an event-driven order workflow.

## Purpose

The simulator demonstrates event contracts, correlation and causation IDs, idempotent consumers, retries, ordering checks, sagas, compensation, outbox publication, dead-letter handling, replay, and operational reporting without using a broker, database, Docker, cloud service, or network dependency.

## Domain Flow

1. `OrderSubmitted`
2. `InventoryReserved`
3. `PaymentAuthorizationRequested`
4. `PaymentAuthorized`
5. `OrderConfirmed`
6. `NotificationRequested`

Failure paths include inventory unavailable, payment rejected, payment timeout, duplicate order submission, duplicate event delivery, out-of-order events, notification failure, compensation, transient consumer failure, poison events, dead-letter replay, and outbox publication failure.

## Run

```bash
mvn test
```

## Architecture

`OrderWorkflowSimulator` contains a typed event model, event envelope, deterministic in-memory event channel, event log, processed-event store, retry policy, dead-letter store, saga state, failure injector, transactional outbox, outbox relay, and workflow report.

The code keeps domain state separate from presentation. Tests exercise the scenarios directly.

## Testing Strategy

The JUnit suite covers envelope validation, event ID uniqueness, correlation and causation propagation, consumer registration, handler failure, duplicate delivery, idempotent side effects, ordering checks, retries, retry exhaustion, dead-letter replay, outbox retention, duplicate publication, saga success, compensation, invalid transitions, deterministic ordering, and report accuracy.

## Limitations

This is an educational in-memory simulator. It is not a durable broker, database, production saga orchestrator, production schema registry, durable event store, real distributed transaction coordinator, or exactly-once processing system.

## Production Comparison

A production system would need durable storage, transactional boundaries, broker-specific delivery behavior, authentication, authorization, schema governance, monitoring, alerting, replay tooling, deployment strategy, and incident runbooks. Those concrete technologies belong in later stages, especially Stage 27 for messaging and streaming with Java.
