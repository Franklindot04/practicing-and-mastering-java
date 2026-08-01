# Distributed Systems Answer Key

## Multiple Choice

1. B. A timeout proves only that the client did not receive a timely response. A is plausible but wrong because the server may still commit. D is unsafe without idempotency.
2. B. CAP names the consistency/availability trade-off for operations affected by partition. It is not about CPU, memory, encryption, or Java threads.
3. A. Read-your-writes means the user can observe their own completed write. Eventual consistency alone does not guarantee that.
4. B. Clocks can skew and timestamps do not prove observation. Logging timestamps is useful but not causal proof.
5. B. Missed heartbeats are suspicion. "Guaranteed dead" ignores pauses, packet loss, and partitions.
6. A. Fencing tokens block stale leaders if every protected resource checks them.
7. A. Asynchronous replication can lag. It does not remove failover risk or provide exactly-once guarantees.
8. A. Modulo uses the partition count as divisor, so changing it can remap many keys.
9. A. A hot partition receives disproportionate traffic or work.
10. B. Distributed readiness needs cluster-level signals. Process liveness alone is too weak.

## Short Answer

1. One JVM can run while peers are unreachable, overloaded, partitioned, paused, or acting on stale state. Distributed health is a property of communication and agreement, not only process uptime.
2. If the first request commits but the acknowledgement is lost, a retry may repeat the effect. A stable command ID and idempotent receiver reduce the risk.
3. A profile photo or recommendation can often be stale briefly. Account transfers, medication allergies, or inventory reservations can be dangerous when stale.
4. `R=3, W=3` overlaps because `3 + 3 > 5`. `R=2, W=4` also overlaps. The formula is conceptual and does not replace a full protocol.
5. On receive, set `local = max(local, received) + 1`. Lamport clocks show that causality implies ordering, but a lower number alone does not prove causality.
6. Stale membership can route traffic to failed nodes, exclude healthy nodes, or let two groups believe they are authoritative.
7. Older terms may belong to stale leaders. Rejecting them reduces split-brain writes when resources enforce the check.
8. Pending replication operations, version gaps, lag duration, stale-read rate, or replica apply delay can reveal lag.
9. Rebalancing moves data, changes routing, warms caches, and consumes capacity. Clients may also use stale routing tables.
10. Verify membership, current term, state version, replication lag, dependency readiness, and successful heartbeats before marking healthy.

## Failure Analysis And Design

1. The timeout was an unknown outcome. A safer design uses a command ID, idempotent payment call, status lookup, reconciliation job, and user messaging that avoids claiming success or rollback until evidence exists.
2. In a five-node cluster, the three-node side has majority quorum and is the safer authority for correctness-sensitive writes. The two-node side should reject such writes. After healing, compare terms, repair replicas, and reject stale leaders.
3. Clock skew and pauses can let two schedulers believe they own the same job. Safer designs use short leases with renewal margins, monotonic elapsed-time checks, and fencing tokens checked by the job state store.
4. The promoted replica may miss the latest write, causing lost updates or stale reads. Checks include replication version, durable log position, quorum acknowledgement policy, and recovery repair before promotion.
5. Modulo hashing is simple but moves many keys when node count changes. Consistent hashing usually moves fewer keys by placing nodes and keys on a ring. It still needs routing metadata, virtual-node tuning, replication, and controlled data movement.
