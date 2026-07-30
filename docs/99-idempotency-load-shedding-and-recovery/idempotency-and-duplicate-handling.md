# Idempotency And Duplicate Handling

Distributed callers retry, networks fail, users double-submit, and queues redeliver. Idempotency makes equivalent repeats safe.

## Coverage Notes

### Mathematical And Operational Idempotency

Mathematically, applying an operation more than once has the same effect as once; operationally, duplicate requests produce one accepted side effect.

Java angle: Setting a task status is naturally closer to idempotent than charging a card.

Tradeoff or failure case: Do not call an operation idempotent only because the response text is the same.

### Naturally Idempotent Operations

Reads and assignments like `setEmail(value)` can be naturally idempotent.

Java angle: Prefer PUT-like replace semantics where they fit.

Tradeoff or failure case: Increment, append, and charge are not naturally idempotent.

### Idempotency Keys

An idempotency key identifies one logical request across retries.

Java angle: Store the key before executing side effects.

Tradeoff or failure case: Keys must be scoped to the caller and operation.

### Fingerprints

A fingerprint summarizes the request shape that belongs to a key.

Java angle: Reject the same key with a different fingerprint.

Tradeoff or failure case: Without fingerprints, callers can accidentally reuse keys for different work.

### Deduplication Records

Deduplication records store key, fingerprint, status, and response.

Java angle: In Java examples this may be an in-memory map.

Tradeoff or failure case: Production systems need durable storage.

### Suppression

Duplicate suppression avoids repeating side effects for equivalent requests.

Java angle: Return the stored response for a duplicate key.

Tradeoff or failure case: Suppression before completion needs concurrency handling.

### Replay Safety

Replay safety means reprocessing an event or request does not repeat unsafe work.

Java angle: Idempotent consumers record processed message IDs.

Tradeoff or failure case: Replay safety must include side-effect ordering.

### Windows

Idempotency windows define how long duplicate records are retained.

Java angle: Choose based on retry and replay behavior.

Tradeoff or failure case: Too short a window allows late duplicates.

### Stored Responses

Stored responses let duplicates receive the same outcome.

Java angle: Cache success, failure, or in-progress state deliberately.

Tradeoff or failure case: Storing only success can repeat failed side effects.

### Races And Concurrent Duplicates

Concurrent duplicates arrive before the first request completes.

Java angle: Use atomic map operations, locks, or database uniqueness.

Tradeoff or failure case: Check-then-act maps can execute twice.

### Uniqueness Constraints

Database uniqueness constraints enforce one record per logical key.

Java angle: They are a production-grade backstop beyond in-memory examples.

Tradeoff or failure case: A constraint alone does not define the response to duplicates.

### At-Least-Once Delivery

At-least-once systems may deliver the same message repeatedly.

Java angle: Consumers must be idempotent.

Tradeoff or failure case: At-least-once is common because it preserves durability.

### Exactly-Once Claims

Exactly-once usually means a scoped guarantee with conditions, not magic absence of duplicates.

Java angle: Read vendor guarantees carefully.

Tradeoff or failure case: Application side effects can still duplicate.

### Idempotent Consumers

Idempotent consumers record processed identifiers or write deterministic upserts.

Java angle: Handle redelivery after crashes.

Tradeoff or failure case: Acknowledging before side effects can lose work.

### Side-Effect Ordering

Side effects must happen in an order that can be recovered.

Java angle: Record intent before irreversible work when appropriate.

Tradeoff or failure case: Sending notification before payment commit can mislead users.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
