# Cached Read Architecture

```text
Client -> Load Balancer -> Backend Service -> Cache -> Managed Database
```

## What It Shows

The backend checks a cache before querying the managed database. This can reduce latency and database load for repeated reads.

## Tradeoffs

- Faster reads for cache hits.
- Less database pressure.
- Requires cache invalidation.
- Can serve stale data if freshness rules are unclear.

## Intentionally Simplified

This sketch does not include cache eviction policy, consistency guarantees, security boundaries, or cache failure behavior.

