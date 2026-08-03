# Messaging And Streaming Architecture Scenario Quiz

## AS1. Order Workflow Messaging Design

Design a Java order workflow where validation, inventory, payment, shipment, analytics, and notification react to order events. Include topic or queue choice, partition or routing key, acknowledgement timing, idempotency, retry, dead-letter, replay, and observability decisions.

## AS2. Broker Selection

A team needs high-throughput replayable analytics and flexible command routing for operational tasks. Explain where Kafka and RabbitMQ each fit, and where JMS might or might not help.

## AS3. Schema Rollout

`PaymentProcessed v1` has `orderId` and `paymentId`. `v2` adds `currency`, `amountCents`, and `processorReference`. Design a rollout that old and new consumers can survive.

## AS4. Stream Aggregation

Design revenue aggregation by five-minute event-time windows. Include filtering, mapping, grouping, state store, changelog, watermarks, late-event policy, and replay recovery.
