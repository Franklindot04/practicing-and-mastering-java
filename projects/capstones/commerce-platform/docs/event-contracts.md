# Event Contracts

Events include an id, type, aggregate id, schema version, and payload. Version 1 events are compatible. Higher versions are rejected into dead-letter storage until a consumer upgrade exists.

The simulation publishes product-change, order-confirmed, shipment-requested, and notification-requested events.
