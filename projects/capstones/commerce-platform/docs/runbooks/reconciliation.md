# Reconciliation Runbook

Compare reserved units, confirmed orders, and quarantined events. Investigate reservations without confirmed orders, repeated dead-letter events, and audit entries showing failed compensation.

## Triage

1. Compare reservation totals with confirmed orders.
2. Check cancelled orders for successful compensation.
3. Inspect dead-letter counts for events that block projections or fulfillment.
4. Record whether repair, replay, or manual adjustment is needed.

## Constraints

The simulation provides summary counts, not durable record history. A production reconciliation process would query authoritative stores and preserve an audit trail for every manual action.
