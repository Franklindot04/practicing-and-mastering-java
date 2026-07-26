# Consistency Replication And Partitioning

Distributed data design is mostly about deciding where copies exist, how quickly they agree, and how work is divided.

## Consistency Models

Consistency describes what readers can expect after writes.

| Model | Meaning | Example Fit |
| --- | --- | --- |
| Strong consistency | Reads reflect committed writes according to the chosen boundary | Payment status after confirmation |
| Eventual consistency | Copies converge later | Search index or analytics summary |
| Read-your-writes | A user sees their own recent write | Profile update confirmation |

Eventual consistency is not automatically acceptable. It depends on user expectations and business risk.

## Replication

Replication keeps copies of data.

```text
Primary Write Store
  |
  +--> Read Replica A
  +--> Read Replica B
```

Benefits:

- Read scale.
- Fault recovery options.
- Geographic proximity in some designs.

Risks:

- Replication lag.
- Stale reads.
- Failover complexity.
- Confusing read-after-write behavior.

## Partitioning And Sharding

Partitioning divides data into pieces. Sharding usually means distributing those pieces across storage nodes.

```text
order_id hash
  |
  +--> shard 0
  +--> shard 1
  +--> shard 2
```

Benefits:

- Larger data volume.
- More write capacity.
- Smaller per-node working set.

Risks:

- Cross-partition queries are harder.
- Rebalancing can be expensive.
- Hot partitions can dominate load.
- Operational complexity increases.

## Hot Partitions

A hot partition receives too much traffic.

Examples:

- One celebrity account receives most reads.
- One tenant sends most writes.
- A time-based key sends all current writes to the newest partition.

Mitigations may include better partition keys, adding randomness, per-tenant limits, caching, or splitting hot keys. Each mitigation has tradeoffs.

## Read Replicas

Read replicas can reduce primary database load.

Ask:

- Which queries can tolerate stale reads?
- How will the application avoid stale reads after writes?
- How will replica health be monitored?
- What happens during replication lag?

## Dual Writes And Change Data Capture

A dual write updates two systems from one application flow.

```text
Application
  |
  +--> Database
  +--> Message Broker
```

If the first write succeeds and the second fails, the systems disagree. Patterns such as outbox or change data capture can reduce this risk, but they add operational responsibilities.

Change data capture observes committed changes and publishes them elsewhere. It is useful for read models, search indexes, or integration streams when the team understands ordering, replay, and failure behavior.
