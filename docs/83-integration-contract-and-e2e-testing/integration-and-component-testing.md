# Integration And Component Testing

Integration tests check real collaboration across a boundary. Component tests check a meaningful slice with enough collaborators to exercise behavior without requiring the whole system.

## Useful Targets

| Target | What to check |
| --- | --- |
| Repository testing | Queries, mappings, constraints, transaction behavior, and missing data handling. |
| Database migration testing | Schema changes apply cleanly and preserve expected compatibility. |
| HTTP testing | Status codes, headers, validation errors, serialization, and authentication assumptions. |
| Messaging integration | Routing keys, payload shape, retry classification, idempotency, and dead-letter behavior concepts. |
| Asynchronous workflow | State eventually reaches the expected result within a bounded time. |
| Failure path | Timeout, duplicate request, partial failure, and rollback behavior. |

## Real Dependency Tradeoffs

| Choice | Benefit | Limitation |
| --- | --- | --- |
| Real dependency | Best evidence about adapter behavior. | Slower and more operationally complex. |
| Embedded dependency | Convenient local feedback. | May differ from production features or configuration. |
| In-memory substitute | Fast and deterministic. | Can hide SQL, transaction, network, and serialization behavior. |
| Hand-written fake | Excellent for component tests. | Needs contract discipline so it does not drift from reality. |

## Database Isolation Patterns

- Transaction rollback after each test.
- Isolated schemas per test suite.
- Ephemeral databases created for a run.
- Test data builders that create only required records.
- Explicit cleanup for tests that cross transaction boundaries.

Avoid tests that silently depend on data created by a previous test.

## Eventual Consistency

Asynchronous systems may not show the result immediately. Test the condition, not the clock.

```java
boolean observed = waitUntil(
        Duration.ofMillis(500),
        () -> readModel.containsReservation("reservation-1")
);

assertTrue(observed);
```

The helper should poll at a small interval and stop at a clear timeout. A fixed long sleep makes the suite slow when the condition is already true and flaky when the condition takes slightly longer.

## Resilience And Chaos Concepts

Resilience testing explores how the system behaves under dependency failures, latency, partial outages, or unexpected responses. Chaos testing deliberately injects failure to learn about resilience. In a learning repository, discuss these concepts and practice small failure-path tests before adding operational tooling.

