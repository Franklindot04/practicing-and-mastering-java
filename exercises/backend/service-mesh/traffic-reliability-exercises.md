# Traffic Reliability Exercises

## Exercise 1: Plan A Canary

Difficulty: Intermediate

Concepts practiced: traffic splitting, rollout safety, rollback criteria.

Problem statement: Design a conceptual canary from `notification-service` v1 to v2 for calls made by `task-api`. Include traffic steps and signals to watch.

Hints:

- Start with a small percentage.
- Watch errors, latency, retries, and business correctness.
- Define rollback before rollout.

Stretch: Add one reason a technically healthy canary might still be rolled back.

## Exercise 2: Review Retry Safety

Difficulty: Intermediate

Concepts practiced: retries, timeouts, idempotency, circuit breaking.

Problem statement: A mesh retries task creation requests twice after timeout. Explain why this may be unsafe and propose a safer policy.

Hints:

- Ask whether task creation is idempotent.
- Compare application and mesh timeouts.
- Think about duplicate side effects.

Stretch: Describe how idempotency keys could change the answer.

