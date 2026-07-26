# Resilience And Fault Isolation

Resilience is the ability to absorb, limit, and recover from failures. It depends on behavior, not only on extra servers.

## Fault Tolerance

Fault tolerance means a system can continue operating through some failures.

Examples:

- One API instance crashes but others continue serving traffic.
- A worker retries a temporary downstream timeout.
- A read path uses a replica when the primary is overloaded.

Fault tolerance has limits. A design should state which failures it handles and which ones require manual recovery.

## Fault Isolation

Fault isolation prevents one failing area from consuming the whole system.

```text
Without isolation:
Slow Reports -> Shared Thread Pool -> Checkout Slow

With isolation:
Slow Reports -> Reports Pool
Checkout     -> Checkout Pool
```

## Bulkheads

Bulkheads separate capacity so one workload cannot exhaust everything.

Examples:

- Separate thread pools.
- Separate worker queues.
- Per-tenant limits.
- Separate connection pools for critical and non-critical paths.

## Timeouts

Timeouts stop a request from waiting forever. Without timeouts, slow dependencies can turn into thread exhaustion.

Questions:

- What timeout is shorter than the user-facing deadline?
- What should happen after timeout?
- Is the downstream action safe to retry?

## Retries

Retries can recover from temporary failures. They can also amplify load.

Retry only when:

- The failure is likely temporary.
- The operation is idempotent or otherwise safe.
- There is a retry limit.
- There is backoff or pacing.
- Observability shows retry behavior.

## Circuit Breakers

A circuit breaker stops calling a dependency that is failing repeatedly.

```text
Closed -> calls allowed
Open   -> calls blocked or fallback used
Half   -> small probe allowed
```

Circuit breakers protect the caller and give the dependency time to recover. They do not fix the dependency.

## Backpressure

Backpressure tells callers or upstream systems to slow down when downstream capacity is saturated.

Examples:

- Reject excess requests with a clear status.
- Limit queue intake.
- Pause consumers.
- Apply per-client rate limits.

Backpressure is often more honest than accepting unlimited work that cannot be completed.
