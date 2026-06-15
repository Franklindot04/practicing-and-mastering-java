# Observability Security Exercises

## Exercise 1: Trace A Slow Request

Difficulty: Intermediate

Concepts practiced: tracing, metrics, logs, service-to-service debugging.

Problem statement: A task update request is slow after adding a conceptual `notification-service`. List the telemetry you would check and what each signal tells you.

Hints:

- Metrics show trends.
- Traces show one request path.
- Logs provide detail.

Stretch: Include the deployment version in your debugging plan.

## Exercise 2: Separate Identities

Difficulty: Beginner

Concepts practiced: mTLS, workload identity, user authentication, authorization.

Problem statement: Explain the difference between `task-api` proving its workload identity to another service and a user proving they are allowed to complete a task.

Hints:

- Workload identity belongs to services.
- User identity belongs to application security.
- Authorization can exist at both layers.

Stretch: Name one security risk mTLS does not solve.

