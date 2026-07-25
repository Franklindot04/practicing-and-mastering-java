# What Is Event-Driven Architecture

Event-driven architecture organizes software around events: records that something meaningful already happened.

An event usually names a business fact:

- `TaskCreated`
- `OrderPaid`
- `UserRegistered`
- `InventoryAdjusted`

The component that creates the event does not need to know every component that may care about it. This can reduce direct coupling between parts of a system.

## Traditional Direct Flow

```text
Order API
  |
  +--> Payment Service
  |
  +--> Inventory Service
  |
  +--> Email Service
```

The API knows who to call and when to call them.

## Event-Driven Flow

```text
Order API
  |
  v
OrderCreated event
  |
  +--> Payment workflow
  |
  +--> Inventory reservation
  |
  +--> Customer notification
```

The API records the fact. Interested consumers decide how to react.

## What Events Are Good For

- Informing several parts of a system about the same fact.
- Decoupling the source of a change from later reactions.
- Capturing a history of business activity.
- Moving non-critical work out of the immediate user request.

## What Events Do Not Automatically Solve

- Poor domain boundaries.
- Missing validation.
- Data ownership confusion.
- Operational visibility.
- Failure handling.

Event-driven architecture can make a good design more flexible. It can also make a confusing design harder to debug.
