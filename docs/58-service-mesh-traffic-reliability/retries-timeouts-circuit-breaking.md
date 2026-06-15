# Retries, Timeouts, And Circuit Breaking

Retries, timeouts, and circuit breakers are reliability tools. They can protect users from temporary failures, but they can also amplify load if configured carelessly.

## Timeouts

A timeout limits how long a caller waits. Without timeouts, threads and connections may wait too long and pile up.

Good timeout planning considers:

- The caller's user-facing deadline.
- The downstream service's normal latency.
- Network and queue delays.
- Whether the work is safe to abandon.

## Retries

A retry sends a request again after a failure or timeout. Retries are safest for idempotent operations, such as reads or updates designed with idempotency keys.

Retries become dangerous when:

- The original request may still complete.
- The operation creates side effects.
- Many callers retry at the same time.
- Retry budgets are missing.

## Circuit Breaking

A circuit breaker stops sending some requests to an unhealthy dependency for a period of time. It can prevent a failing dependency from consuming more resources.

## Design Habit

Set application and mesh policies together. A mesh timeout that conflicts with application timeouts can create confusing failures.

