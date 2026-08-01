# Partitioning Sharding And Rebalancing

Partitioning divides data or work into smaller ownership units. Sharding is partitioning commonly applied to data storage or routing.

## Partitioning Strategies

Key partitioning maps each key to an owner. Range partitioning groups adjacent key ranges. Hash partitioning spreads keys through a hash function.

Modulo hashing is simple:

```java
int owner = Math.floorMod(key.hashCode(), nodeCount);
```

It moves many keys when `nodeCount` changes. Consistent hashing reduces movement by placing nodes and keys on a ring. Virtual nodes are multiple positions per physical node, used conceptually to smooth distribution.

## Hot Partitions And Skew

A hot partition receives disproportionate traffic. Skew can come from popular users, time-based keys, poor hash choice, or one large tenant.

Symptoms include high latency on one owner while the rest of the cluster is idle.

## Ownership Routing And Cross-Partition Work

Partition ownership tells clients or routers where to send a key. Cross-partition operations require coordination, scatter-gather reads, or relaxed guarantees.

Scatter-gather queries send work to many partitions and combine results. They are flexible but can be slow and can fail if any required partition is unavailable.

## Rebalancing And Resharding

Rebalancing changes ownership to add capacity or recover from failure. Resharding changes partition boundaries or counts.

Operational risks:

- data movement competes with user traffic
- caches become cold
- replicas lag during transfer
- clients use stale routing tables
- partial movement leaves unclear ownership

## Review Questions

1. Why does modulo hashing move many keys when node count changes?
2. What is a hot partition?
3. Why can rebalancing reduce availability?
