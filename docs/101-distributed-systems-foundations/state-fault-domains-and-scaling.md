# State Fault Domains And Scaling

State is the information a system relies on to answer requests and make decisions. In one JVM, state may live in objects, caches, files, or database connections. In a distributed system, the location and ownership of state become design choices.

## State Ownership

Every piece of mutable state should have a clear owner. Ambiguous ownership creates conflicting writes and unclear recovery.

Examples:

- an account service owns account status
- an inventory service owns available stock
- a notification service owns delivery attempts

Other services can keep copies, but a copy is not the same as ownership. Copies can be stale.

## Horizontal Scaling

Horizontal scaling adds more nodes. Stateless Java services are easier to scale because any node can handle any request. Stateful services need routing, replication, locking, partitioning, or another coordination strategy.

```java
record CustomerId(String value) { }

int partitionFor(CustomerId id, int partitionCount) {
    return Math.floorMod(id.value().hashCode(), partitionCount);
}
```

This simple modulo partitioning is easy to teach, but adding or removing partitions can move many keys. Later examples compare it with consistent hashing.

## Fault Domains And Blast Radius

Scaling can reduce one kind of risk while adding another. Three JVMs on three hosts survive one host failure better than one JVM on one host. Three JVMs all depending on one saturated database may still fail together.

Ask:

- Which dependencies are shared?
- Which queues, pools, or caches can fill up?
- Which credentials, DNS entries, or certificates can expire together?
- Which deployment can break all nodes at once?

## Coordination Costs

Coordination is any communication needed for nodes to agree, order work, elect owners, replicate data, or avoid conflicting decisions. Coordination costs latency, throughput, and operational simplicity.

Examples:

- acquiring a distributed lease before processing a scheduled job
- waiting for replica acknowledgements before confirming a write
- checking membership before routing a request
- blocking writes during a partition because quorum is not available

The more often a system coordinates, the more it behaves like one slower shared system.

## Availability Versus Correctness

Some state can be approximate. A cached product count may be allowed to lag. Other state must protect invariants. A seat reservation system should not sell the same seat twice simply to remain available.

The business rule determines the consistency need. Technology does not remove that choice.

## Review Questions

1. Why should mutable state have an owner?
2. Why is stateless code easier to scale horizontally?
3. What shared dependency can defeat horizontal scaling?
4. What is one coordination cost paid by synchronous replication?
