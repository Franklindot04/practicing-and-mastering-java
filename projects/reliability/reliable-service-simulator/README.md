# Reliable Service Simulator

A deterministic in-memory checkout simulator for Stage 24 reliability practice. It validates requests, applies request deadlines, suppresses duplicate idempotency keys, retries transient inventory failures with capped exponential backoff, uses a circuit breaker and bulkhead around inventory, sheds overload, degrades notification failure, and records recovery decisions.

## Architecture

`ReliableCheckoutService` coordinates simulated dependencies:

- inventory: critical dependency protected by retry, circuit breaker, and bulkhead
- payment: critical dependency that fails clearly on non-retryable failure
- shipping quote: deterministic local step
- notification: non-critical dependency with graceful degradation

`FailurePlan` supplies deterministic dependency behavior. `RecoveryJournal` records operational decisions. `ReliabilityMetrics` exposes accepted, rejected, degraded, payment, and inventory counters.

## Request Processing Flow

validate request -> check deadline -> admit or shed load -> verify idempotency key and fingerprint -> reserve inventory with transient-only retry and backoff -> charge payment -> select shipping quote -> send notification or degrade -> return structured diagnostics.

## Failure Scenarios

The tests cover healthy checkout, transient inventory retry success, fatal payment failure, notification degradation, breaker open and fast failure, half-open recovery, duplicate suppression, changed fingerprint rejection, bulkhead saturation, expired deadlines, and recovery journal evidence.

## Commands

```bash
mvn -f projects/reliability/reliable-service-simulator/pom.xml test
```

## Limitations

This is a simulator, not a production payment or order system. It uses no real network, database, queue, payment processor, or resilience library. Production systems need durable idempotency storage, transactional state, distributed coordination, real telemetry, security review, and operational runbooks.
