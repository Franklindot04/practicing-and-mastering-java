# Common Mistakes

Distributed systems often fail because teams underestimate operational details.

## Mistake: No Timeouts

Without timeouts, callers can wait indefinitely and exhaust resources.

## Mistake: Blind Retries

Retries without limits, backoff, jitter, or idempotency can amplify outages.

## Mistake: Logs Without Correlation

Logs that cannot be connected across services make investigations much harder.

## Mistake: Hidden Background Failures

If background work fails silently, users and operators may believe the workflow completed when it did not.

## Mistake: No Degraded Mode

Some systems fail completely when a non-critical dependency is unavailable. Design which features can degrade safely.

## Mistake: Splitting Too Early

Moving unclear boundaries across the network creates distributed confusion. Clarify ownership and data rules before splitting.

