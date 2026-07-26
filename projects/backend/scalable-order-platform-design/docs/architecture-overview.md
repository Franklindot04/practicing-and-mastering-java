# Architecture Overview

The first design is a modular backend boundary around order submission. It keeps order decisions local while treating inventory, payment, and notification as external boundaries.

## Package Design

```text
dev.franklindot04.learnjava.orderdesign
  OrderService
  OrderRequest
  Order
  OrderStatus
  InventoryGateway
  PaymentGateway
  NotificationGateway
  IdempotencyStore
  CapacityEstimate
  ArchitectureDecisionRecord
```

## API Boundaries

Conceptual API shape:

```text
POST /orders
GET  /orders/{orderId}
```

The project does not implement HTTP. The API shape is documented so the Java service can focus on behavior.

## Synchronous Versus Asynchronous Flow

Initial synchronous decisions:

- Validate order request.
- Check idempotency key.
- Reserve inventory conceptually.
- Authorize payment conceptually.
- Save final order status in the local boundary.

Possible asynchronous follow-up:

- Notification delivery.
- Analytics projection.
- Search indexing.
- Dead-letter review for failed follow-up tasks.

## Read And Write Paths

```text
Write path:
OrderRequest -> OrderService -> InventoryGateway -> PaymentGateway -> Order

Read path:
Order id -> OrderService -> stored order summary
```

Read/write separation can later evolve into a dedicated read model if query traffic justifies it.

## Data Ownership

The order boundary owns:

- Order identifier.
- Customer identifier reference.
- Submitted line items.
- Order status.
- Failure reason category.

It does not own:

- Payment credentials.
- Inventory truth.
- Customer notification preferences.
