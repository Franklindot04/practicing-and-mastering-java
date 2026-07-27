# Backend System Design Patterns

This small Maven project demonstrates selected backend system design patterns with plain Java.

The examples are intentionally simplified. They are useful for learning boundaries and behavior, not production-ready implementations.

## Included Patterns

- Cache-aside boundary
- Fixed-window rate limiter
- Circuit breaker state model
- Retry policy with limits
- Bulkhead with bounded permits
- Idempotency-key store and request deduplication
- Consistent-hashing style partition routing simulation
- Read/write service separation
- Health-status aggregation
- Graceful-degradation fallback
- Architecture decision record model

## Run Tests

```bash
mvn test
```

## Limitations

- No databases, network access, Docker, brokers, cloud services, or Kubernetes.
- Time is injected in examples that need deterministic tests.
- Concurrency examples are deliberately small.
- Real systems need metrics, persistence, distributed coordination, security review, and operational runbooks.
