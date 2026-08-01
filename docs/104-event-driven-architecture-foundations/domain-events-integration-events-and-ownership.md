# Domain Events Integration Events And Ownership

Events carry meaning across boundaries. The boundary determines who owns the event, how stable it must be, and which consumers may safely depend on it.

## Domain Events

A domain event is a fact inside a bounded business model. It uses the language of that domain.

```java
sealed interface OrderDomainEvent permits OrderSubmitted, OrderCancelled {}

record OrderSubmitted(String orderId, String customerId, long totalCents)
        implements OrderDomainEvent {}

record OrderCancelled(String orderId, String reason)
        implements OrderDomainEvent {}
```

Domain events are useful inside a service or closely owned module. They can be detailed because producers and consumers often evolve together.

## Integration Events

An integration event is a stable contract shared outside the producer boundary.

```java
record OrderSubmittedV1(
        String orderId,
        String customerId,
        long totalCents,
        String currency
) {}
```

Integration events should expose facts that external consumers can rely on without learning private implementation details. They need clearer compatibility rules than internal domain events.

## Internal And External Events

Internal events may include implementation-specific fields. External events should be intentionally designed, documented, versioned, and monitored.

Do not publish every domain event as an integration event. Some facts are too noisy, too private, too unstable, or too easy to misinterpret outside the owning domain.

## Event Ownership

The producer owns the event contract because it owns the fact being reported. Ownership includes:

- naming the event
- defining payload meaning
- documenting required and optional fields
- deciding compatibility rules
- communicating schema changes
- monitoring publication health

Consumers own their reactions. A producer should not need to know whether analytics updates a dashboard, notifications sends email, or fulfillment creates a pick list.

## Consumer Autonomy

Consumer autonomy means each consumer can choose its own model, storage, retry policy, and release cadence. It does not mean consumers can reinterpret event meaning freely.

If a consumer needs a new field, it should request an additive contract change or maintain its own lookup. If it relies on private producer behavior, the system becomes tightly coupled again.

## Event Sourcing Is Distinct

Ordinary event publishing records or emits facts so other components can react. Event sourcing stores state as the complete sequence of events and rebuilds current state by replaying them.

```java
record AccountOpened(String accountId) {}
record MoneyDeposited(String accountId, long cents) {}
record MoneyWithdrawn(String accountId, long cents) {}
```

With event sourcing, the event log is the source of truth. That requires strict event design, long-term retention, upcasting, replay safety, and operational tooling. Stage 26 may mention the pattern, but it does not implement a production event-sourced store.

## Ownership Mistakes

Common mistakes include publishing database row snapshots as external contracts, letting consumers depend on internal event order, changing event meaning without changing the contract, and assuming no one consumes an event just because no direct caller exists.
