# Retry Patterns

Retries are useful when a failure is temporary. They are risky when the operation has side effects or the downstream system is already overloaded.

## Retry Only When It Helps

Good retry candidates:

- Temporary network errors.
- Brief service unavailability.
- Rate limits that include a retry-after signal.
- Read operations that do not change state.

Poor retry candidates:

- Validation failures.
- Authentication failures.
- Requests that may create duplicate side effects.
- Persistent dependency outages.

## Backoff And Limits

Retries should have limits and delays.

```text
attempt 1 -> fail
wait 100ms
attempt 2 -> fail
wait 300ms
attempt 3 -> fail
stop and return a clear error
```

Backoff gives the downstream system time to recover. Limits prevent endless work.

## Retry Storms

A retry storm happens when many clients retry at once and increase pressure on a struggling service.

Avoid retry storms with:

- Timeouts.
- Maximum attempts.
- Exponential backoff.
- Jitter, which adds randomness to retry timing.
- Circuit breakers or load shedding when appropriate.

## Safe Retry Checklist

- Is the operation idempotent?
- Is there a timeout?
- Is there a retry limit?
- Is the delay strategy documented?
- Are duplicate outcomes observable?
- Does the caller have a fallback?

