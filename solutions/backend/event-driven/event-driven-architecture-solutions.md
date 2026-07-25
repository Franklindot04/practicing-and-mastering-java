# Event-Driven Architecture Solutions

## 1. Events Versus Commands

`CreateTask` and `SendWelcomeEmail` are commands because they ask for work. `TaskCreated`, `UserRegistered`, and `InventoryAdjusted` are events because they describe facts that happened.

## 2. Synchronous Versus Asynchronous Communication

Task validation and saving should be synchronous because the user needs to know whether the task exists. Notification can often be asynchronous because notification failure should not usually undo task creation.

## 3. Producers And Consumers

The task API is the producer of `TaskCompleted`. Possible consumers include notification, reporting, audit, and activity feed boundaries.

## 4. Publish Subscribe

`OrderPaid` can be published once and consumed independently by fulfillment, receipt notification, reporting, and audit subscribers. Adding a subscriber increases the importance of a stable event contract.

## 5. Event Notification

`TaskUpdated { taskId }` tells consumers that the task changed. A consumer that needs details must fetch current state or read from an owned projection. The risk is that fetched state may be newer than the state at event time.

## 6. Event-Carried State Transfer

A useful `TaskCompleted` payload might include `eventId`, `version`, `occurredAt`, `taskId`, `title`, `completedAt`, `completedByUserId`, and `correlationId`. A later correction should be another event, such as `TaskReopened` or `TaskCompletionCorrected`.

## 7. Choreography And Orchestration

In choreography, payment reacts to `OrderCreated`, fulfillment reacts to `PaymentAccepted`, and notification reacts to `FulfillmentStarted`. In orchestration, a coordinator requests payment, fulfillment, and notification in order. Choreography reduces central control but can hide the whole workflow; orchestration improves visibility but can over-couple the coordinator.

## 8. Event Naming And Payloads

Replace `UpdateTaskMessage { data }` with a fact such as `TaskUpdated` or a more specific event like `TaskTitleChanged`. Use explicit fields such as `eventId`, `version`, `occurredAt`, `taskId`, `oldTitle`, `newTitle`, and `correlationId` when appropriate.

## 9. Immutability

Do not edit the original `TaskCompleted` event. Keep it as historical fact and emit a follow-up event such as `TaskReopened`.

## 10. Versioning And Schema Evolution

Add `createdByUserId` as an optional field in `TaskCreated v2`. Old consumers should ignore it. New consumers should handle old events where the field is missing.

## 11. Outbox Pattern

The task API saves the task and an outbox record in one local transaction. A later publisher reads the outbox and publishes `TaskCreated`. If marking the record as published fails after publication, the event can be sent again, so consumers still need duplicate handling.

## 12. CQRS

A write model might be `Task`, with validation and state transition rules. A read model might be `TaskListItem`, shaped for a list screen with title, status label, and display timestamps. CQRS is unnecessary if one simple model handles reads and writes clearly.

## 13. Operational Troubleshooting

Start with the task ID, find the `TaskCompleted` event and correlation ID, confirm the producer created the event, inspect notification consumer attempts, classify the failure, then retry, correct data, or fix code. A useful metric is oldest unprocessed notification event age.
