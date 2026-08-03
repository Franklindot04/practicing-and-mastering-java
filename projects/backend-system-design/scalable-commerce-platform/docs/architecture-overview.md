# Architecture Overview

The default architecture is a modular commerce backend with clear module boundaries and simulated ports. Catalogue owns product facts. Search owns derived projections. Pricing owns price decisions. Inventory owns reservations. Cart owns customer intent before checkout. Checkout coordinates the saga. Payment owns provider idempotency. Orders own confirmed lifecycle state. Shipment and notification react to events. Identity and administration define trust boundaries.

```mermaid
flowchart LR
    Gateway[API Gateway Simulation]
    Catalog[Catalog Module]
    Search[Search Projection]
    Pricing[Pricing Module]
    Inventory[Inventory Module]
    Checkout[Checkout Module]
    Payment[Payment Gateway]
    Orders[Order Module]
    Outbox[(Outbox)]
    Events[Event Channel]
    Shipment[Shipment Module]
    Notification[Notification Module]

    Gateway --> Catalog
    Gateway --> Search
    Gateway --> Checkout
    Checkout --> Pricing
    Checkout --> Inventory
    Checkout --> Payment
    Checkout --> Orders
    Orders --> Outbox
    Outbox --> Events
    Events --> Shipment
    Events --> Notification
```
