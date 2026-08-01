# Distributed Systems Operational Complexity

Distributed systems require operational design because failures are no longer obvious from one process. The system needs evidence, ownership, recovery steps, and explicit assumptions.

## Fallacies Of Distributed Computing

Common false assumptions include:

- the network is reliable
- latency is zero
- bandwidth is infinite
- the network is secure by default
- topology does not change
- there is one administrator
- transport cost is zero
- the network is homogeneous

In Java work, these fallacies show up as missing timeouts, unbounded executors, infinite retries, unclear ownership, and tests that only cover the happy path.

## Explicit Failure Models

A failure model states what failures the design expects and how it responds. For example:

- requests may be duplicated, so commands include idempotency keys
- responses may be delayed, so callers use deadlines and cancellation
- a node may pause, so peers treat missing heartbeats as suspicion, not proof
- replicas may lag, so reads document freshness expectations

Without a failure model, code often contains accidental promises. A method named `saveExactlyOnce` is misleading if it only writes once in one JVM and cannot prove remote delivery.

## Operational Readiness

Distributed readiness includes:

- request IDs and correlation IDs
- logs with node identity and operation identity
- metrics for latency, retry rate, error rate, queue depth, and saturation
- health checks that distinguish process liveness from dependency readiness
- runbooks for timeouts, partial outages, stale reads, and retry storms
- recovery tests that prove the team can restore service

## Realistic Failure Case

A Java API calls an inventory service with a 300 ms timeout. The inventory service reserves stock in 350 ms and returns success, but the API has already timed out and retries. Without an idempotency key, the second call may reserve stock again. If the retry storm grows, the inventory database gets slower, which creates more timeouts.

This is not solved by "add retries." It needs deadlines, idempotency, backoff, observability, and a business decision about what to show the user during uncertainty.

## Honest Simplifications

Educational simulations can show delayed messages, stale reads, and leader terms. They should not be described as production consensus or distributed locks. Real systems must handle persistence, process pauses, clock problems, security, rolling upgrades, operator mistakes, and many more edge cases.

## Review Questions

1. Which distributed-systems fallacy is violated by a 300 ms timeout on a 350 ms successful operation?
2. Why is "suspected" more accurate than "dead" in a heartbeat-based detector?
3. What signals would help diagnose a retry storm?
4. Why should educational simulations state their limitations?
