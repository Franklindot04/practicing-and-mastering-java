# Sagas Outbox And Event Recovery

Event-driven systems need explicit recovery design. Retries, dead-letter handling, replay, sagas, and outbox or inbox records turn asynchronous failure from mystery into work that can be inspected and repaired.

## Scope

This section is broker-independent and infrastructure-light. It teaches patterns and trade-offs before later stages introduce concrete messaging technology.

## Recommended Reading Order

1. [Retries Dead Letters And Replay](retries-dead-letters-and-replay.md)
2. [Eventual Consistency Sagas And Compensation](eventual-consistency-sagas-and-compensation.md)
3. [Orchestration Choreography And Workflow State](orchestration-choreography-and-workflow-state.md)
4. [Transactional Outbox Inbox And Dual Writes](transactional-outbox-inbox-and-dual-writes.md)
5. [Event-Driven Operational Readiness](event-driven-operational-readiness.md)

## Learning Goals

After this section, you should be able to:

- classify transient and permanent failures
- design bounded retries without retry storms
- explain dead-letter queues, parking-lot queues, quarantine, replay, and manual intervention
- model eventual consistency, saga state, and compensating actions
- compare orchestration and choreography
- explain the dual-write problem and transactional outbox pattern
- use consumer inbox records for idempotency
- define event-driven metrics, runbooks, and recovery drills

## Review Questions

1. Why is compensation not the same as rollback?
2. What makes a poison event different from a transient failure?
3. Why can publishing an event after a database commit create a dual-write failure?
4. What operational signal tells you a consumer is falling behind?
5. Who owns replay verification after a repair?
