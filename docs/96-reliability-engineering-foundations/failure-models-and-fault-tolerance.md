# Failure Models And Fault Tolerance

A failure model names how the system can go wrong. Fault tolerance starts by deciding which faults are expected, how they are contained, and what the caller sees.

## Coverage Notes

### Transient Failures

Transient failures are temporary conditions that may succeed if retried after a short delay.

Java angle: Examples include a short network timeout or a temporarily busy dependency.

Tradeoff or failure case: Retry only when the operation is safe and the request still has budget.

### Persistent Failures

Persistent failures continue until something changes, such as configuration, deployment, capacity, or data repair.

Java angle: A bad JDBC URL or incompatible schema is persistent until corrected.

Tradeoff or failure case: Repeated retries usually increase noise and load.

### Intermittent Failures

Intermittent failures appear and disappear, often because of race conditions, load, resource pressure, or unstable dependencies.

Java angle: They often show up as flaky tests or rare production exceptions.

Tradeoff or failure case: They need evidence over time rather than one isolated log line.

### Partial Failures

Partial failures happen when one part of a workflow fails while another part remains healthy.

Java angle: Payment may work while notification fails.

Tradeoff or failure case: Partial success must be represented honestly in responses and recovery records.

### Fail-Stop Behavior

Fail-stop systems stop responding clearly when they cannot continue safely.

Java angle: Throwing a clear exception before writing partial state is often fail-stop.

Tradeoff or failure case: Fail-stop is easier to detect but can reduce availability.

### Omission Failures

Omission failures occur when an expected response, event, or write never happens.

Java angle: A Java consumer may acknowledge a message before performing the side effect and omit the actual work.

Tradeoff or failure case: Missing evidence can be harder to diagnose than explicit errors.

### Timing Failures

Timing failures happen when work completes too late to be useful.

Java angle: A response after the caller deadline may waste threads and produce orphaned work.

Tradeoff or failure case: Timeouts must be layered so inner work stops before outer callers give up.

### Dependency Failure

Dependency failure is a caller-visible problem caused by another service, database, queue, cache, or filesystem.

Java angle: Wrap dependency clients with timeouts, classification, and telemetry.

Tradeoff or failure case: Do not let one dependency consume all worker threads.

### Resource Exhaustion

Resource exhaustion means a bounded resource such as threads, memory, connections, permits, or disk is depleted.

Java angle: Use bounded executors, pools, queues, and semaphores.

Tradeoff or failure case: Unbounded queues can convert overload into latency collapse.

### Cascading Failure

Cascading failure occurs when one failing component causes pressure or failure in others.

Java angle: Layered retries can multiply traffic from one request into many downstream calls.

Tradeoff or failure case: Bulkheads, load shedding, and retry budgets reduce blast radius.

### Fault Versus Error Versus Failure

A fault is the underlying defect or condition, an error is an incorrect internal state, and a failure is externally visible behavior.

Java angle: A null configuration value is a fault; a thrown NullPointerException is an error; a failed request is the failure.

Tradeoff or failure case: Good diagnostics separate root conditions from symptoms.

### Fault Containment

Fault containment limits how far a fault can spread.

Java angle: Separate thread pools or semaphores can isolate slow dependencies.

Tradeoff or failure case: Containment costs resources and must be sized deliberately.

### Redundancy

Redundancy adds alternative capacity or copies so one failure does not stop the workflow.

Java angle: Examples include multiple instances or replicated data, even if the repo examples stay in-memory.

Tradeoff or failure case: Redundancy can duplicate bugs and increase consistency complexity.

### Isolation

Isolation separates tenants, dependencies, resource pools, or work classes.

Java angle: A Java service may isolate admin operations from user traffic with separate executors.

Tradeoff or failure case: Too many isolated pools can waste capacity.

### Graceful Recovery

Graceful recovery restores service without misleading callers or losing accepted work.

Java angle: Replay, reconciliation, and idempotency support safe recovery.

Tradeoff or failure case: Recovery must be verified, not assumed.

### Fail-Fast Versus Fail-Safe

Fail-fast rejects quickly when success is impossible; fail-safe preserves a safer state even if availability drops.

Java angle: Validation failures should fail fast; payment uncertainty may fail safe by stopping fulfillment.

Tradeoff or failure case: The safer choice depends on the harm of wrong success versus clear failure.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
