# Event-Driven Architecture Multiple Choice Quiz

## 1. What best describes an event?

A. A request for a specific service to do work
B. An immutable fact that something happened
C. A query that returns the latest state
D. A retry instruction for a failed consumer

## 2. Which name is the clearest event name?

A. `ProcessPaymentEvent`
B. `PaymentAuthorized`
C. `DoPayment`
D. `PaymentCommand`

## 3. What does temporal decoupling mean?

A. Producer and consumer do not need to run at the same time
B. Producer and consumer use the same classpath
C. Events are globally ordered by timestamp
D. Consumers cannot fail after acknowledgement

## 4. Which envelope field groups a workflow across services?

A. Event version
B. Correlation ID
C. Partition key
D. Tenant key

## 5. Which envelope field identifies the immediate cause of an event?

A. Causation ID
B. Source
C. Payload
D. Metadata

## 6. Which schema change is usually safest?

A. Remove a required field
B. Rename a field in place
C. Add an optional field with a default
D. Change field meaning without changing the contract

## 7. At-least-once delivery requires consumers to handle what?

A. Duplicate delivery
B. Perfect global order
C. Durable local memory
D. Real distributed transactions

## 8. Acknowledging after processing mainly risks what?

A. Duplicate delivery after side effect succeeds
B. Guaranteed message loss before processing
C. No need for idempotency
D. Synchronous blocking of every producer

## 9. Which partition key is usually best for per-order workflow ordering?

A. Random UUID per delivery attempt
B. Order ID
C. Event type only
D. Wall-clock minute

## 10. What is a hot key?

A. A partition key that receives disproportionate traffic
B. A cryptographic secret in metadata
C. A schema registry entry
D. A command that cannot be rejected

## 11. What is compensation?

A. Deleting all evidence of an earlier step
B. A new business action that offsets a completed action
C. A broker guarantee
D. A query retry

## 12. What problem does the transactional outbox reduce?

A. Dual-write failure between local state change and event publication
B. Consumer UI styling
C. Field naming ambiguity
D. Global event ordering

## 13. Why does a consumer inbox exist?

A. To track processed events and avoid duplicate side effects
B. To replace event contracts
C. To force synchronous request-response
D. To remove the need for schema evolution

## 14. Which metric directly indicates failed event handling volume?

A. Dead-letter count
B. CPU model
C. Repository size
D. Number of Java records

## 15. Which statement is safest?

A. An in-memory event bus is a durable broker
B. External side effects are automatically exactly once
C. Stage 26 examples are educational simulations
D. Schema changes do not need rollout planning
