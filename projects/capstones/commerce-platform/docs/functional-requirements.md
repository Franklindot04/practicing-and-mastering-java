# Functional Requirements

The capstone models catalogue, pricing, inventory reservations, carts, checkout, payment, order confirmation, shipment request, notification request, search projection, audit, and reconciliation.

Checkout must validate cart contents, reserve inventory, charge payment idempotently, publish order events through an outbox, and compensate reservations when payment fails. Duplicate checkout and duplicate payment requests must return stable results for the same idempotency key.
