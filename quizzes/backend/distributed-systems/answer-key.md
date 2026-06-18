# Distributed Systems Answer Key

## Multiple Choice

1. B
2. B
3. C
4. C
5. B
6. A
7. B
8. B

## Short Answer

1. A monolith keeps most behavior in one deployable unit, while a distributed system splits behavior across processes or services that communicate over a network. The better choice depends on boundaries, scale, team ownership, and operational readiness.

2. The downstream service may have completed the work, but the response may have been delayed or lost. The caller cannot safely assume failure or success without idempotency or a status check.

3. A task detail page after creation usually needs read-after-write consistency. A dashboard count or report summary may be eventually consistent if delayed data is acceptable.

4. The API should reject it as a conflict or invalid idempotency-key reuse and log enough detail to investigate without exposing sensitive data.

5. Unlimited retries can overload a struggling dependency, waste resources, and repeat side effects. Limits force the system to fail clearly or move work to a recovery path.

6. A compensation action is a business operation that responds to a completed step after a later step fails, such as releasing a reservation or marking an import failed.

7. Instances can be slow, partitioned, paused, or stale. A safe election must avoid multiple active leaders performing exclusive work.

8. Useful signals include correlated logs, metrics, traces, dependency health, workflow status records, retry counts, latency, and error rates.

## Design Reading

1. The task write and the user's immediate task detail or list view should be read-after-write consistent.
2. The reporting summary may be eventually consistent.
3. Retrying may send a duplicate notification.
4. Store a notification request ID or idempotency key and return the original result on duplicate attempts.
5. Show a last-updated time, pending status, or lag indicator, and monitor projection delay.
6. Include request ID, task ID, notification request ID, attempt number, dependency name, latency, and final status.
7. Duplicate summary updates may happen. The update should be idempotent, partitioned by ownership, or protected by safe coordination.
8. Task data-store failure should usually block task creation because it is core state. Notification failure can often be degraded or retried later.

