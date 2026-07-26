# Distributed Workflows And Migration Patterns

Distributed workflows coordinate work across boundaries. They require careful failure handling because one local transaction cannot protect every step.

## Orchestration

Orchestration uses a central coordinator.

```text
Order Coordinator
  |
  +--> Reserve Inventory
  +--> Authorize Payment
  +--> Send Notification
```

Benefits:

- Clear workflow visibility.
- Central place for retries and compensation.

Risks:

- Coordinator can become complex.
- Teams may over-centralize decisions.

## Choreography

Choreography lets services react to events.

```text
Order Created -> Inventory reacts
Order Created -> Payment reacts
Payment Done  -> Notification reacts
```

Benefits:

- Looser coupling.
- Services can evolve independently around events.

Risks:

- Workflow can be harder to see.
- Debugging requires strong observability.
- Event contracts need discipline.

## Distributed Transactions And Sagas

A distributed transaction attempts to commit multiple resources together. It is often expensive or unavailable across modern service boundaries.

A saga breaks a workflow into steps with compensating actions.

```text
Create Order -> Reserve Inventory -> Authorize Payment
      |               |                    |
      v               v                    v
  Cancel Order <- Release Inventory <- Void Payment
```

Sagas do not make failure disappear. They make partial completion explicit.

## Strangler Migration Pattern

The strangler pattern migrates behavior gradually.

```text
Clients -> Routing Layer -> Old System
                         -> New Capability
```

Use it when rewriting everything at once is too risky.

## Anti-Corruption Layer

An anti-corruption layer translates between models so a new domain does not inherit old system concepts blindly.

```text
New Order Model <-> Translation Boundary <-> Legacy Fulfillment Model
```

## Evolution From Monolith To Services

A safer path:

1. Clarify module boundaries.
2. Separate domain interfaces.
3. Remove shared mutable internals.
4. Define API or event contracts.
5. Extract only when ownership or scaling pressure justifies it.
6. Monitor the new distributed boundary.
