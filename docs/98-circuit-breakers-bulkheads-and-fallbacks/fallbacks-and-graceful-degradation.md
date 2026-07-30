# Fallbacks And Graceful Degradation

A fallback is an alternative result when the preferred path is unavailable. It must be honest about what is missing.

## Coverage Notes

### Cached Fallback

Cached fallback returns a previously known value.

Java angle: Useful for read-only reference data.

Tradeoff or failure case: Stale cache can violate correctness.

### Static Fallback

Static fallback returns a safe constant result.

Java angle: A feature flag may hide optional recommendations.

Tradeoff or failure case: Static values can be misleading if presented as live data.

### Stale-But-Usable Data

Stale data is acceptable only when freshness is not critical.

Java angle: Include age or diagnostic metadata when possible.

Tradeoff or failure case: Stale inventory or payment status can be unsafe.

### Partial Response

Partial response returns available fields and marks missing parts.

Java angle: Use structured response status.

Tradeoff or failure case: Do not return HTTP 200 with hidden critical failure.

### Optional Feature Disablement

Optional features can be disabled to protect core flows.

Java angle: Recommendation, notification, or analytics paths often degrade.

Tradeoff or failure case: Disabling security or payment checks is not graceful degradation.

### Default Values

Defaults fill missing non-critical data.

Java angle: Use defaults only when the caller can safely interpret them.

Tradeoff or failure case: A default can hide data loss.

### Read-Only Mode

Read-only mode accepts reads while rejecting writes.

Java angle: Useful during storage recovery.

Tradeoff or failure case: It must be visible to users or callers.

### Reduced Functionality

Reduced functionality preserves essential work while cutting optional work.

Java angle: Checkout may accept orders but defer notification.

Tradeoff or failure case: Reduced mode needs observability and recovery tasks.

### Correctness Risks

Fallbacks can preserve availability while reducing correctness.

Java angle: Define which invariant still holds.

Tradeoff or failure case: Payment fallback is usually unsafe.

### Misleading Success

Misleading success claims work completed when it did not.

Java angle: Return degraded or partial statuses.

Tradeoff or failure case: Misleading success damages recovery.

### Fallback Observability

Record fallback use as a metric and diagnostic event.

Java angle: Operators need to know degraded mode is active.

Tradeoff or failure case: Invisible fallback can hide incidents.

### User Transparency

Callers should know when behavior is degraded.

Java angle: Expose status and next action when appropriate.

Tradeoff or failure case: Too much detail can leak internals.

### Explicit Failure Safer

Explicit failure is safer when fallback would violate correctness, safety, or compliance.

Java angle: Reject duplicate payment uncertainty rather than ship unpaid goods.

Tradeoff or failure case: Availability is not always the highest priority.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
