# Requirements And Assumptions

## Functional Requirements

- Customers can submit an order.
- Customers can retrieve order status.
- The platform checks inventory availability through a boundary.
- The platform requests payment authorization through a boundary.
- The platform can request customer notifications after meaningful state changes.
- Operators can inspect order identifiers, status, and failure reason categories.

## Non-Functional Requirements

- Order submission should be idempotent for client retries.
- The order write path should keep its core decision inside one local boundary.
- External payment, inventory, and notification systems should be isolated behind interfaces.
- The design should support asynchronous follow-up work later.
- The design should expose enough status for observability and support.
- The design should avoid storing payment secrets.

## Assumptions

- Most reads are order status lookups.
- Order creation is lower volume than order retrieval.
- Payment authorization may be slow or unavailable.
- Inventory reservation may fail because stock is insufficient.
- Notifications are not required before the order response returns.
- Exact traffic and storage numbers require measurement.

## Core Use Cases

```text
Create order:
Customer -> Order API -> Idempotency check -> Inventory boundary -> Payment boundary -> Order state

Retrieve order:
Customer -> Order API -> Order read model
```

## Constraints

- Keep the first implementation infrastructure-free.
- Keep future external systems behind interfaces.
- Do not couple the domain model to provider-specific SDKs.
- Prefer explicit tradeoffs over a single supposedly perfect architecture.
