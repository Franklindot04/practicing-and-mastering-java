# Reliability, Failure, And Cost Exercises

## Exercise 1: Stateless Vs Stateful

Difficulty: Beginner

Concepts practiced: stateless services, horizontal scaling

Problem statement: Explain why a stateless task API is easier to scale behind a load balancer than one that stores session data in memory.

Hints: Any healthy instance should be able to handle the next request.

Stretch challenge: Name one stateful dependency the API still needs.

## Exercise 2: Failure Mode Analysis

Difficulty: Intermediate

Concepts practiced: failure modes, graceful degradation

Problem statement: List three failure modes for a task API architecture and one response for each.

Hints: Consider backend instances, database, cache, queue, and observability.

Stretch challenge: Identify which failures cannot be gracefully degraded for task creation.

## Exercise 3: Cost Review

Difficulty: Beginner

Concepts practiced: cost-aware architecture

Problem statement: Name four architecture choices that can increase cloud cost unexpectedly.

Hints: Think about scaling, logs, storage, backups, and data transfer.

Stretch challenge: Add one alert or budget guardrail.

