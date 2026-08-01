# Quorums Stale Reads And Business Tradeoffs

A quorum is enough participants to make progress according to a rule. In replicated data systems, the common teaching formula is `read quorum + write quorum > replication factor`. The overlap helps a later read observe at least one participant that saw the write.

This is a concept, not a complete algorithm. Real systems must handle membership changes, failures during writes, repair, leases, clocks, and operator actions.

## Stale Reads

A stale read returns an older value than a successful write. Stale reads can happen with asynchronous replicas, caches, lagging search indexes, or reads routed to a region that has not caught up.

Business impact depends on the invariant:

- acceptable: showing a profile picture from a few seconds ago
- risky: showing old account limits before approving a transfer
- dangerous: showing an old medication allergy list

## Quorum Trade-Offs

Larger write quorums can reduce stale reads but increase latency and reduce availability during failures. Smaller write quorums can accept work during more failures but require clearer stale-read handling.

```java
boolean hasWriteQuorum(int acknowledgements, int replicationFactor) {
    return acknowledgements > replicationFactor / 2;
}
```

This majority rule is useful for learning. Production quorum systems must define which nodes are eligible voters, how membership changes, and what happens when acknowledgements are delayed.

## Read-Your-Writes

A Java API can preserve read-your-writes by routing a user's next read to the primary, using a session token, waiting for a replica version, or returning the written representation from the command response.

Each choice trades latency, availability, and complexity.

## Review Questions

1. Why can asynchronous replication produce stale reads?
2. Give one feature where stale reads are acceptable and one where they are not.
3. What does quorum overlap try to protect?
4. Why is the majority formula incomplete as a production design?
