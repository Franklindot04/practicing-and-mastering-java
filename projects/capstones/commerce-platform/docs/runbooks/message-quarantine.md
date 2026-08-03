# Message Quarantine Runbook

Inspect dead-letter reason, schema version, event type, aggregate id, and duplicate status. Poison events require producer correction or manual discard. Incompatible schemas require consumer upgrade or contract rollback.
