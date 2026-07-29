# Reliable Service Simulator

A deterministic in-memory checkout simulator. It validates requests, applies deadlines, idempotency, retry, circuit breaker, bulkhead, load shedding, fallback, and recovery-journal recording around simulated inventory, payment, shipping, and notification dependencies.

## Request Flow

validate request -> reject expired or overloaded work -> check idempotency -> reserve inventory with retry -> charge payment clearly -> quote shipping -> send notification with graceful degradation -> record diagnostics.

## Commands

```bash
mvn -f projects/reliability/reliable-service-simulator/pom.xml test
```

This is a simulator, not a production payment or order system. It uses no network, database, queue, or external resilience library.
