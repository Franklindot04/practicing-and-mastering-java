# Distributed Cluster Simulator

This Stage 25 project simulates a small distributed cluster with deterministic Java domain logic. It is designed for learning, tests, and scenario review without brokers, databases, Docker, cloud services, or real network dependencies.

## Capabilities

- node registration, duplicate-registration checks, lifecycle states, failure, recovery, and membership changes
- heartbeat exchange with timeout-based suspicion and false-suspicion recovery
- educational leader election with terms and stale-term rejection
- simulated message loss and network partitions
- replicated state with asynchronous lag, stale reads, catch-up, and recovery
- deterministic event log and scenario reports

## Run

```bash
mvn test
```

## Scenarios To Explore

- healthy cluster: nodes join, heartbeats succeed, one leader is selected, state replicates
- leader failure: a leader fails, a higher term is elected, and old terms are rejected
- network partition: a minority side cannot safely act as authoritative and converges after healing
- replica lag: a replica reads stale data until catch-up
- node recovery: a failed node rejoins, synchronizes, and becomes healthy
- stale leader: a former leader returns with an old term and cannot write

## Architecture

The simulator keeps state in domain types such as `Cluster`, `ClusterNode`, `MembershipRegistry`, `FailureDetector`, `ElectionCoordinator`, `NetworkSimulator`, and `ReplicatedState`. Presentation is represented by `SimulationReport`, so tests can assert behavior without parsing console output.

## Production Comparison

Real distributed systems need durable logs, storage engines, security, backpressure, rolling upgrade compatibility, richer membership, operator controls, and heavily tested consensus protocols. This project demonstrates concepts only.

## Limitations

This project does not implement production consensus, Raft, Paxos, distributed locking, exactly-once delivery, durable storage, real networking, or cloud orchestration.
