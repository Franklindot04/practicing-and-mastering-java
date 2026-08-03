# Messaging And Streaming Solutions

## 1. Queues Topics And Broker Choice

Billing, fulfillment, analytics, and email usually need topic-style fan-out from the order service because each owns an independent reaction to the same fact. Inside billing or email, queue-style competing consumers can split work across instances. Kafka fits retained event streams, replay, and consumer groups. RabbitMQ fits flexible routing, work queues, and exchange-based delivery. JMS can help when Java code needs a provider abstraction, but provider operations still differ.

Broker acceptance is not business completion. Consumers still need idempotency, retries, monitoring, and repair.

## 2. Kafka Topic Partition And Consumer Group Design

Use a topic such as `orders.events` and key records by `orderId` so each order's workflow remains ordered within one partition. Use separate consumer groups for projection, analytics, notification, and fraud consumers. Commit offsets manually after the side effect and idempotency record complete.

Assumptions: partition count is chosen from expected throughput and future growth. `orderId` spreads well unless a few orders produce extreme event volume. During rebalancing, revoked partitions may retry uncommitted work, so handlers must be duplicate-safe. Monitor lag, rebalance frequency, processing latency, and hot partitions.

## 3. RabbitMQ Routing And Acknowledgement Design

Use a topic exchange such as `orders.events` with routing keys like `orders.submitted`, `orders.paid`, and `orders.shipped`. Bind payment to payment-specific keys, audit to `orders.*`, analytics to broader patterns, and notification to events that trigger messages. Use manual acknowledgements after durable side effects and idempotency writes.

Publisher confirms show broker acceptance, not consumer completion. Negative acknowledgement with requeue is acceptable only for bounded transient failures. Permanent failures should reject without requeue into a dead-letter exchange. Prefetch should match handler concurrency and fairness goals. Queue depth and unacknowledged counts reveal different failure shapes.

## 4. JMS Abstraction And Portability Limits

`ConnectionFactory` creates provider connections or contexts. `JMSContext` sends and receives. `Destination`, `Queue`, and `Topic` model destinations. `JMSProducer` sends messages and `JMSConsumer` receives them. Messages carry headers and properties; selectors filter by properties. Durable subscriptions retain topic messages for disconnected subscribers according to provider rules.

Provider-specific concerns include redelivery policy, dead-letter configuration, clustering, monitoring, persistence tuning, selectors, security setup, and operational tooling. JMS does not make every provider behave identically, and Kafka's partitioned log model should be treated separately.

## 5. Message Contract And Schema Evolution

A good envelope includes `messageId`, `eventType`, `version`, `contentType`, `correlationId`, `causationId`, timestamp, producer, headers, and payload. Add `currency` with a default such as `USD` and make `promotionCode` optional. Old consumers should ignore unknown fields; new consumers should tolerate missing optional fields.

Removing `customerId`, renaming `totalCents`, or changing cents to dollars in place is unsafe. Contract tests should include serialized examples for old and new versions, malformed payloads, unsupported versions, and untrusted input. JSON is easy to inspect; Avro and Protocol Buffers provide stronger schema tooling but add infrastructure and compatibility discipline.

## 6. Idempotent Consumer Implementation

One valid design is:

```java
if (inbox.alreadyProcessed(message.id())) {
    ack();
    return;
}
transaction(() -> {
    inbox.record(message.id());
    projection.apply(message.payload());
});
ack();
```

If the consumer crashes after the transaction but before acknowledgement, redelivery sees the inbox record and skips the side effect. If it crashes before the transaction commits, redelivery retries the work. A database unique constraint on `message_id` is a strong backstop.

## 7. Producer Confirmation And Uncertain Delivery

A successful callback with metadata is accepted by the broker under the chosen acknowledgement policy. A retryable broker error can be retried within a budget. A confirmation timeout is uncertain because the broker may have accepted the message. A serialization failure is permanent until the code or contract is fixed.

Log message ID, event type, correlation ID, destination, key, attempt, error class, and outcome. Metrics should separate success, retryable failure, permanent failure, and uncertain delivery. Graceful shutdown flushes in-flight sends but still handles callbacks that report uncertainty.

## 8. Retry Topics Queues And Dead Letters

Kafka commonly uses retry topics with delay tiers; RabbitMQ commonly uses retry queues with TTL and dead-letter routing. Include attempt count, original destination, original offset or routing key, first failure, last failure, and failure class in metadata. Use exponential delay, jitter, and retry budgets.

Malformed messages should go directly to quarantine or dead letter. Dead-letter destinations need owners, alerts, retention policy, privacy review, and replay tooling. Retrying can break order when later records continue while one record waits in a delayed destination.

## 9. Replay And Projection Rebuild

Pause or isolate the broken projection, choose a replay range, disable protected side effects, reset offsets or read retained events into a rebuild job, and compare rebuilt counts/checksums against expectations. Record operator identity and replay parameters.

Emails, payments, and external HTTP calls should be disabled or guarded by idempotency. Stop conditions include rising errors, unexpected schema failures, bad counts, or side-effect leakage. Keep audit history for the repair.

## 10. Consumer Rebalancing And Ordering

When partitions are revoked, finish or stop in-flight records, commit only completed offsets, and let uncommitted records retry on the new owner. Idempotency protects duplicate side effects. Per-key ordering is preserved only when the key remains on one partition and the handler does not process later records before earlier records for that key complete.

Automatic commits may mark records complete before side effects finish. Alert on high rebalance frequency, lag spikes, and long handler durations.

## 11. Stream Windows And Late Events

Use event time for revenue windows because business occurrence time matters. A tumbling five-minute window is simplest unless overlapping analysis is required. Map payment events to revenue amounts, group by window and business dimension, aggregate totals, and store state with a changelog.

Watermarks estimate completeness. Late events can update the old window during a grace period and emit correction records after results were published. After the grace period, route very late events to a late-events topic or repair workflow. Kafka Streams and Flink can implement this, with different APIs and operational models.

## 12. Messaging Operations Runbook

Triage: check producer error rate, broker health, queue depth or consumer lag, dead-letter volume, retry volume, schema errors, message-size errors, and downstream latency. Correlate by message ID and correlation ID.

Mitigation: pause poison consumers, scale safe consumers, reduce retry storms, fix credentials or TLS, rollback bad deployments, or route malformed messages to quarantine. Repair: inspect dead letters, patch contracts or data, replay safely, and verify projections. Follow-up: add alerts, capacity changes, contract tests, runbook updates, and owner review.
