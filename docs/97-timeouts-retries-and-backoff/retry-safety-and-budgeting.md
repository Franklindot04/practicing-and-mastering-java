# Retry Safety And Budgeting

Retry safety is about protecting correctness and dependencies. Budgeting turns retry from an instinct into an explicit limit.

## Coverage Notes

### Retry Amplification

Retry amplification is the multiplication of downstream calls per user request.

Java angle: Three layers with three attempts can create many calls.

Tradeoff or failure case: Amplification is dangerous during overload.

### Retry Storms

Retry storms are synchronized, amplified retries after a shared failure.

Java angle: Use jitter and shared budgets.

Tradeoff or failure case: Storms can outlast the original failure.

### Layered Retries

Layered retries occur when clients, services, SDKs, and queues all retry.

Java angle: Decide where retry ownership belongs.

Tradeoff or failure case: Hidden SDK retries can break budgets.

### Retry Budgets

A retry budget limits the fraction or number of calls spent on retries.

Java angle: Track retry counts as telemetry.

Tradeoff or failure case: A retry budget should protect the dependency, not just the caller.

### Request Budgets

A request budget is the total time allowed for the whole workflow.

Java angle: Each attempt and delay must fit inside it.

Tradeoff or failure case: Starting work after expiration creates orphaned operations.

### Time Budgets

Time budgets divide a deadline among validation, dependencies, retries, and response handling.

Java angle: Use `Clock` and `Duration` in tests.

Tradeoff or failure case: Leaving no time for cleanup can make recovery unclear.

### Attempt Budgets

Attempt budgets bound how many tries are allowed.

Java angle: Attempt one is the original call.

Tradeoff or failure case: Attempt budgets without elapsed limits can still exceed the caller deadline.

### Dependency Protection

Retries should not overwhelm the dependency they are trying to help.

Java angle: Circuit breakers, bulkheads, and load shedding work with retry policy.

Tradeoff or failure case: Retrying every 500 response is often unsafe.

### Side-Effect Safety

Only retry side effects when idempotency or deduplication makes duplicates safe.

Java angle: Use idempotency keys for create or payment-like operations.

Tradeoff or failure case: Safe reads and unsafe writes have different retry rules.

### Idempotency Requirements

Idempotency lets repeated equivalent requests produce one effect.

Java angle: Store fingerprints and responses for duplicate keys.

Tradeoff or failure case: In-memory idempotency is educational, not durable.

### Fallback After Exhaustion

After retries are exhausted, either fail clearly or use an honest fallback.

Java angle: Return partial status when optional work is deferred.

Tradeoff or failure case: Do not pretend a critical write succeeded.

### Retry Telemetry

Record attempts, delay, final result, and classification.

Java angle: Metrics should be aggregated; logs should avoid per-attempt noise unless useful.

Tradeoff or failure case: Noisy logs can hide the real incident.

### Retry Ownership

Retry belongs closest to the component that understands safety, budget, and classification.

Java angle: A service may retry an idempotent dependency call; a UI may retry a safe read.

Tradeoff or failure case: Multiple owners cause layered retries.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
