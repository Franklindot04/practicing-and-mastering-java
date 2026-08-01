# Ordering Partitioning And Concurrency

Ordering is not one guarantee. A system may provide no ordering, global ordering, per-partition ordering, or per-key ordering. Throughput usually improves when ordering is scoped narrowly.

## Global And Per-Key Ordering

Global ordering means every consumer sees every event in one total order. It is expensive and often unnecessary.

Per-key ordering means events for the same business key are processed in order, while different keys can proceed concurrently. For orders, the partition key is often `orderId`.

```java
record OrderEvent(String orderId, int aggregateVersion, String status) {}
```

## Partitions And Concurrent Consumers

A partition groups events for ordering and parallelism. Concurrent consumers can process different partitions at the same time. If two consumers process the same aggregate concurrently, race conditions can appear.

Hot keys are keys with disproportionate traffic. A popular tenant, account, or product can overload one partition while others sit idle. Good partition keys balance ordering needs against throughput.

## Out-Of-Order Late And Stale Events

An out-of-order event arrives before an earlier event. A late event arrives after the system already moved on. A stale event describes an older aggregate version.

Consumers can use sequence numbers or aggregate versions:

```java
boolean canApply(int currentVersion, int incomingVersion) {
    return incomingVersion == currentVersion + 1;
}
```

If `incomingVersion <= currentVersion`, the event is duplicate or stale. If `incomingVersion > currentVersion + 1`, a previous event is missing, and the consumer can buffer, defer, reject, or request repair depending on the business need.

## Optimistic Concurrency And Conflict Detection

Optimistic concurrency applies a change only if the current version is what the event expects. If the version differs, the consumer detects a conflict instead of silently overwriting state.

This protects read models and workflow state from stale events.

## Buffering Deferred Application And Watermarks

Buffering holds future events until missing earlier events arrive. Deferred application records that an event cannot be applied yet.

Watermarks estimate that no earlier event is expected before a point in event time. They are useful in stream processing, but Stage 26 treats them only as a high-level concept.

## Event Time And Processing Time

Event time is when the business fact occurred. Processing time is when a consumer handles it. They differ during delays, retries, outages, and replay.

Use event time for business ordering when the event source is trusted. Use processing time for operational measurements such as consumer latency.

## Ordering Versus Throughput

Strict ordering can reduce concurrency. More partitions can increase throughput but may weaken ordering across keys. The right choice depends on invariants:

- Account balance events may need per-account ordering.
- Search indexing may tolerate late updates if a newer version wins.
- Analytics may tolerate approximate ordering with correction.
- Payment and inventory workflows should detect stale transitions explicitly.
