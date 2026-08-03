# Module Boundaries

Catalogue owns product activation and product names. Pricing owns current price contracts. Inventory owns stock and reservations. Checkout coordinates the saga but does not own product or payment state. Payment owns idempotent charge results. Messaging owns outbox, duplicate-message protection, and quarantine. Search owns its projection and accepts lag.
