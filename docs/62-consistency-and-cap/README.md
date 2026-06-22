# Consistency And CAP

Distributed systems often keep data in more than one place. Once data is copied, cached, replicated, or processed asynchronously, teams must decide what "correct" means when parts of the system disagree.

This section explains consistency and CAP theorem as design tools, not as slogans.

## Topics

- [CAP Theorem](cap-theorem.md)
- [Consistency Models](consistency-models.md)
- [Availability Tradeoffs](availability-tradeoffs.md)

## Learning Goals

After this section, you should be able to:

- Explain consistency, availability, and partition tolerance in plain language.
- Describe why network partitions force tradeoffs.
- Compare strong consistency, eventual consistency, and read-after-write consistency.
- Choose consistency expectations based on user experience and business risk.

## Simple Mental Model

```text
Client writes value X
        |
        v
Primary copy ----replication delay----> Secondary copy

Question: what should another client read during the delay?
```

There is no single correct answer for every system. The answer depends on the operation.

