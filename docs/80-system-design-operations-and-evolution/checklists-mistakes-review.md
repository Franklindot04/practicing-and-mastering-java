# Checklists Mistakes And Review Questions

## Operational Review Checklist

- Metrics exist for critical request paths.
- Logs include correlation identifiers and safe failure categories.
- Traces cross important service boundaries.
- Health checks distinguish liveness from readiness.
- Saturation signals are visible.
- Queueing and backlog signals are visible where async work exists.
- Alerts have owners.
- Runbooks exist for known failure modes.

## Migration Checklist

- Old and new versions can coexist.
- Schema changes are expand-and-contract where needed.
- API or event compatibility is documented.
- Rollback limits are named.
- Backfill behavior is observable.
- Failed migration recovery is planned.
- Deprecation timeline is communicated.

## Common Mistakes

- Adding observability after incidents instead of during design.
- Creating alerts with no owner.
- Treating rollback as always possible.
- Forgetting old clients during API changes.
- Keeping feature flags forever.
- Ignoring cost until it becomes urgent.
- Letting technical debt remain unnamed.
- Skipping threat modeling because the design is "internal."

## Review Questions

- Which signal would show the first sign of saturation?
- Which dependency needs a timeout or fallback?
- Which migration step is irreversible?
- Which feature flag needs a removal date?
- Which technical debt item has a revisit trigger?
- Which cost grows with traffic?
- Which sensitive field must never appear in logs?
- Which incident would force an architecture decision review?
