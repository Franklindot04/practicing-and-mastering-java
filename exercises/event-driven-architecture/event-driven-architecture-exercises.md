# Event-Driven Architecture Exercises

## 1. Classify Message Semantics

Objective: Distinguish events, commands, queries, requests, and notifications.

Scenario: A checkout system uses messages named `SubmitOrder`, `OrderSubmitted`, `GetOrderStatus`, `SendReceiptEmail`, and `ReceiptEmailSent`.

Requirements: Classify each message, explain the key semantic clue, and identify which messages can be rejected or answered.

Constraints: Do not classify only by transport. Use business meaning.

Expected reasoning: Facts are past tense, commands express intent, queries ask for data, and delivery tasks are instructions.

Acceptance criteria: Every message has one classification and a short justification.

Optional extension: Propose a clearer name for any ambiguous message.

## 2. Rename Ambiguous Messages

Objective: Improve event names that hide business meaning.

Scenario: A service publishes `OrderUpdatedEvent`, `PaymentProcessedEvent`, and `InventoryEvent`.

Requirements: Rename each into precise facts or commands and explain why the original name is risky.

Constraints: Avoid generic verbs such as updated, processed, or handled.

Expected reasoning: Ambiguous events couple consumers to private producer behavior.

Acceptance criteria: Each replacement makes fact versus intent clear.

## 3. Identify Fact Versus Intent

Objective: Separate immutable facts from requested work.

Scenario: A producer emits `ReserveInventoryEvent` after receiving an order.

Requirements: Decide whether the name describes a fact or intent, then model a command and the resulting event.

Constraints: The resulting event must be past tense.

Expected reasoning: Reserving inventory can fail, so the request and result should be separate.

Acceptance criteria: Answer includes one command and at least one success or failure event.

## 4. Model Domain And Integration Events

Objective: Decide which events should remain internal and which can cross service boundaries.

Scenario: An order module records line-item edits, tax recalculation, order submission, and fraud review completion.

Requirements: Identify domain events and integration events, then explain ownership and stability expectations.

Constraints: Do not publish private implementation details as external contracts.

Expected reasoning: Integration events should expose stable business facts useful outside the boundary.

Acceptance criteria: The answer lists at least two internal-only events and two candidate integration events.

## 5. Design An Event Envelope

Objective: Create a typed event envelope.

Scenario: `OrderSubmitted` must be consumed by inventory, payment, analytics, and notification components.

Requirements: Define a Java `record` envelope with event ID, type, version, source, timestamp, correlation ID, causation ID, tenant key, partition key, payload, and metadata.

Constraints: Use typed records instead of `Map<String, Object>` payloads.

Expected reasoning: Operational fields belong in the envelope, while business facts belong in the payload.

Acceptance criteria: The envelope validates required fields and keeps metadata separate from payload meaning.

## 6. Propagate Correlation And Causation

Objective: Trace event flow across a workflow.

Scenario: `OrderSubmitted` causes `InventoryReserved`, which causes `PaymentAuthorizationRequested`.

Requirements: Show how correlation and causation IDs change or stay the same across the three events.

Constraints: Use one correlation ID for the workflow.

Expected reasoning: Correlation groups the workflow; causation points to the immediate triggering message.

Acceptance criteria: The answer includes a small table of event ID, correlation ID, and causation ID.

## 7. Identify Schema-Breaking Changes

Objective: Recognize unsafe event evolution.

Scenario: `OrderSubmittedV1(orderId, totalCents, currency)` changes by renaming `totalCents`, removing `currency`, adding `salesChannel`, and changing total from pre-tax to post-tax.

Requirements: Classify each change as safe, risky, or breaking.

Constraints: Include semantic compatibility, not only field shape.

Expected reasoning: Additive optional fields are usually safer than removals, renames, or meaning changes.

Acceptance criteria: Each change has a compatibility classification and reason.

## 8. Design Backward-Compatible Evolution

Objective: Add a new event field safely.

Scenario: Consumers need `salesChannel` on `OrderSubmitted`.

Requirements: Propose a rollout plan using optional fields, defaults, tolerant consumers, and contract tests.

Constraints: Assume old and new producers run during a rolling deployment.

Expected reasoning: Consumers should tolerate the new shape before producers require it.

Acceptance criteria: The plan includes producer and consumer deployment order.

## 9. Compare Delivery Semantics

Objective: Explain at-most-once, at-least-once, and scoped exactly-once claims.

Scenario: A notification consumer crashes after sending email but before acknowledgement.

Requirements: Explain what each delivery semantic could mean for this event.

Constraints: Do not claim external email is exactly once.

Expected reasoning: Acknowledgement timing creates either loss risk or duplicate risk.

Acceptance criteria: The answer names the safest consumer-side protection.

## 10. Analyze Duplicate Delivery

Objective: Design duplicate-safe side effects.

Scenario: `PaymentAuthorizationRequested` is delivered twice.

Requirements: Choose an idempotency key and explain where processed-event tracking should live.

Constraints: The key must represent the logical payment request.

Expected reasoning: Random retry IDs do not protect the business side effect.

Acceptance criteria: The design prevents duplicate authorization attempts.

## 11. Implement An Idempotent Consumer

Objective: Write broker-independent Java idempotency logic.

Scenario: A consumer updates a read model from `OrderConfirmed`.

Requirements: Implement a small `ProcessedEventStore` and consumer method that applies the side effect once.

Constraints: Do not use sleeping, threads, or external storage.

Expected reasoning: Marking and side effect should be atomic in production; the exercise can simulate the concept.

Acceptance criteria: Duplicate events do not duplicate the side effect.

## 12. Choose Acknowledgement Timing

Objective: Reason about acknowledgement trade-offs.

Scenario: A consumer can acknowledge before processing or after writing to its local store.

Requirements: Compare both choices for loss, duplicate delivery, and recovery.

Constraints: Include crash-after-side-effect and crash-before-side-effect cases.

Expected reasoning: No timing removes the need for idempotency.

Acceptance criteria: The recommendation states the remaining risk.

## 13. Classify Failure Types

Objective: Separate transient and permanent failures.

Scenario: A consumer sees a timeout, malformed payload, unknown customer ID, rate limit, and unsupported event version.

Requirements: Classify each and state whether retry is eligible.

Constraints: Explain any ambiguity.

Expected reasoning: Retry only helps when the failure may stop being true.

Acceptance criteria: Each failure includes retry guidance.

## 14. Design Retry Policy

Objective: Create a bounded retry strategy.

Scenario: Payment authorization times out intermittently.

Requirements: Define max attempts, backoff, jitter, retry budget, and stop condition.

Constraints: Avoid infinite retry and retry storms.

Expected reasoning: Retry protects transient failures but can amplify outages.

Acceptance criteria: The policy includes when to dead-letter.

## 15. Design Dead-Letter Handling

Objective: Preserve failed events for repair.

Scenario: `OrderSubmitted` fails because a required field is missing.

Requirements: Define dead-letter record fields, owner, investigation steps, and replay eligibility.

Constraints: Do not discard the original envelope.

Expected reasoning: Operators need enough context to repair safely.

Acceptance criteria: The plan includes audit and recovery verification.

## 16. Reason About Replay Safety

Objective: Identify unsafe side effects during replay.

Scenario: A consumer sends emails and updates a projection during replay.

Requirements: Decide which side effects should run, be skipped, or be guarded.

Constraints: Assume events may be old and duplicated.

Expected reasoning: Rebuilding state differs from repeating external side effects.

Acceptance criteria: The answer includes a replay mode strategy.

## 17. Handle Out-Of-Order Events

Objective: Protect state from stale and future events.

Scenario: `PaymentAuthorized(version=4)` arrives before `InventoryReserved(version=2)`.

Requirements: Use aggregate version or sequence number logic to apply, reject, or defer events.

Constraints: Do not silently overwrite newer state.

Expected reasoning: Missing versions should be visible.

Acceptance criteria: The design converges when missing events arrive.

## 18. Choose A Partition Key

Objective: Balance ordering and throughput.

Scenario: Order events include `tenantId`, `customerId`, `orderId`, and `productId`.

Requirements: Choose a partition key for order workflow processing and explain trade-offs.

Constraints: Include per-order ordering and hot-key risk.

Expected reasoning: The key should match the ordering invariant.

Acceptance criteria: The answer identifies at least one risky alternative.

## 19. Identify Hot-Key Risk

Objective: Spot uneven partition load.

Scenario: One tenant produces 70 percent of events.

Requirements: Explain why `tenantId` may be a hot key and propose mitigation.

Constraints: Preserve business ordering where needed.

Expected reasoning: Partitioning by broad keys can limit concurrency.

Acceptance criteria: The mitigation states which ordering guarantee changes or remains.

## 20. Compare Orchestration And Choreography

Objective: Choose a saga coordination style.

Scenario: Checkout requires inventory, payment, confirmation, and notification.

Requirements: Compare orchestration and choreography for visibility, coupling, failure handling, and participant autonomy.

Constraints: Do not declare one universally better.

Expected reasoning: The right model depends on workflow ownership and recovery needs.

Acceptance criteria: The recommendation includes trade-offs.

## 21. Model Saga State

Objective: Represent workflow progress explicitly.

Scenario: An order is submitted, inventory is reserved, payment is pending, then payment times out.

Requirements: Define saga state fields and transitions.

Constraints: Include timeout and recovery information.

Expected reasoning: A saga needs enough state to answer where the workflow is stuck.

Acceptance criteria: The model includes current status, completed steps, retry/deadline data, and correlation ID.

## 22. Define Compensation

Objective: Distinguish compensation from rollback.

Scenario: Inventory was reserved but payment was rejected.

Requirements: Define the compensating action and explain why it is not a rollback.

Constraints: Include failure of the compensating action.

Expected reasoning: Compensation is a new business fact.

Acceptance criteria: The answer records both original and compensating events.

## 23. Identify Irreversible Actions

Objective: Design around actions that cannot be undone.

Scenario: The system sends an email, ships a package, and captures payment.

Requirements: Identify which actions are irreversible or require business reversal.

Constraints: Do not delete history to hide an action.

Expected reasoning: Some actions require apology, refund, recall, or support workflow.

Acceptance criteria: Each action has a recovery strategy.

## 24. Analyze Dual-Write Failure

Objective: Explain the outbox motivation.

Scenario: Code saves an order, then publishes `OrderSubmitted`. Publication fails after the save commits.

Requirements: Explain the inconsistency and propose transactional outbox records.

Constraints: No distributed transaction.

Expected reasoning: Local state and event publication need a recoverable bridge.

Acceptance criteria: The answer includes relay retry and duplicate publication safety.

## 25. Design Consumer Inbox

Objective: Prevent duplicate consumer side effects.

Scenario: A shipping consumer receives `OrderConfirmed` more than once.

Requirements: Define inbox fields and processing flow.

Constraints: Explain atomicity requirements.

Expected reasoning: The inbox should be close to the side effect it protects.

Acceptance criteria: Duplicate event IDs are ignored safely.

## 26. Define Event Metrics

Objective: Make asynchronous workflows observable.

Scenario: Orders are stuck in pending payment.

Requirements: Define technical and business metrics.

Constraints: Include lag, event age, retry count, dead-letter volume, and business outcome counts.

Expected reasoning: Technical movement does not guarantee business success.

Acceptance criteria: Metrics can identify where the workflow is stuck.

## 27. Design An Event-Flow Runbook

Objective: Prepare operational recovery steps.

Scenario: Dead-letter volume spikes for `PaymentAuthorizationRequested`.

Requirements: Write runbook steps for detection, triage, ownership, repair, replay, and verification.

Constraints: Include correlation and causation tracing.

Expected reasoning: Recovery needs both technical and business checks.

Acceptance criteria: The runbook identifies who decides replay eligibility.

## 28. Analyze Safe Rolling Schema Deployment

Objective: Plan a compatible schema rollout.

Scenario: `OrderSubmitted` adds required `currency`.

Requirements: Convert the change into a safe rollout with optional field, defaulting, consumer tolerance, producer update, and final enforcement.

Constraints: Old events may still be replayed.

Expected reasoning: Required fields should become required only after consumers and historical data are safe.

Acceptance criteria: The plan avoids breaking mixed-version deployment and replay.
