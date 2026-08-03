# Architecture Overview

The system is organized as a modular monolith simulation with explicit boundaries for catalogue, pricing, inventory, checkout, payment, messaging, search, observability, and operations.

```mermaid
flowchart LR
  Cart["Cart"] --> Checkout["Checkout Saga"]
  Checkout --> Inventory["Inventory Reservation"]
  Checkout --> Payment["Payment Boundary"]
  Checkout --> Outbox["Outbox"]
  Outbox --> Search["Search Projection"]
  Outbox --> Shipment["Shipment Request"]
  Outbox --> Notify["Notification Request"]
  Checkout --> Audit["Audit Trail"]
  Checkout --> Metrics["Metrics"]
```
