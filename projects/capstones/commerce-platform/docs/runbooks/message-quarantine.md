# Message Quarantine Runbook

Inspect dead-letter reason, schema version, event type, aggregate id, and duplicate status. Poison events require producer correction or manual discard. Incompatible schemas require consumer upgrade or contract rollback.

## Triage

1. Group quarantined events by reason.
2. Separate poison payloads from incompatible schemas.
3. Confirm whether duplicates are being suppressed.
4. Decide whether replay is safe after code or data repair.

## Operational Implications

Dead-letter growth means downstream state may lag. Search, shipment, or notification behaviour may be incomplete until events are repaired or deliberately discarded.

## Future Improvements

Add replay approval records, payload redaction, operator ownership, and age-based alerts for quarantined events.
