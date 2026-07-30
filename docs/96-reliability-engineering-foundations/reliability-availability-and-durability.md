# Reliability, Availability, And Durability

These terms sound similar, but they answer different questions. Reliable systems do the right thing over time, available systems answer when needed, durable systems keep accepted data, and safe systems avoid harmful behavior even when something fails.

## Coverage Notes

### Reliability

Reliability is the probability that a system performs its intended behavior correctly for a period of time.

Java angle: A reliable Java service validates inputs, handles expected exceptions, bounds waiting, and returns results that match its contract.

Tradeoff or failure case: A fast response is not reliable if it returns the wrong state or silently drops accepted work.

### Availability

Availability is the fraction of time a service can successfully serve valid requests.

Java angle: Availability is measured from the caller's perspective, often with successful response rate or health checks.

Tradeoff or failure case: A service can be available while a non-critical feature is degraded, but it should not hide critical failure as success.

### Durability

Durability means accepted data survives process restarts, crashes, and expected storage failures.

Java angle: In-memory maps are useful for examples but not durable across JVM restarts.

Tradeoff or failure case: Backups help durability only when restore has been tested and data corruption is considered.

### Correctness

Correctness means the system preserves its business rules and data invariants.

Java angle: Unit tests, validation, transaction boundaries, and idempotency checks protect correctness.

Tradeoff or failure case: Retries can damage correctness when they repeat payments, messages, or writes without duplicate protection.

### Safety

Safety means the system avoids unacceptable harm even when it cannot fully succeed.

Java angle: A Java method may reject a request clearly rather than make an unsafe best-effort write.

Tradeoff or failure case: Failing closed can reduce harm, but it may reduce availability.

### Resilience

Resilience is the ability to absorb, adapt to, and recover from failures while preserving essential behavior.

Java angle: Retries, circuit breakers, bulkheads, fallbacks, and load shedding are resilience techniques.

Tradeoff or failure case: A resilience pattern can make incidents worse when configured without budgets or observability.

### Recoverability

Recoverability is the ability to return to a known good state after failure.

Java angle: Recovery may use restart, replay, reconciliation, or repair jobs.

Tradeoff or failure case: A service is not recoverable just because it restarts; the state must be verified.

### Maintainability

Maintainability is the ease of changing, debugging, and operating the system safely.

Java angle: Small Java classes with clear boundaries make failure handling testable.

Tradeoff or failure case: Complex reliability code can become its own source of outages.

### Reliability Versus Performance

Performance measures speed and resource use; reliability measures correct behavior under expected conditions.

Java angle: A low-latency Java endpoint still needs timeouts, validation, and failure classification.

Tradeoff or failure case: Aggressive optimization can remove safety checks or overload dependencies.

### Availability Percentages And Downtime

Availability percentages imply downtime budgets: 99.9 percent over 30 days allows roughly 43 minutes of downtime, while 99.99 percent allows roughly 4 minutes.

Java angle: Use these numbers to discuss tradeoffs, not to invent production promises.

Tradeoff or failure case: Higher targets usually require more engineering, operations, and cost.

### SLIs And SLOs

A service-level indicator is a measured signal; a service-level objective is a target for that signal.

Java angle: Examples include request success rate, latency percentile, freshness, or processing lag.

Tradeoff or failure case: An SLO without reliable measurement is only a wish.

### Why 100 Percent Availability Is Usually Unrealistic

Hardware, networks, dependencies, deployments, operator mistakes, and software defects all fail.

Java angle: Java code can reduce risk with bounded waits and graceful handling, but it cannot remove every failure.

Tradeoff or failure case: Chasing 100 percent can waste effort and make systems harder to change.

### End-To-End Reliability

End-to-end reliability depends on the full path: caller, service, dependency, data store, queue, network, and operator workflow.

Java angle: A method can be locally correct but still fail users because a downstream dependency is unreliable.

Tradeoff or failure case: Improving one layer does not guarantee the whole workflow.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
