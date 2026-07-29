# Recovery Patterns And Operational Readiness

Recovery is the work of getting back to correct, verified behavior after failure. Operational readiness means people know how to do that work.

## Coverage Notes

### Restart And Retry Recovery

Some faults clear after restart or retry.

Java angle: Use restarts for clean process state, not data repair.

Tradeoff or failure case: Restart loops can hide persistent faults.

### Checkpointing

Checkpointing records progress so work can resume.

Java angle: Batch jobs can store last processed ID.

Tradeoff or failure case: Bad checkpoints can skip or duplicate work.

### Replay

Replay reprocesses stored events or requests.

Java angle: Idempotency makes replay safer.

Tradeoff or failure case: Replay can overload dependencies.

### Reconciliation

Reconciliation compares systems of record and repairs differences.

Java angle: Use deterministic comparison jobs.

Tradeoff or failure case: It needs ownership and auditability.

### Compensation

Compensating actions offset prior work when rollback is impossible.

Java angle: Issue a refund rather than deleting payment history.

Tradeoff or failure case: Compensation is not always equivalent to undo.

### Rollback And Roll-Forward

Rollback returns to an earlier version; roll-forward deploys a fix.

Java angle: Choose based on data migrations and risk.

Tradeoff or failure case: Rollback can be unsafe after irreversible data changes.

### State Repair

State repair fixes incorrect data.

Java angle: Scripts should be reviewed, tested, and logged.

Tradeoff or failure case: Manual repair without evidence creates new risk.

### Reprocessing

Reprocessing reruns data through a pipeline.

Java angle: Bound it and observe progress.

Tradeoff or failure case: Reprocessing old data may trigger old side effects.

### Degraded Operation

Degraded mode preserves essential work while reducing functionality.

Java angle: Expose degraded status in diagnostics.

Tradeoff or failure case: A degraded mode needs exit criteria.

### Recovery Verification

Recovery is complete only after checks pass.

Java angle: Verify data, metrics, user paths, and dependency health.

Tradeoff or failure case: Green health checks alone may be insufficient.

### Runbooks

Runbooks list symptoms, diagnosis, mitigation, escalation, and verification.

Java angle: Keep commands and decision points clear.

Tradeoff or failure case: Untested runbooks are guesses.

### Ownership And Escalation

Ownership identifies who decides and who acts.

Java angle: Escalation paths prevent delay.

Tradeoff or failure case: Ambiguous ownership lengthens incidents.

### Readiness Reviews

Readiness reviews check observability, limits, rollback, recovery, and support before release.

Java angle: Review reliability code and runbooks together.

Tradeoff or failure case: A review without action tracking is weak.

### Drills

Recovery drills test whether people and systems can recover.

Java angle: Use safe test environments or controlled scopes.

Tradeoff or failure case: A drill should not end until findings are tracked.

### Post-Incident Learning

Post-incident learning improves systems without blame.

Java angle: Convert findings into tests, alerts, docs, or design changes.

Tradeoff or failure case: Learning without follow-through repeats incidents.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
