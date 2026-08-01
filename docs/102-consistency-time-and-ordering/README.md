# Consistency Time And Ordering

Distributed systems force Java developers to separate "the code executed" from "everyone agrees on the same state at the same time." This section covers consistency models, CAP, quorum concepts, physical clocks, logical clocks, event ordering, and identifiers.

## Scope

The examples use `Instant`, `Clock`, `Duration`, `UUID`, and simple counters. They are educational models, not database internals or production consensus protocols.

## Recommended Reading Order

1. [CAP Theorem And Consistency Models](cap-theorem-and-consistency-models.md)
2. [Quorums Stale Reads And Business Tradeoffs](quorums-stale-reads-and-business-tradeoffs.md)
3. [Physical Time Clock Skew And Ordering](physical-time-clock-skew-and-ordering.md)
4. [Logical Clocks Causality And Identifiers](logical-clocks-causality-and-identifiers.md)

## Review Questions

1. What does CAP say during a network partition?
2. Why can stale data be acceptable for one feature and dangerous for another?
3. Why should elapsed time use a monotonic source rather than wall-clock timestamps?
4. What can a Lamport counter prove, and what can it not prove?
