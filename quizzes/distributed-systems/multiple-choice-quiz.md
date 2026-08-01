# Multiple Choice Quiz

## 1. What does a client timeout prove?

A. The server definitely rolled back the operation.
B. The client did not receive a response before its deadline.
C. The network is permanently partitioned.
D. The operation is safe to retry without identifiers.

## 2. During a network partition, CAP describes a trade-off between:

A. CPU and memory.
B. consistency and availability for affected operations.
C. durability and encryption.
D. Java threads and database indexes.

## 3. Which consistency model means a user can see their own completed write?

A. Read-your-writes.
B. Eventual consistency.
C. Random consistency.
D. Transport consistency.

## 4. Why are wall-clock timestamps insufficient for causality?

A. They are always unique.
B. They can differ across nodes and do not prove one event observed another.
C. They cannot be logged.
D. They are the same as Lamport clocks.

## 5. What should a heartbeat-based detector usually report after missed heartbeats?

A. Guaranteed dead.
B. Suspected.
C. Exactly-once.
D. Durable.

## 6. What does a fencing token protect against?

A. A stale leader continuing to mutate a protected resource.
B. A Java compiler warning.
C. Hash collisions only.
D. A faster network route.

## 7. Which statement about asynchronous replication is true?

A. Replicas can lag and return stale reads.
B. Replicas are always stronger than the primary.
C. It proves exactly-once delivery.
D. It removes failover risk.

## 8. Why can modulo partitioning move many keys after adding a node?

A. The divisor changes.
B. UUIDs cannot be partitioned.
C. Java maps sort keys randomly.
D. Quorums are disabled.

## 9. A hot partition is:

A. a partition receiving disproportionate load.
B. a partition with no data.
C. a partition with perfect quorum.
D. a partition that never needs rebalancing.

## 10. Operational readiness for a distributed system should include:

A. only process liveness.
B. leader changes, quorum health, replication lag, partition signals, and correlation IDs.
C. only unit test coverage.
D. only a faster CPU.
