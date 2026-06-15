# Caching, Queues, And Async Processing

## Caching Basics

A cache stores data that is expensive to compute or fetch repeatedly. Caching can reduce latency and database load.

Common cache questions:

- What data is safe to cache?
- How long can it be stale?
- Who invalidates it?
- What happens when the cache is unavailable?

## Cache Invalidation

Cache invalidation is deciding when cached data should be removed or refreshed. A fast cache with stale or incorrect data can make the system less reliable.

## Message Queues

A queue stores work for later processing. Queues help decouple request handling from slow background work.

Example:

```text
Backend Service -> Queue -> Worker -> Managed Database
```

## Async Job Processing

Async processing is useful for email sending, reports, imports, media processing, and other work that does not need to finish inside the user request.

Queues add operational responsibility. Learners should understand retries, dead-letter handling, idempotency, and monitoring before treating queues as a cure-all.

