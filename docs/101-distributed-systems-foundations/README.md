# Distributed Systems Foundations

A distributed system is a set of independent processes that cooperate over a network to provide one user-facing capability. In Java, that often means several JVMs, services, workers, schedulers, caches, or data stores whose combined behavior matters more than any one process.

The central shift from a single JVM to a distributed system is that failure becomes partial. One process can continue while another is down, delayed, partitioned, overloaded, or acting on stale information. Stage 25 starts with those foundations before later stages add event-driven architecture, brokers, Kafka, RabbitMQ, or streaming platforms.

## Scope

This section is framework-light and infrastructure-independent. Examples use Java-oriented vocabulary, but the principles apply whether the code later runs in a command-line tool, Spring service, batch job, serverless function, or message consumer.

These notes do not claim to implement consensus, distributed locking, exactly-once delivery, or production-grade coordination. They teach the failure models and trade-offs that make those topics difficult.

## Recommended Reading Order

1. [Distributed Systems And Partial Failure](distributed-systems-and-partial-failure.md)
2. [Networks Latency And Message Delivery](networks-latency-and-message-delivery.md)
3. [State Fault Domains And Scaling](state-fault-domains-and-scaling.md)
4. [Distributed Systems Operational Complexity](distributed-systems-operational-complexity.md)

## Learning Goals

After this section, you should be able to:

- explain why local Java guarantees do not automatically become distributed guarantees
- identify partial failures, unreliable-network symptoms, and retry amplification risks
- distinguish latency, throughput, message loss, duplication, and reordering
- reason about state ownership, fault domains, horizontal scaling, and coordination cost
- describe why operational readiness must include explicit failure models

## Review Questions

1. Why can a client timeout while the server still commits the requested operation?
2. What can go wrong when a retry is issued after an unknown outcome?
3. Why does horizontal scaling often add coordination cost?
4. Which guarantee is local to one JVM but difficult to preserve across several JVMs?
5. Why is "the network is reliable" a dangerous assumption?
