# Outbox And CQRS Introduction

Outbox and CQRS are patterns often discussed near event-driven architecture because they help teams reason about consistency, publishing, reads, and writes.

This section is conceptual only. It does not create runnable messaging infrastructure, background workers, brokers, databases, or application code.

## Topics

- [Transactional Messaging Problem](transactional-messaging-problem.md)
- [Dual-Write Problem](dual-write-problem.md)
- [Outbox Pattern](outbox-pattern.md)
- [Outbox Publishing Flow](outbox-publishing-flow.md)
- [CQRS Introduction](cqrs-introduction.md)
- [Read Models And Write Models](read-models-and-write-models.md)
- [Benefits Tradeoffs And When Not To Use](benefits-tradeoffs-when-not-to-use.md)

## Learning Goals

After this section, you should be able to:

- Explain why saving state and publishing an event can fail in different ways.
- Describe the outbox pattern at a high level.
- Explain CQRS without turning every application into a distributed system.
- Distinguish write models from read models.
- Identify when these patterns add useful safety and when they add premature complexity.

## Big Idea

Reliable event-driven design is not only about sending events. It is also about deciding how state changes, event records, reads, and failures relate to each other.
