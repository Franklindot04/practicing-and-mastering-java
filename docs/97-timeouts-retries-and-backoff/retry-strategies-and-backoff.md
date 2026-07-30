# Retry Strategies And Backoff

Retry strategy determines when another attempt is useful. Backoff spaces attempts so transient failures have time to recover and dependencies are protected.

## Coverage Notes

### Immediate Retry

An immediate retry runs again without delay.

Java angle: Useful only for rare races or already-local operations.

Tradeoff or failure case: Can create retry storms under dependency failure.

### Fixed Delay

Fixed delay waits the same time between attempts.

Java angle: Simple and deterministic in tests with an injected sleeper.

Tradeoff or failure case: Many clients can synchronize on the same delay.

### Linear Backoff

Linear backoff increases delay by a fixed amount.

Java angle: Easy to reason about for local examples.

Tradeoff or failure case: May still be too aggressive during overload.

### Exponential Backoff

Exponential backoff multiplies delay after each attempt.

Java angle: Use `initialDelay * 2^(attempt-1)` with overflow protection.

Tradeoff or failure case: Uncapped exponential delays can exceed request budgets.

### Capped Exponential Backoff

A cap limits maximum delay.

Java angle: A Java `Duration` cap keeps calculations bounded.

Tradeoff or failure case: The cap is not universal; choose it from the request budget.

### Jitter

Jitter randomizes delay to avoid synchronized retries.

Java angle: Inject a seeded random or deterministic function in tests.

Tradeoff or failure case: Uncontrolled randomness makes tests flaky.

### Full Jitter

Full jitter chooses a value between zero and the current cap.

Java angle: It spreads clients widely during overload.

Tradeoff or failure case: A zero delay may still be too aggressive for some dependencies.

### Equal Jitter

Equal jitter keeps part of the delay fixed and randomizes the rest.

Java angle: It balances minimum slowdown with spreading.

Tradeoff or failure case: It is still a policy choice, not a default.

### Decorrelated Jitter

Decorrelated jitter bases the next delay on the prior delay and a cap.

Java angle: Explain it conceptually unless the implementation needs it.

Tradeoff or failure case: Test it with injected deterministic randomness.

### Retryable Versus Non-Retryable

Retryable failures are likely temporary; non-retryable failures require a different action.

Java angle: Classify exceptions before retrying.

Tradeoff or failure case: Validation errors and duplicate unsafe writes should not be retried blindly.

### Maximum Attempts

Maximum attempts bound call amplification.

Java angle: Include the first try in the count.

Tradeoff or failure case: Too many attempts can exhaust the caller deadline.

### Elapsed-Time Limits

Elapsed-time limits stop retries when the request budget is gone.

Java angle: Check elapsed time before sleeping and before the next attempt.

Tradeoff or failure case: Attempt count alone is not enough.

### Retry-After Hints

`Retry-After` or similar hints communicate when a caller should try again.

Java angle: Respect hints when they fit the caller budget.

Tradeoff or failure case: Hints are not permission to retry unsafe operations.

### Synchronized Retry Storms

A retry storm happens when many callers retry at the same time.

Java angle: Backoff, jitter, budgets, and load shedding reduce storm risk.

Tradeoff or failure case: Layered retries multiply the storm.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
