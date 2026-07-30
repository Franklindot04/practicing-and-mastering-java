# Java Resilience Patterns

Educational Java 21 examples for retry, backoff, circuit breaker, bulkhead, idempotency, and load shedding. Tests use injected clocks, deterministic jitter, latches, and sleeper test doubles so they do not rely on real waiting.

## Package Structure

`dev.franklindot04.learnjava.reliability` contains small framework-free classes:

- `RetryExecutor`, `RetryPolicy`, `ExponentialBackoff`, `FailureClassifier`, and `Sleeper`
- `CircuitBreaker`, `CircuitBreakerState`, and `CircuitBreakerSnapshot`
- `Bulkhead` and `BulkheadRejectedException`
- `IdempotencyStore` and `InMemoryIdempotencyStore`
- `LoadShedder` and `RequestBudget`

## Deterministic Testing

Randomness is injected into backoff calculation as a function, time is provided by an injected `Clock`, and sleeping is represented by an injected `Sleeper`. The tests can therefore assert retry delay, request-budget exhaustion, circuit-breaker transitions, concurrent duplicate suppression, and saturation behavior without slow real waits.

## Limitations

This project is educational. Production systems usually need durable idempotency storage, distributed coordination, rolling breaker windows, richer telemetry, configuration review, and mature resilience libraries.

## Commands

```bash
mvn -f examples/reliability/java-resilience-patterns/pom.xml test
```
