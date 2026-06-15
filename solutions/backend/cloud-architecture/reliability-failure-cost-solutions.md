# Reliability, Failure, And Cost Solutions

## Exercise 1

A stateless API is easier to scale because any healthy instance can serve a request. If session data lives in one instance's memory, requests may need sticky routing or a shared session store.

The API still needs stateful dependencies such as a database.

## Exercise 2

Examples:

- Backend instance fails: remove it from traffic with health checks.
- Database is slow: shed load, investigate connection pool usage, and reduce expensive requests.
- Queue backlog grows: slow producers, add workers carefully, or pause non-critical jobs.

Task creation usually cannot degrade gracefully if durable database writes are unavailable.

## Exercise 3

Cost can grow through extra always-on instances, verbose logs, object storage growth, backup retention, queue workers, and data transfer. A guardrail could be a budget alert or a maximum autoscaling limit.

