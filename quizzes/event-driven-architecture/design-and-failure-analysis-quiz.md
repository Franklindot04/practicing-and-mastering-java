# Event-Driven Architecture Design And Failure Analysis Quiz

## 1. Envelope Design Scenario

Design an envelope for `OrderSubmitted` consumed by inventory, payment, analytics, and notifications. Include fields for identity, versioning, source, time, correlation, causation, tenant, partitioning, payload, and metadata.

## 2. Schema Rollout Scenario

`OrderSubmitted` needs a new `currency` field. Old events may be replayed and old consumers may run during a rolling deployment. Propose a safe rollout.

## 3. Duplicate Payment Scenario

`PaymentAuthorizationRequested` is delivered twice after a consumer acknowledgement timeout. Explain how to avoid duplicate payment authorization.

## 4. Out-Of-Order Scenario

`OrderConfirmed(version=5)` arrives before `PaymentAuthorized(version=4)`. Decide whether to apply, reject, or defer it and explain why.

## 5. Retry And Dead-Letter Scenario

A payment consumer times out twice, then fails with an unsupported event version. Design retry and dead-letter behavior.

## 6. Saga Compensation Scenario

Inventory is reserved, payment is rejected, and inventory release fails. Describe the saga state and recovery path.

## 7. Orchestration Versus Choreography Scenario

Checkout requires inventory, payment, order confirmation, and notification. Compare orchestration and choreography for this workflow and recommend one.

## 8. Outbox Failure Scenario

An order save commits, but event publication fails. Explain how an outbox relay recovers and why duplicate publication is still possible.

## 9. Consumer Inbox Scenario

A shipping consumer receives `OrderConfirmed` twice. Design an inbox record and processing flow.

## 10. Observability Scenario

Orders are stuck in `PAYMENT_PENDING`. List the event metrics, business metrics, and trace fields you would inspect first.
