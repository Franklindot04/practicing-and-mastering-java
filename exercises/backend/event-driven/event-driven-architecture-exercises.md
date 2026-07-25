# Event-Driven Architecture Exercises

## 1. Events Versus Commands

Difficulty: Beginner

Concepts: events, commands, naming

Problem: Classify these messages as events or commands: `CreateTask`, `TaskCreated`, `SendWelcomeEmail`, `UserRegistered`, `InventoryAdjusted`.

Hints:

- Events describe facts that already happened.
- Commands ask something to do work.

Stretch challenge: Rename any weak message names so their intent is clearer.

## 2. Synchronous Versus Asynchronous Communication

Difficulty: Beginner

Concepts: request/response, asynchronous reactions

Problem: A task API creates a task and sends a notification. Decide which work must be synchronous and which work could happen asynchronously.

Hints:

- Ask what the user needs before receiving a response.
- Ask which failure should block task creation.

Stretch challenge: Draw both versions as ASCII diagrams.

## 3. Producers And Consumers

Difficulty: Beginner

Concepts: producers, consumers, ownership

Problem: For `TaskCompleted`, identify the producer and at least three possible consumers.

Hints:

- The producer owns the fact.
- Consumers own their local reaction.

Stretch challenge: For each consumer, decide whether failure should alert immediately.

## 4. Publish Subscribe

Difficulty: Intermediate

Concepts: publish/subscribe, decoupling

Problem: Design a publish/subscribe flow for `OrderPaid` with three independent subscribers.

Hints:

- The producer should not call each subscriber directly.
- Each subscriber can have a different reason to care.

Stretch challenge: Explain how adding a fourth subscriber affects event contract risk.

## 5. Event Notification

Difficulty: Intermediate

Concepts: notification events, current state lookup

Problem: Design an event notification for `TaskUpdated` that only carries the task ID. Explain what a consumer must do next.

Hints:

- Event notification tells consumers that something changed.
- Consumers may need to fetch current state.

Stretch challenge: Describe one stale-data risk in this approach.

## 6. Event-Carried State Transfer

Difficulty: Intermediate

Concepts: event payloads, read models

Problem: Design an event-carried state transfer payload for `TaskCompleted`.

Hints:

- Include enough fields for a reporting consumer.
- Avoid secrets and unnecessary data.

Stretch challenge: Explain how a correction would be represented later.

## 7. Choreography And Orchestration

Difficulty: Intermediate

Concepts: workflows, choreography, orchestration

Problem: Compare choreography and orchestration for an order workflow with payment, fulfillment, and notification steps.

Hints:

- Choreography spreads workflow decisions across participants.
- Orchestration uses a coordinator.

Stretch challenge: Choose one style and explain the operational visibility tradeoff.

## 8. Event Naming And Payloads

Difficulty: Intermediate

Concepts: naming, payload design

Problem: Improve this event: `UpdateTaskMessage { data }`.

Hints:

- Use past tense.
- Name the business fact.
- Replace vague fields with explicit fields.

Stretch challenge: Add event metadata that helps tracing.

## 9. Immutability

Difficulty: Beginner

Concepts: immutable events, history

Problem: A task was completed by mistake and reopened five minutes later. Should the original `TaskCompleted` event be edited? Explain.

Hints:

- Events represent history.
- Corrections should usually add new facts.

Stretch challenge: Name the follow-up event.

## 10. Versioning And Schema Evolution

Difficulty: Intermediate

Concepts: versioning, backward compatibility, forward compatibility

Problem: `TaskCreated v1` has `taskId` and `title`. A new consumer wants `createdByUserId`. Propose a compatible change.

Hints:

- Adding an optional field is usually safer than renaming a field.
- Old consumers should ignore unknown fields.

Stretch challenge: Explain how a new consumer can handle old events.

## 11. Outbox Pattern

Difficulty: Advanced

Concepts: dual-write problem, outbox

Problem: A task API saves a task and publishes `TaskCreated`. Explain how the outbox pattern changes this flow.

Hints:

- Save business state and event record together.
- Publish later from the durable event record.

Stretch challenge: Explain why consumers still need duplicate handling.

## 12. CQRS

Difficulty: Intermediate

Concepts: commands, queries, read models, write models

Problem: For a task API, describe one write model and one read model.

Hints:

- The write model protects business rules.
- The read model serves display or reporting needs.

Stretch challenge: Explain when CQRS would be unnecessary for this API.

## 13. Operational Troubleshooting

Difficulty: Advanced

Concepts: correlation IDs, poison events, failure investigation

Problem: A user reports that a task completion notification never arrived. Write an investigation workflow.

Hints:

- Start with the affected task ID.
- Find the event ID and correlation ID.
- Check producer and consumer outcomes separately.

Stretch challenge: Identify one metric that would have detected the issue earlier.
