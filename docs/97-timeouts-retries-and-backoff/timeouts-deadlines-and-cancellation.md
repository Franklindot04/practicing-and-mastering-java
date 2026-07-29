# Timeouts, Deadlines, And Cancellation

A timeout is a local waiting limit. A deadline is an absolute latest completion time for the request. Cancellation is the cooperative act of stopping work after the result is no longer useful.

## Coverage Notes

### Connection Timeout

The maximum time allowed to establish a connection.

Java angle: Configure HTTP and database clients so connection setup cannot block a worker forever.

Tradeoff or failure case: Too low causes false failures; too high hides dependency trouble.

### Read Timeout

The maximum wait for response bytes after a request is sent.

Java angle: A Java client should distinguish no response from a slow response.

Tradeoff or failure case: Missing read timeouts can pin request threads.

### Write Timeout

The maximum wait while sending a request body.

Java angle: Large uploads or slow sockets need bounded writes.

Tradeoff or failure case: A write timeout does not prove the dependency did not receive partial data.

### Operation Timeout

A timeout for an entire local operation.

Java angle: Wrap a dependency method with a total budget rather than only socket phases.

Tradeoff or failure case: Layered timeouts must fit inside the caller deadline.

### Request Deadline

A deadline is an absolute end time shared across nested calls.

Java angle: Pass remaining time through service methods.

Tradeoff or failure case: A retry that starts after the deadline creates orphaned work.

### Cancellation

Cancellation asks in-flight work to stop because the result is no longer needed.

Java angle: Use `Future.cancel`, interrupt-aware blocking, or explicit cancellation flags where appropriate.

Tradeoff or failure case: Cancellation in Java is cooperative, not magic.

### Timeout Propagation

Timeout propagation passes local limits to downstream calls.

Java angle: Convert remaining deadline to a shorter timeout before calling a dependency.

Tradeoff or failure case: An inner timeout longer than the outer request wastes work.

### Deadline Propagation

Deadline propagation carries the absolute deadline across layers.

Java angle: A `RequestContext` can expose `remaining()` and `isExpired()`.

Tradeoff or failure case: Clock differences matter across machines; local examples can use injected clocks.

### Bounded Waiting

Bounded waiting means every blocking point has a limit.

Java angle: Use bounded queues, timed acquires, and client timeouts.

Tradeoff or failure case: Unbounded waiting is a reliability bug.

### Timeout Selection

Timeouts should reflect caller expectations, dependency behavior, and recovery cost.

Java angle: Start from measured latency and SLO needs, then test edge cases.

Tradeoff or failure case: There is no universal timeout value.

### Timeout Layering

Outer deadlines should be longer than inner dependency timeouts plus retry delay.

Java angle: Make the math visible in code or configuration.

Tradeoff or failure case: Layering mistakes create work that cannot complete in time.

### Java Interruption

Interruption is Java's standard signal for cooperative cancellation in blocking code.

Java angle: Catch `InterruptedException`, restore interrupt status, and exit or propagate.

Tradeoff or failure case: Swallowing interruption can prevent shutdown.

### Cooperative Cancellation

Code must periodically check whether cancellation was requested.

Java angle: Loops can check `Thread.currentThread().isInterrupted()` or a request budget.

Tradeoff or failure case: CPU-bound work will not stop unless it checks.

### Preserving Interrupt Status

If a method cannot throw `InterruptedException`, it should call `Thread.currentThread().interrupt()`.

Java angle: This lets outer code observe the cancellation signal.

Tradeoff or failure case: Clearing the flag hides shutdown requests.

### CompletableFuture Timeout Concepts

`orTimeout` completes the future exceptionally; `completeOnTimeout` supplies a fallback value.

Java angle: The underlying work may continue unless the executor and task cooperate.

Tradeoff or failure case: Do not confuse future completion with stopping side effects.

### Orphaned Work

Orphaned work continues after the caller has left.

Java angle: Use deadlines, cancellation, and bounded executors to reduce it.

Tradeoff or failure case: Orphaned writes can corrupt user expectations.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
