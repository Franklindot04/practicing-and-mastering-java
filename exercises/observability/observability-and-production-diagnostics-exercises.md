# Observability And Production Diagnostics Exercises

## 1. Monitoring Or Observability

For each prompt, classify whether it is mainly monitoring, observability, or both:

1. Page when checkout error rate is above the SLO burn threshold.
2. Investigate why retry traffic increased only for premium orders.
3. Show current JVM heap usage on a dashboard.
4. Determine whether a missing span means the dependency was not called.

## 2. Telemetry Selection

A Java order service has slow confirmations. Choose logs, metrics, traces, health checks, profiles, dumps, or domain signals for each question:

- Are users affected?
- Which dependency is slow?
- Are workers saturated?
- Did a release change behavior?
- Is CPU time concentrated in JSON parsing?

## 3. Structured Log Review

Rewrite this unsafe log idea as a structured event:

```text
ERROR User alice@example.com sent password hunter2 and token abc123 to checkout; payment failed for card 4111111111111111
```

Include event name, severity, operation, outcome, safe identifiers, and redaction choices.

## 4. Severity And Duplicate Logging

A repository throws `TimeoutException`. The repository logs it at `error`, the service logs it at `error`, and the controller logs it at `error` before returning 503. Explain what is wrong and propose a safer logging strategy.

## 5. Cardinality Analysis

Classify each field as safe for metric labels, safe for logs/traces only, or unsafe:

- `operation=createOrder`
- `requestId=req-932`
- `userEmail=learner@example.test`
- `status=success`
- `exceptionMessage=Timed out after 1973ms for order 81231`
- `dependency=payment`

## 6. Metric Type Selection

Choose counter, gauge, histogram, summary concept, or timer:

- Requests received.
- Current queue depth.
- Request duration distribution.
- Active database connections.
- Retry attempts by dependency.
- Heap used bytes.

## 7. Health Check Design

Design liveness, readiness, and startup checks for a local Java worker that consumes jobs from a queue and writes to a database. Explain shallow versus deep checks and timeout risks.

## 8. Trace Context Loss

A request log has `requestId=req-10`, the controller span exists, but no span appears for work done in an executor. List likely causes and how you would test context propagation.

## 9. Sampling Tradeoffs

Traffic is high, storage is expensive, and rare payment failures matter. Compare head sampling, tail sampling, probabilistic sampling, and error-biased sampling for this service.

## 10. Latency Breakdown

A trace-like waterfall shows:

```text
checkout root: 1200ms
  validate: 5ms
  inventory: 40ms
  payment attempt 1: 500ms timeout
  payment attempt 2: 520ms success
  persist order: 25ms
```

Identify symptoms, cause candidates, missing evidence, and one mitigation.

## 11. Queue Saturation And Retry Storm

Metrics show queue depth rising, active workers maxed, dependency timeouts increasing, and retries increasing. What evidence supports a retry storm? What evidence would you seek before changing retry policy?

## 12. Alert Actionability

Review this alert:

```text
CPU over 70 percent for 2 minutes on one instance
```

Decide whether it should page, ticket, or be a dashboard signal. Improve it if needed.

## 13. SLI And SLO Reasoning

Define an availability SLI and latency SLO for a task API. State eligible events and what you would exclude.

## 14. Error Budget Calculation

An SLO allows 99.9 percent successful valid requests over 30 days. The service receives 2,000,000 valid requests. How many failures fit the budget? If 1,500 failures happen in one day, what concern does that raise?

## 15. Incident Timeline

Build a short incident timeline from:

- 10:02 deployment starts.
- 10:07 p95 latency rises.
- 10:09 errors rise.
- 10:11 rollback begins.
- 10:15 errors return to baseline.

Add evidence you would preserve.

## 16. Dump Safety

When would a thread dump be useful? When would a heap dump be risky? State handling precautions.

## 17. Java Code Review

Review this code:

```java
logger.error("failed user=" + email + " token=" + token, ex);
metrics.increment("failure." + requestId);
executor.submit(() -> service.process(request));
```

Identify logging, metric, and context-propagation issues.

## 18. Design Telemetry

Design telemetry for a small Java report-generation service. Include logs, metrics, trace-like records, health checks, and privacy rules.

## 19. Partial Evidence Diagnosis

Users report stale reports. Metrics show report jobs are delayed, database errors are normal, queue depth is high, and a recent deployment changed worker concurrency. State evidence, speculation, and next diagnostic steps.

