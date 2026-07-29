# Circuit Breaker Pattern

A circuit breaker is a state machine around a dependency call. It protects callers and dependencies by failing fast after enough evidence of failure.

## Coverage Notes

### CLOSED

Closed means calls are allowed and outcomes are recorded.

Java angle: Wrap the dependency method and count failures in a window.

Tradeoff or failure case: A closed breaker still needs timeouts.

### OPEN

Open means calls are rejected without invoking the dependency.

Java angle: Throw a clear exception or return a controlled failure.

Tradeoff or failure case: Opening too eagerly can create false positives.

### HALF_OPEN

Half-open allows limited probe calls after the open duration.

Java angle: Use an injected `Clock` in tests to avoid waiting.

Tradeoff or failure case: Allowing many probes can overload a recovering dependency.

### Thresholds

Thresholds define how many failures or what failure rate opens the breaker.

Java angle: Require a meaningful minimum-call count.

Tradeoff or failure case: A threshold of one can be noisy for low-volume dependencies.

### Rolling Windows

Rolling windows evaluate recent calls rather than lifetime totals.

Java angle: Examples can simplify to counters, but production designs need windows.

Tradeoff or failure case: Lifetime counters may keep a dependency open long after recovery.

### Minimum-Call Counts

Minimum call counts prevent decisions from tiny samples.

Java angle: Do not open based on one failure out of one call unless the workflow is intentionally strict.

Tradeoff or failure case: Too high delays protection.

### Open Duration

Open duration is how long the breaker rejects before probing.

Java angle: Use `Duration` and `Clock` for deterministic tests.

Tradeoff or failure case: Too short can hammer dependencies; too long can extend outages.

### Probes

Probe calls test whether recovery is likely.

Java angle: Limit concurrent probes with a boolean or semaphore.

Tradeoff or failure case: Probe side effects must be safe or idempotent.

### Success Thresholds

Some breakers require multiple successful probes before closing.

Java angle: A simple educational breaker may close after one success and document the simplification.

Tradeoff or failure case: One success may be a false recovery.

### Transitions

Closed to open follows failure evidence; open to half-open follows time; half-open closes on success or reopens on failure.

Java angle: Expose state snapshots for diagnostics.

Tradeoff or failure case: Hidden transitions are hard to operate.

### Dependency-Specific Breakers

Each dependency should have its own breaker policy.

Java angle: Payment and notification have different criticality.

Tradeoff or failure case: A shared breaker can block healthy dependencies.

### Metrics

Record state, rejected calls, failures, probes, and recoveries.

Java angle: Metrics help explain why callers fail fast.

Tradeoff or failure case: Metrics without labels can hide which dependency failed.

### Configuration Risks

Bad thresholds, missing minimums, and wrong failure classification create risk.

Java angle: Review breaker settings with dependency behavior.

Tradeoff or failure case: Copying settings between systems is unsafe.

### Timeout And Retry Interaction

Timeouts define failure evidence; retries should stop when the breaker opens.

Java angle: Retry outside an open breaker usually fails fast.

Tradeoff or failure case: Retries before breaker recording can hide failure rate.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
