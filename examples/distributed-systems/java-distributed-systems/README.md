# Java Distributed Systems Examples

This standalone Maven module demonstrates Stage 25 concepts with deterministic, in-memory Java simulations.

## Examples

- heartbeat failure detector with delayed heartbeats, recovery, and false-suspicion risk
- Lamport logical clock with local, send, receive, causal, and concurrent events
- educational leader election with terms and stale-term rejection
- replicated key-value store with asynchronous replication, lag, stale reads, catch-up, and promotion risk
- consistent hashing demonstration with node add/remove and modulo comparison

## Run

```bash
mvn test
```

## Limitations

These examples are teaching models. They do not implement production consensus, Raft, Paxos, distributed locks, exactly-once delivery, durable replication, secure networking, or real cluster membership.
