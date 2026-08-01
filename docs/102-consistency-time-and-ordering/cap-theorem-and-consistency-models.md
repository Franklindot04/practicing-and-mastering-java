# CAP Theorem And Consistency Models

CAP describes a choice during a network partition: a distributed data system cannot simultaneously provide strict consistency and full availability for operations that require communication across the partition.

It does not say "pick two forever." In normal operation, many systems provide useful consistency and availability. CAP matters when communication fails.

## Partition Tolerance Is Normally Unavoidable

Network partitions are not exotic. They can be caused by routing problems, firewall changes, overloaded hosts, DNS failures, packet loss, or process pauses that look like network delay. If the system spans more than one process, it must decide what to do when peers cannot communicate.

## Consistency Models

Strong consistency means a read observes the latest successful write according to a single agreed order. It is simple for users but can require coordination before responding.

Eventual consistency means replicas converge when new writes stop and communication resumes. It can keep more of the system available, but reads may be stale.

Read-your-writes means a user can read their own completed write. Monotonic reads mean a user does not move backward from a newer value to an older value. Causal consistency preserves cause-and-effect relationships. These models sit between strict global ordering and loose convergence.

## Misunderstandings

CAP is not a reason to ignore correctness. It is a way to name trade-offs. A product catalog may return stale data during a partition. A medication-ordering system may reject ambiguous updates.

Consistency is not durability. A write can be durable on one replica but not yet consistently visible everywhere. A write can also be consistently visible in memory but not durable after a crash.

## Java Example

```java
record VersionedValue(String value, long version) { }

boolean canReplace(VersionedValue current, VersionedValue update) {
    return update.version() > current.version();
}
```

Version checks help avoid overwriting newer state with older state. They do not by themselves provide strong distributed consistency; they are one application-level guard.

## Review Questions

1. Why is partition tolerance usually not optional?
2. How is eventual consistency different from "anything goes"?
3. Why is durability not the same as consistency?
