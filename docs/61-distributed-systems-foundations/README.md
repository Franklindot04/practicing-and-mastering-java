# Distributed Systems Foundations

Distributed systems are programs split across multiple processes, machines, networks, or locations that must cooperate to deliver one user-visible capability.

This section is conceptual. It does not ask you to build a cluster, deploy infrastructure, or install messaging tools. The goal is to learn how to reason about systems where a request may cross process and network boundaries.

## Topics

- [What Is A Distributed System](what-is-a-distributed-system.md)
- [Network Latency And Failures](network-latency-and-failures.md)
- [Distributed System Tradeoffs](distributed-system-tradeoffs.md)
- [Common Misconceptions](common-misconceptions.md)

## Learning Goals

After this section, you should be able to:

- Explain why multiple services introduce network and coordination problems.
- Compare a monolith with a distributed design without treating either as automatically better.
- Describe latency, timeouts, retries, partial failures, and backpressure at a high level.
- Recognize common misconceptions before choosing distributed architecture.

## Big Idea

A distributed system is not just a bigger application. It is an application where distance, delay, independent failure, and unclear ownership become part of the design.

```text
Client
  |
  v
Service A ----network----> Service B ----network----> Database
  |                            |
  |                            v
  +------ timeout? retry? partial failure? duplicate request?
```

The same business request may succeed in one place, fail in another, and leave the whole system in a state that needs careful handling.

