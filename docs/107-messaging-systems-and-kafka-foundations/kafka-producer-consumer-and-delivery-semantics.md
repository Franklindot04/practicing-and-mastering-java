# Kafka Producer Consumer And Delivery Semantics

## Producer Design

Kafka Java producers can send synchronously or asynchronously. Synchronous sends are simple but limit throughput. Asynchronous sends use callbacks and require careful error handling:

```java
producer.send(record, (metadata, error) -> {
    if (error != null) {
        // classify retryable errors separately from contract or authorization failures
        return;
    }
    // metadata contains topic, partition, and offset
});
```

Important producer settings include `acks`, `retries`, `delivery.timeout.ms`, `linger.ms`, `batch.size`, `compression.type`, `enable.idempotence`, serializers, and security settings. Batching and compression improve throughput but can increase latency and memory pressure.

Producer acknowledgement confirms broker acceptance according to the selected `acks` level. It does not confirm that any consumer processed the record.

## Consumer Design

A Kafka consumer polls records, deserializes them, processes them, and commits offsets. A production loop needs shutdown handling, pause and resume decisions, poison-record handling, metrics, and clear ownership of offset commits.

```java
while (running.get()) {
    ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(500));
    for (ConsumerRecord<String, byte[]> record : records) {
        ProcessingResult result = handler.handle(record.key(), record.value(), record.headers());
        if (result.retryable()) {
            retryPublisher.publish(record);
            continue;
        }
        if (result.permanentFailure()) {
            deadLetterPublisher.publish(record, result.reason());
        }
    }
    consumer.commitSync();
}
```

This is a design sketch, not a complete production loop. Real code must handle partial batches, commit failures, rebalance callbacks, wakeups, metrics, and idempotency.

## Duplicate Delivery

Acknowledgements and commits do not eliminate duplicates. A consumer can perform a database update, crash before committing, and then process the same record again. Use an idempotency key such as message ID, event ID, or business key plus version.

## Exactly-Once Semantics Scope

Kafka transactions and idempotent producers can provide exactly-once processing for specific Kafka read-process-write topologies when configured correctly. The scope is narrow: producer idempotence, transactional IDs, consumers using `read_committed`, and offset commits included in the transaction.

Those semantics do not automatically make external systems exactly once. A database update, email, payment call, cache write, or HTTP request outside the Kafka transaction can still happen zero, one, or multiple times from the business perspective. Treat external side effects with idempotency, reconciliation, and repair workflows.

## Error Mapping

Separate failures:

- serialization and malformed payloads: usually permanent until repaired
- authorization and configuration errors: operational failure
- broker timeout or leader movement: often retryable but bounded
- business validation rejection: not a transport retry
- downstream dependency outage: retryable with budget and backoff

Poison records should not block a partition forever. Send them to a dead-letter topic with enough context for repair.
