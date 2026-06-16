# Traffic Policy Planning

Traffic policy should be planned from user experience and service behavior, not copied from a generic mesh example.

## Planning Questions

- What is the user-facing deadline?
- Which internal calls are safe to retry?
- Which operations are non-idempotent?
- What timeout does application code set?
- What timeout would mesh policy set?
- What signal triggers rollback?
- What should happen when a dependency is unavailable?

## Canary Planning

A future `notification-service` v2 canary should define:

- Starting traffic percentage.
- Success metrics.
- Error and latency thresholds.
- Business correctness checks.
- Rollback steps.
- Owner for the rollout decision.

## Avoid

Do not add hidden retries to task creation or update flows unless idempotency is designed and tested.

