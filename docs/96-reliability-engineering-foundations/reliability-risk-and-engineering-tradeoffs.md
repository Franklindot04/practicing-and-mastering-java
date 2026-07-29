# Reliability Risk And Engineering Tradeoffs

Reliability choices trade probability, impact, cost, complexity, and user expectations. The goal is not to add every pattern, but to match the risk.

## Coverage Notes

### Probability And Impact

Risk combines how likely a failure is with how severe the outcome would be.

Java angle: A rare but harmful billing defect deserves more protection than a cosmetic intermittent error.

Tradeoff or failure case: Do not treat all exceptions as equal.

### Blast Radius

Blast radius is the amount of traffic, data, tenants, or operations affected by a failure.

Java angle: Bulkheads reduce blast radius by limiting shared resources.

Tradeoff or failure case: Large shared pools can turn one dependency issue into a whole-service outage.

### Recovery Cost

Recovery cost includes time, people, lost data, customer impact, and verification work.

Java angle: A repair job should be testable and observable.

Tradeoff or failure case: Cheap prevention may be better than expensive manual recovery.

### Operational Complexity

Every reliability pattern adds behavior operators must understand.

Java angle: A circuit breaker with clear snapshots is easier to operate than hidden state.

Tradeoff or failure case: Complexity can slow incidents if runbooks do not explain it.

### Redundancy Cost

Redundancy costs infrastructure, testing, data consistency work, and operational attention.

Java angle: Local examples can explain redundancy without requiring external infrastructure.

Tradeoff or failure case: Unused or untested redundancy may fail during the first real incident.

### Consistency Versus Availability

Some designs preserve availability by accepting stale or partial data; others preserve consistency by rejecting work.

Java angle: Java service methods should document which invariant they protect.

Tradeoff or failure case: Do not claim both perfect consistency and perfect availability under partition.

### Latency Versus Reliability

Extra checks, replication, retries, or consensus can improve reliability while adding latency.

Java angle: Budgeted retries make this tradeoff explicit.

Tradeoff or failure case: Retrying past the caller deadline only adds load.

### Retry Versus Overload Risk

Retries help transient failures but amplify traffic during overload.

Java angle: Use maximum attempts, jitter, elapsed-time limits, and idempotency.

Tradeoff or failure case: Layered retries are a common cascading-failure trigger.

### Prevention Detection Mitigation Recovery

Prevention reduces fault likelihood, detection finds symptoms, mitigation limits impact, and recovery restores correct state.

Java angle: Tests, telemetry, load shedding, and runbooks cover different parts of this chain.

Tradeoff or failure case: Observability alone does not recover the system.

### Risk Registers

A risk register records failure mode, probability, impact, owner, mitigation, and evidence.

Java angle: Keep entries specific enough to test.

Tradeoff or failure case: A stale register is less useful than a small current one.

### Failure-Mode Reviews

Failure-mode reviews ask how a workflow breaks before it breaks in production.

Java angle: Review dependency timeouts, duplicate requests, partial writes, and recovery steps.

Tradeoff or failure case: They should produce concrete tests or runbook changes.

### System Criticality

Criticality determines how much rigor a workflow needs.

Java angle: Authentication, payment, medical, safety, or data-loss paths deserve stricter handling than optional suggestions.

Tradeoff or failure case: Do not copy thresholds from one system criticality level to another.

## Java Review Questions

- Which exception, timeout, metric, or log line would prove this condition happened?
- Is retrying safe for this method, or could it repeat a side effect?
- What caller-visible result should happen when recovery is impossible within the request budget?

```java
if (requestBudget.isExpired()) {
    throw new TimeoutException("request deadline exhausted before dependency call");
}
```
