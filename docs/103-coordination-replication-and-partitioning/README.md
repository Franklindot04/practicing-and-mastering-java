# Coordination Replication And Partitioning

Distributed systems need ways to know which nodes exist, which nodes are healthy enough to use, who owns a decision, where data lives, and how replicas recover. This section introduces those ideas before later roadmap stages discuss event-driven architecture and messaging platforms.

## Scope

These notes are conceptual and Java-oriented. They do not implement Raft, Paxos, production consensus, distributed locks, or exactly-once guarantees.

## Recommended Reading Order

1. [Membership Failure Detection And Partitions](membership-failure-detection-and-partitions.md)
2. [Coordination Leader Election And Fencing](coordination-leader-election-and-fencing.md)
3. [Replication Lag Failover And Recovery](replication-lag-failover-and-recovery.md)
4. [Partitioning Sharding And Rebalancing](partitioning-sharding-and-rebalancing.md)
5. [Operational Readiness For Distributed Systems](operational-readiness-for-distributed-systems.md)

## Review Questions

1. Why is failure detection an imperfect signal?
2. What problem do leadership terms and fencing tokens reduce?
3. Why can asynchronous replicas return stale reads?
4. Why can rebalancing reduce availability or increase latency?
