# Bulkheads And Resource Isolation

Bulkheads keep one class of work from consuming all shared resources. They reduce blast radius by setting explicit isolation boundaries.

## Coverage Notes

### Thread-Pool Isolation

Thread-pool isolation gives risky work its own executor.

Java angle: Use bounded executors for dependency-heavy tasks.

Tradeoff or failure case: Too many pools waste threads.

### Semaphore Isolation

Semaphore isolation limits concurrent calls without creating threads.

Java angle: A Java `Semaphore` can reject immediately when full.

Tradeoff or failure case: Always release permits in `finally`.

### Queue Isolation

Queue isolation separates waiting work by class.

Java angle: Use bounded queues and clear rejection behavior.

Tradeoff or failure case: Unbounded queues create memory and latency failures.

### Connection-Pool Isolation

Connection pools isolate database or HTTP connections.

Java angle: Separate pools for critical dependencies can prevent starvation.

Tradeoff or failure case: Oversized pools can overload the dependency.

### Tenant Isolation

Tenant isolation prevents one tenant from consuming all capacity.

Java angle: Rate limits or per-tenant semaphores can help.

Tradeoff or failure case: Strict isolation can strand capacity.

### Dependency Isolation

Dependency isolation allocates separate resources per downstream system.

Java angle: Notification should not starve payment.

Tradeoff or failure case: Isolation boundaries should reflect criticality.

### Bounded Queues

Bounded queues define how much waiting is acceptable.

Java angle: Reject when full and report the reason.

Tradeoff or failure case: A full queue is a signal, not just an exception.

### Concurrency Limits

Concurrency limits cap simultaneous work.

Java angle: Expose active and available counts for diagnostics.

Tradeoff or failure case: A limit that is too high can still saturate the dependency.

### Starvation

Starvation means lower-priority work never gets resources.

Java angle: Reserve capacity or split pools when necessary.

Tradeoff or failure case: Priority systems can starve normal users.

### Rejection Policies

Rejection can fail fast, return retry hints, or degrade optional work.

Java angle: Use typed exceptions or status codes.

Tradeoff or failure case: Silent dropping is misleading.

### Leaks

Leaks happen when permits, threads, connections, or buffers are not released.

Java angle: Use try/finally and tests for exception paths.

Tradeoff or failure case: Leaks become outages over time.

### Blast-Radius Reduction

Bulkheads contain dependency or tenant failure.

Java angle: A saturated search dependency should not prevent checkout payment.

Tradeoff or failure case: Containment may reduce total throughput.

### Isolation-Boundary Selection

Choose boundaries by criticality, failure behavior, and shared resource risk.

Java angle: Review thread pools, queues, clients, and database pools.

Tradeoff or failure case: Do not isolate everything without evidence.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
