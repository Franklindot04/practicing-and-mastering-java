# Discovery Exercises

## Exercise 1: Stale Discovery Entry

Difficulty: Beginner

Concepts practiced: service discovery, health checks, stale routing.

Problem statement:
A task API discovers three notification service instances. One instance becomes unhealthy but remains in discovery for two minutes. Explain what users may experience and what safeguards the caller should have.

Hints:

- Discovery is not a substitute for timeouts.
- Health signals can be stale.
- Callers may need retries to other instances.

Stretch challenge:
Define one metric that would reveal stale discovery entries.

## Exercise 2: Discovery Unavailable

Difficulty: Intermediate

Concepts practiced: dependency failure, cached discovery data, degraded behavior.

Problem statement:
The discovery registry is unavailable. Decide whether the task API should fail immediately, use cached endpoints, or enter degraded mode. Explain the risks.

Hints:

- Cached endpoints may be stale.
- Failing fast may protect the system.
- Degraded mode may be acceptable for optional dependencies.

Stretch challenge:
Write a runbook note for this failure.

