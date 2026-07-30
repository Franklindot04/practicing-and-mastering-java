# Reliability, Resilience, And Recovery Exercises

## Exercise 1: Terminology Classification

Classify each item as reliability, availability, durability, resilience, recoverability, or maintainability: a service returns valid data for 30 days, a backup restores correctly, a JVM restarts after a crash, and a runbook is easy to follow.

## Exercise 2: Reliability Versus Availability

A service returns HTTP 200 for every request but sometimes omits confirmed orders from the response. Explain why this can be available but unreliable.

## Exercise 3: Failure-Mode Analysis

For a checkout service with inventory, payment, shipping, and notification dependencies, identify one transient, persistent, intermittent, partial, timing, and omission failure.

## Exercise 4: Timeout Selection

Given a 700 ms caller deadline, choose reasonable inner budgets for validation, inventory, payment, notification, and response assembly. Explain the tradeoff.

## Exercise 5: Retry Classification

Classify these failures as retryable or non-retryable: validation error, connection timeout, duplicate idempotency fingerprint mismatch, HTTP 503 from inventory, payment card declined.

## Exercise 6: Exponential Backoff Calculation

Calculate delays for attempts 2 through 5 with initial delay 50 ms, multiplier 2, and cap 300 ms.

## Exercise 7: Jitter Reasoning

Explain how full jitter and equal jitter reduce synchronized retry storms. Give one reason tests should inject or seed randomness.

## Exercise 8: Retry-Budget Design

Design a retry budget for a dependency call inside a 2 second request deadline. Include maximum attempts, elapsed-time limit, delay cap, and stop conditions.

## Exercise 9: Circuit-Breaker Transitions

Trace a breaker from CLOSED to OPEN to HALF_OPEN to CLOSED. Include what happens to calls while OPEN and what happens after a failed probe.

## Exercise 10: Circuit-Breaker Configuration

Choose failure threshold, open duration, minimum calls, and success threshold for a low-volume dependency. Explain false-positive risk.

## Exercise 11: Bulkhead Sizing

A service has 40 request threads and one slow optional dependency. Propose a semaphore or thread-pool bulkhead and explain how it protects critical work.

## Exercise 12: Fallback Safety

Compare cached product recommendations, stale inventory, default shipping quote, read-only mode, and skipped notification. Which are safe fallbacks and why?

## Exercise 13: Idempotency-Key Design

Design an idempotency record for order creation. Include key scope, request fingerprint, stored response, expiration window, and status fields.

## Exercise 14: Duplicate-Request Race

Two identical create-order requests arrive at the same time. Explain how a naive check-then-insert map can duplicate side effects and how to fix it.

## Exercise 15: Load Shedding

Define overload, saturation, admission control, and load shedding for a Java service using a bounded executor.

## Exercise 16: Deadline-Aware Rejection

Write pseudocode that rejects a request before acquiring a dependency permit when the deadline is already expired.

## Exercise 17: Cascading Failure Diagnosis

A database slows down. API retries increase. Thread pools fill. Health checks fail. Diagnose the cascade and name two mitigations.

## Exercise 18: Graceful Degradation

Design a response for successful checkout with failed notification. Include user-facing status, internal diagnostics, and recovery action.

## Exercise 19: Recovery Objective Selection

Choose RTO and RPO for a personal blog, an online classroom, and a payment ledger. Explain why they differ.

## Exercise 20: Disaster-Recovery Planning

Create a short DR plan outline with trigger, owner, backup source, restore steps, communication plan, and validation.

## Exercise 21: Chaos Experiment Design

Design a safe experiment for injected inventory latency in a staging environment. Include steady-state hypothesis, blast radius, abort conditions, and expected learning.

## Exercise 22: Recovery-Runbook Review

Review a runbook that says only `restart the service and check logs`. List missing diagnosis, escalation, verification, and rollback details.

## Exercise 23: Java Code Reading

Read this snippet and identify the reliability bug:

```java
try {
    Thread.sleep(delayMillis);
} catch (InterruptedException ignored) {
}
```

## Exercise 24: Java Implementation

Implement a small retry executor that accepts a max-attempt count, classifier, backoff function, request budget, and sleeper. Describe tests you would write.

## Exercise 25: Production Diagnosis

An incident shows high latency, many duplicate order requests, and a spike in notification failures. Build an investigation plan using logs, metrics, idempotency records, and recovery journal evidence.

## Exercise 26: Architecture Tradeoff

A team wants active-active deployment for a low-traffic internal tool. Ask questions about cost, recovery objectives, operational complexity, data consistency, and simpler alternatives.
