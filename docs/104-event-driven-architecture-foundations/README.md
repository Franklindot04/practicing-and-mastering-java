# Event-Driven Architecture Foundations

Event-driven architecture is a way to design systems around facts that happened and the reactions that follow. A Java service publishes an event when meaningful state changes, and other components decide independently whether and how to react.

Stage 26 stays broker-independent. The examples use Java records, sealed interfaces, and deterministic in-memory models to teach design reasoning before Stage 27 introduces messaging and streaming technology.

## Scope

These notes explain event-driven thinking, not a production messaging platform. An in-memory event bus in this stage is a teaching model. It does not provide durable messaging, exactly-once delivery, distributed transactions, durable event storage, or real network guarantees.

## Recommended Reading Order

1. [Event-Driven Architecture And Decoupling](event-driven-architecture-and-decoupling.md)
2. [Events Commands Queries And Notifications](events-commands-queries-and-notifications.md)
3. [Domain Events Integration Events And Ownership](domain-events-integration-events-and-ownership.md)
4. [Event-Driven Tradeoffs And Operational Complexity](event-driven-tradeoffs-and-operational-complexity.md)

## Learning Goals

After this section, you should be able to:

- distinguish producers, consumers, channels, and asynchronous communication
- explain temporal and spatial decoupling
- compare request-response workflows with event-driven workflows
- classify events, commands, queries, requests, and notifications
- separate domain events from integration events
- explain why event ownership and consumer autonomy matter
- describe benefits, trade-offs, eventual consistency, and operational complexity
- recognize why "sending messages" is not enough to create a good event-driven architecture

## Review Questions

1. Why is an event usually named in the past tense?
2. What does temporal decoupling change about producer and consumer availability?
3. Why can event-carried state transfer reduce follow-up queries?
4. When is event sourcing a distinct pattern rather than ordinary event publishing?
5. Why can a command-style event create hidden coupling?
