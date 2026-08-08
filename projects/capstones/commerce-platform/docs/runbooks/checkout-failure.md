# Checkout Failure Runbook

Check the checkout result reason, inventory failure count, payment result, compensation audit entry, and order status. Confirm whether the failure is customer-correctable, dependency-related, or a compensation defect.

## Triage

1. Identify whether the failure is validation, inventory, payment, capacity, or compensation.
2. Check duplicate counts to see whether the caller retried with the same idempotency key.
3. Review audit entries for compensation status.
4. Run reconciliation if reservations and confirmed orders may disagree.

## Operator Decision

- Customer-correctable failures can be returned as clear rejection reasons.
- Dependency failures should be bounded and visible.
- Compensation failures require follow-up because inventory and order state may be inconsistent.

## Production Comparison

A real runbook would include dashboards, trace queries, payment-provider lookup, inventory database queries, incident severity, customer communication, and rollback criteria.
