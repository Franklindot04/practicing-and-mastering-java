# Component Boundaries And Data Ownership

Catalogue owns product descriptions and administration changes. Search owns derived indexes and projection lag. Pricing owns price rules. Inventory owns stock and reservations. Cart owns unsubmitted customer intent. Checkout owns saga coordination, not every downstream state. Payment owns provider requests, uncertain outcomes, and idempotency records. Orders own confirmed order state. Shipment and notification own downstream side effects. Identity owns authentication, roles, tenants, and administrative trust boundaries.
