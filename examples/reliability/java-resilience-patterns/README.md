# Java Resilience Patterns

Educational Java 21 examples for retry, backoff, circuit breaker, bulkhead, idempotency, and load shedding. Tests use injected clocks, deterministic jitter, and sleeper test doubles so they do not rely on real waiting.

## Package Structure

`dev.franklindot04.learnjava.reliability` contains small framework-free classes. Production systems usually need durable stores, distributed coordination, mature telemetry, and battle-tested libraries.

## Commands

```bash
mvn -f examples/reliability/java-resilience-patterns/pom.xml test
```
