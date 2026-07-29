# Load Shedding And Overload Protection

Overload protection rejects or defers work before saturation collapses the whole system.

## Coverage Notes

### Overload

Overload means offered work exceeds useful capacity.

Java angle: Measure queue depth, latency, active threads, and rejection counts.

Tradeoff or failure case: Retrying into overload makes it worse.

### Saturation

Saturation means a resource is at or near its limit.

Java angle: Threads, connections, memory, CPU, and queues can saturate.

Tradeoff or failure case: Latency often rises before hard failure.

### Admission Control

Admission control decides whether work may enter the system.

Java angle: Reject early when there is no capacity or budget.

Tradeoff or failure case: Late rejection wastes resources.

### Load Shedding

Load shedding deliberately rejects lower-value or impossible work.

Java angle: Return a clear overloaded status and retry hint when appropriate.

Tradeoff or failure case: Silent shedding loses trust.

### Bounded Queues

Bounded queues limit waiting work.

Java angle: Use a rejection policy when full.

Tradeoff or failure case: Unbounded queues hide overload until memory or latency fails.

### Concurrency Limits

Concurrency limits cap simultaneous work.

Java angle: A semaphore can protect a dependency.

Tradeoff or failure case: Too high is no protection; too low wastes capacity.

### Rate Limits

Rate limits bound requests over time.

Java angle: They can protect shared APIs.

Tradeoff or failure case: Rate limits do not replace concurrency limits.

### Token-Bucket Concepts

A token bucket allows bursts up to bucket size while refilling over time.

Java angle: Useful for explaining rate limits without external infrastructure.

Tradeoff or failure case: Bucket settings must match business needs.

### Rejection

Rejection should be fast, observable, and understandable.

Java angle: Use a typed exception, status, or response field.

Tradeoff or failure case: Dropping requests silently creates unknown outcomes.

### Priority Shedding

Priority shedding rejects less critical work first.

Java angle: Protect checkout before recommendations.

Tradeoff or failure case: Priority systems need fairness review.

### Stale Rejection

Stale requests are rejected because they are too old to matter.

Java angle: Compare now to request deadline.

Tradeoff or failure case: Processing stale work can create wrong side effects.

### Deadline-Aware Rejection

Deadline-aware rejection stops work that cannot finish before the caller deadline.

Java angle: Check the budget before acquiring scarce resources.

Tradeoff or failure case: It is better to reject than start doomed work.

### Backpressure

Backpressure tells upstream callers to slow down.

Java angle: Queues, reactive streams, or HTTP 429-style responses can express it.

Tradeoff or failure case: Backpressure requires callers to cooperate.

### Critical-Operation Protection

Critical operations deserve reserved capacity or stricter protection.

Java angle: Separate payment from optional notification.

Tradeoff or failure case: Do not let optional work starve recovery.

### Fairness

Fairness prevents one caller or tenant from monopolizing capacity.

Java angle: Per-tenant limits can help.

Tradeoff or failure case: Fairness can conflict with raw throughput.

### Retry-After

Retry-After communicates when retry may be useful.

Java angle: Use it with overload responses when safe.

Tradeoff or failure case: Do not encourage retries for non-idempotent operations.

### Overload Collapse

Overload collapse happens when the system spends capacity on doomed or repeated work.

Java angle: Load shedding, budgets, and backoff prevent collapse.

Tradeoff or failure case: Adding workers may worsen dependency saturation.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
