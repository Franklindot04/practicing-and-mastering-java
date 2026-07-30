# Reliability Quiz Answer Key

## Vocabulary And Failure Models

1. Reliability is correct behavior over time; availability is successful service when requested; durability is preserving accepted data; resilience is absorbing and adapting to failure; recoverability is returning to verified good state.
2. False. A service can answer every request while returning wrong or misleading data, which is available but unreliable.
3. Transient is temporary, persistent lasts until a change, intermittent appears irregularly, partial affects only part of a workflow, omission means an expected response or side effect is missing, and timing means work is too late.

## Timeouts Retries And Backoff

1. Attempts 2 through 5 are 100 ms, 200 ms, 400 ms, and 500 ms after the cap. Jitter may reduce or vary those values.
2. False in normal request handling. Work after the caller deadline usually creates orphaned load unless it is an intentional background recovery task.
3. Timeout and 503 may be retryable when the operation is safe and budget remains. Validation error, card declined, and fingerprint mismatch are non-retryable without changed input.

## Circuit Breakers Bulkheads And Fallbacks

1. CLOSED allows calls and records outcomes. OPEN rejects calls after enough failures. HALF_OPEN allows controlled probes after open duration. Success closes; failed probe reopens.
2. Use a small semaphore or bounded executor for notification so it cannot starve payment or inventory. The size should come from measured latency and capacity, not a universal default.
3. False. A fallback that hides failed payment or stale critical data can reduce correctness. Fallbacks are reliable only when they preserve the important contract honestly.

## Idempotency Overload And Recovery

1. Store scoped key, request fingerprint, operation, status, stored response, creation time, expiration window, and error state. Production storage should be durable with uniqueness constraints.
2. Deadline-aware load shedding checks remaining time before admitting work. If the request cannot finish in time, it rejects early instead of consuming scarce resources.
3. Rollback returns to a prior version, roll-forward deploys a fix, replay reprocesses records, reconciliation compares and repairs state, and compensation offsets an irreversible action.

## Java Code Reading

1. Ignoring `InterruptedException` clears the cancellation signal. Restore it with `Thread.currentThread().interrupt()` and stop or propagate.
2. `containsKey` then `put` is not atomic. Two threads can both execute side effects. Use `putIfAbsent`, locking, or a database uniqueness constraint.
3. Without `finally`, exceptions leak permits. Over time the bulkhead rejects all work even when no real work is active.

## Scenario Diagnosis

1. The database slowdown increases latency, retries multiply calls, pools saturate, and health checks fail. Mitigate with timeouts, jittered retry budgets, circuit breakers, bulkheads, and load shedding.
2. A payment ledger usually needs very low RPO because losing accepted financial data is severe. RTO depends on business requirements, but correctness and recoverability should outrank cosmetic availability.
3. Hypothesis: checkout remains successful while notification latency is injected. Limit to staging or a test tenant, abort on latency or error thresholds, and verify degraded diagnostics and recovery events.

## Multiple Choice

1. A. Request success rate is a measured service-level indicator; the other options are organizational facts, not service behavior.
2. A. Setting status to a value is naturally idempotent. Charging, appending, and incrementing repeat side effects.
3. A. Bounded concurrency protects against saturation. Infinite queues, hidden retries, and missing timeouts increase overload risk.
