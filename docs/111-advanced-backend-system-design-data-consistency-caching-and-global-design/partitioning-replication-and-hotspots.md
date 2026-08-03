# Partitioning Replication And Hotspots

Partitioning splits data or work. Replication copies data or work. Both can improve scale and resilience, and both can create surprising failure modes.

## Partitioning Strategies

Hash partitioning spreads keys by hashing an identifier. It helps distribute load but weakens range queries.

Range partitioning groups adjacent values. It helps range scans but can create hot recent ranges, especially with time-based keys.

Consistent hashing reduces movement when nodes are added or removed. It is useful for caches and distributed stores, but it does not remove hotspot risk.

Partition keys should reflect access patterns. A strong key has good cardinality, even distribution, stable ownership, and query locality. Weak keys create hot partitions or expensive cross-partition queries.

## Hotspots

Hot partitions can come from a large tenant, popular product, celebrity account, regional event, time-based writes, or retry storm. Detect them with per-partition throughput, latency, queue depth, error rate, and saturation metrics.

Mitigations include splitting hot tenants, salting keys, write aggregation, read replicas, request coalescing, adaptive throttling, and product-level limits. Salting can break ordering and complicate queries, so it must be deliberate.

## Rebalancing

Rebalancing moves partitions or ownership. It can cause cache misses, consumer rebalances, temporary lag, duplicate processing, and uneven load. Plans should include throttling, observability, stop conditions, and rollback constraints.

## Replication

Synchronous replication waits for replicas before acknowledging writes. It can improve durability but increases latency and can reduce availability during replica trouble.

Asynchronous replication acknowledges before replicas catch up. It improves latency and availability but creates lag and possible data loss during failover.

Secondary indexes in partitioned systems may be local or global. Local indexes are cheaper for partition-local queries. Global indexes improve query flexibility but add coordination, lag, or cross-partition work.

## Global Uniqueness And Locality

Global IDs can be generated through database sequences, UUIDs, ULIDs, Snowflake-style IDs, or service-specific allocators. Consider ordering, collision risk, shard hints, privacy, and operational dependency.

Locality matters. Keeping user traffic, application instances, caches, databases, and queues near each other reduces latency and cross-region cost. Locality can conflict with global availability and data residency.
