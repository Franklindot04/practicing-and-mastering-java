# Messaging And Streaming Exercises

## 1. Queues Topics And Broker Choice

Objective: Compare queues, topics, Kafka, RabbitMQ, and JMS.

Scenario: An order service must notify billing, fulfillment, analytics, and email systems.

Requirements: Choose whether each consumer needs queue-style competing consumption or topic-style fan-out. Identify one case where Kafka fits, one where RabbitMQ fits, and one where JMS abstraction could help.

Constraints: Do not assume any broker proves business completion.

Expected reasoning: Discuss retention, routing, replay, consumer groups, exchanges, and provider portability.

Acceptance criteria: The answer distinguishes queue and topic semantics and names at least two technology-specific trade-offs.

Optional extension: Add a migration note for replacing a JMS provider.

## 2. Kafka Topic Partition And Consumer Group Design

Objective: Design Kafka topics, partitions, keys, offsets, and groups.

Scenario: `OrderSubmitted`, `PaymentProcessed`, and `ShipmentPrepared` must preserve per-order ordering while supporting many orders at once.

Requirements: Propose topic names, partition count assumptions, partition key, consumer groups, and manual offset commit timing.

Constraints: Avoid a hot partition and avoid claiming global ordering.

Expected reasoning: Explain why `orderId` is useful, when it may become hot, and what happens during rebalancing.

Acceptance criteria: The design includes lag monitoring, commit timing, idempotency, and rebalance handling.

Optional extension: Explain how replay would rebuild a projection.

## 3. RabbitMQ Routing And Acknowledgement Design

Objective: Choose exchanges, queues, bindings, routing keys, acknowledgements, and prefetch.

Scenario: Order events must route to payment, notification, audit, and analytics queues.

Requirements: Use direct, topic, or fanout exchanges where appropriate. Define routing keys, queue bindings, prefetch, manual acknowledgement, negative acknowledgement, and dead-letter behavior.

Constraints: Avoid requeue loops and distinguish publisher confirms from consumer acknowledgements.

Expected reasoning: Explain queue depth, unacknowledged counts, redelivery flags, and fairness.

Acceptance criteria: The design includes retry routing and a dead-letter exchange.

Optional extension: Add message TTL or queue TTL where useful.

## 4. JMS Abstraction And Portability Limits

Objective: Explain Jakarta Messaging concepts and limits.

Scenario: A company wants provider-independent Java messaging APIs for queues and topics.

Requirements: Describe `ConnectionFactory`, `JMSContext`, `Destination`, `Queue`, `Topic`, `JMSProducer`, `JMSConsumer`, message headers, selectors, acknowledgement modes, durable subscriptions, and local transactions.

Constraints: Do not describe Kafka as a direct JMS implementation.

Expected reasoning: Separate API portability from provider-specific operations.

Acceptance criteria: The answer names at least three provider-specific concerns.

Optional extension: Sketch a Java adapter interface that hides provider lookup.

## 5. Message Contract And Schema Evolution

Objective: Design a versioned message envelope and safe schema changes.

Scenario: `OrderSubmitted v1` has `orderId`, `customerId`, and `totalCents`. A new service wants `currency` and `promotionCode`.

Requirements: Define envelope fields, payload fields, content type, event type, version, correlation ID, causation ID, headers, and tolerant-reader behavior.

Constraints: Handle malformed messages and untrusted payloads.

Expected reasoning: Discuss additive fields, defaults, removed fields, renamed fields, contract tests, and deployment ordering.

Acceptance criteria: The answer identifies backward and forward compatibility risks.

Optional extension: Compare JSON, Avro, and Protocol Buffers at a high level.

## 6. Idempotent Consumer Implementation

Objective: Implement duplicate-safe Java consumer logic.

Scenario: A consumer updates an order projection and may receive the same message twice.

Requirements: Write pseudocode or Java for an inbox/idempotency store, processing transaction, acknowledgement timing, and duplicate result.

Constraints: Acknowledgements do not eliminate duplicate processing.

Expected reasoning: Explain crash before acknowledgement and crash after side effect.

Acceptance criteria: The side effect happens once even when the message is redelivered.

Optional extension: Add a unique database constraint strategy.

## 7. Producer Confirmation And Uncertain Delivery

Objective: Classify producer outcomes.

Scenario: A Java producer sends asynchronously. One send succeeds, one receives a retryable broker error, one times out waiting for confirmation, and one fails serialization.

Requirements: Decide which outcomes are accepted, retryable, uncertain, or permanent.

Constraints: Do not assume retrying a timeout is duplicate-free.

Expected reasoning: Include producer callbacks, broker confirms, idempotent producer concepts, and message IDs.

Acceptance criteria: The answer includes metrics and logging fields for each outcome.

Optional extension: Add graceful shutdown behavior.

## 8. Retry Topics Queues And Dead Letters

Objective: Design bounded retries and dead-letter handling.

Scenario: Consumers fail because of transient database outages, malformed messages, and rate limits.

Requirements: Define immediate retry, delayed retry, attempt headers, exponential delay, jitter, retry budget, poison-message handling, dead-letter destination, parking-lot destination, and manual repair flow.

Constraints: Avoid retry storms and privacy leaks.

Expected reasoning: Distinguish Kafka retry topics from RabbitMQ retry queues.

Acceptance criteria: The design includes ownership, alerting, retention, and replay policy.

Optional extension: Explain ordering consequences.

## 9. Replay And Projection Rebuild

Objective: Design safe replay.

Scenario: A bug corrupted an order status read model. Events are retained for seven days.

Requirements: Describe how to rebuild the projection, protect external side effects, verify results, and record audit history.

Constraints: Do not resend emails or charge payments during replay.

Expected reasoning: Explain retention, offsets, idempotency, side-effect modes, and operator controls.

Acceptance criteria: The plan includes rollback or stop conditions.

Optional extension: Add a replay command-line interface design.

## 10. Consumer Rebalancing And Ordering

Objective: Reason about failure during Kafka consumer rebalancing.

Scenario: A consumer processes records for two partitions and is revoked before committing one partition's offset.

Requirements: Explain what can be retried, how idempotency protects state, and how per-key ordering is preserved or broken.

Constraints: Do not assume automatic commits are always safe.

Expected reasoning: Include partition assignment, consumer groups, long-running handlers, and graceful shutdown.

Acceptance criteria: The answer defines safe revoke handling.

Optional extension: Add lag and rebalance-frequency alerts.

## 11. Stream Windows And Late Events

Objective: Design stream transformations and windows.

Scenario: Analytics needs revenue per five-minute event-time window, but mobile clients can send events late.

Requirements: Define filtering, mapping, grouping, aggregation, event time, processing time, tumbling or hopping windows, watermark, late-event policy, state store, changelog, and replay recovery.

Constraints: Do not ignore late events silently.

Expected reasoning: Explain correction events or grace periods.

Acceptance criteria: The design names window type and late-event behavior.

Optional extension: Compare Kafka Streams and Flink as implementation options.

## 12. Messaging Operations Runbook

Objective: Write an incident runbook.

Scenario: Customer notifications are delayed and dead-letter volume is rising.

Requirements: Investigate broker health, queue depth, consumer lag, throughput, processing latency, acknowledgement failures, commit failures, retry volume, dead-letter volume, hot partitions, unacknowledged messages, schema failures, message-size failures, tracing, correlation IDs, credentials, TLS, and capacity.

Constraints: Do not require cloud credentials or Docker for diagnosis steps.

Expected reasoning: Separate producer, broker, consumer, contract, and downstream failures.

Acceptance criteria: The runbook has triage, mitigation, repair, replay, and follow-up sections.

Optional extension: Add SLO and alert suggestions.
