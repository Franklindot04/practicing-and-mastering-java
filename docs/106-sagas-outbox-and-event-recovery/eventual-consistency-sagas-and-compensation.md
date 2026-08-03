# Eventual Consistency Sagas And Compensation

Event-driven workflows often cross several local transaction boundaries. Instead of one large distributed transaction, each participant commits its own local change and publishes or reacts to events.

## Local And Distributed Transactions

A local transaction updates one transactional resource, such as one database. A distributed transaction attempts to coordinate several resources as one atomic unit.

Two-phase commit is a classic distributed transaction protocol. At a high level, participants prepare, then a coordinator decides commit or rollback. It is difficult in distributed systems because participants, networks, and coordinators can fail at awkward times, and locks may be held while the system waits.

Stage 26 does not implement distributed transactions.

## Eventual Consistency

Eventual consistency means a workflow may pass through intermediate states before converging. Users and operators must be able to see those states.

```java
enum OrderStatus {
    SUBMITTED,
    INVENTORY_RESERVED,
    PAYMENT_PENDING,
    CONFIRMED,
    CANCELLED
}
```

`PAYMENT_PENDING` is not a bug if the business process intentionally waits for asynchronous authorization.

## Sagas

A saga is a sequence of local transactions connected by messages or events. Saga state records which steps completed, which step is next, and what recovery action is needed.

```java
record SagaState(String orderId, OrderStatus status, int version, boolean compensationStarted) {}
```

Sagas protect business invariants through forward progress and compensation, not through one global rollback.

## Compensation Is Not Rollback

Compensation is a new business action that offsets an earlier completed action. If inventory was reserved and payment later fails, releasing inventory is compensation.

Rollback pretends the earlier action never happened. Compensation acknowledges that it did happen and records the correction.

Some actions are irreversible. An email cannot be unsent. A shipment may not be recallable. Money movement may require refund rather than deletion. Design sagas around real business reversibility.

## Compensation Failure

Compensation can fail too. A saga needs state for compensation attempts, retry policy, escalation, and manual intervention. Failure during compensation is an operational event, not a reason to erase history.

## Business Invariants And Convergence

Define invariants before designing events:

- an order cannot be confirmed without payment authorization
- inventory should not remain reserved for cancelled orders
- a customer should see pending states honestly
- timeout handling should move stuck workflows to a visible state

Convergence means all participants eventually agree on the intended outcome or expose a repairable exception.
