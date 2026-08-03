# Functional Requirements

The platform supports catalogue browsing, search, price resolution, inventory reservation, cart management, checkout, payment authorization, order confirmation, shipment request, notification, customer account isolation, and administration workflows.

Core checkout accepts a cart, resolves current prices, reserves inventory, authorizes payment with an idempotency key, confirms an order, writes an outbox event, requests shipment, and emits notification work. Optional dependencies may degrade, but inventory, payment idempotency, and order confirmation invariants remain protected.
