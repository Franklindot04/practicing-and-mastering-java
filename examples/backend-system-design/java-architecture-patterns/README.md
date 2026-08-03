# Java Backend System Design Patterns

This standalone Maven example module demonstrates advanced backend architecture decisions with deterministic Java simulations. It does not deploy infrastructure. The goal is to make architecture rules testable enough for learning: module boundaries, API aggregation budgets, cache-aside behaviour, partition routing, saga compensation, and fitness checks.

## Run

```bash
mvn test
```

## Examples

- modular monolith boundaries with public ports and domain events
- API aggregation with request budgets, partial responses, fallbacks, circuit state, and correlation propagation
- cache-aside reads with expiry, invalidation, negative caching, stale fallback, metrics, and stampede prevention
- partitioned repository routing, hot-partition detection, rebalance planning, hashing, and cross-partition query cost
- saga workflow with reservations, idempotency, compensation, reconciliation, and audit history
- architecture fitness checks for dependency rules, interface bypass, contracts, retry configuration, and security boundaries
