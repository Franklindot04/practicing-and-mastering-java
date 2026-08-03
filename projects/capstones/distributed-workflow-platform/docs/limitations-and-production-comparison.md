# Limitations and Production Comparison

This project demonstrates scheduling and recovery reasoning. It does not provide real durability, distributed consensus, worker isolation, broker operations, multi-node failover, deployment evidence, or load-test evidence.

## What The Capstone Proves

- Task and workflow state transitions can be represented explicitly.
- Lease expiration and reassignment can be tested without sleeping.
- Idempotency keys can suppress duplicate submissions and completions.
- Quarantine can prevent repeated poison work.
- Partition pressure and admission rejection can be surfaced as operational signals.

## What It Does Not Prove

- Durability across process loss.
- Correctness under network partitions.
- Consensus or leader election safety.
- Worker sandboxing or tenant isolation.
- Throughput under sustained load.
- Security of a deployed worker fleet.

## Production Requirements

A deployed platform would need durable state storage, transactional updates, worker authentication, lease renewal, heartbeat ingestion, replay tooling, queue or log infrastructure, schema evolution, operational dashboards, alert rules, data retention, and security review.

## Future Improvements

Future maintenance could add richer task types, persistent fixtures, static architecture tests, replay decision records, and more detailed runbooks. Those additions would improve learning value, but still would not create production evidence without real deployment and operations.
