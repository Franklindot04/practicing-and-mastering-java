# Reliability, Resilience, And Recovery Solutions

## Solution 1

Reliability is valid behavior over time; availability is answering requests; durability is preserving accepted data; resilience is absorbing failure; recoverability is returning to verified good state; maintainability is ease of change and operation. The examples map accordingly: valid data is reliability, restore is durability and recoverability, restart is recoverability only if state is correct, and a usable runbook is maintainability.

## Solution 2

The service is available because it responds, but unreliable because the response violates the business contract. A misleading HTTP 200 can be worse than a clear failure because callers may stop retrying or start recovery from false information.

## Solution 3

Transient: one inventory timeout. Persistent: bad payment credentials. Intermittent: race in duplicate handling. Partial: payment succeeds but notification fails. Timing: shipping quote arrives after deadline. Omission: consumer acknowledges but never sends notification.

## Solution 4

One reasonable split might reserve 50 ms for validation, 200 ms inventory, 250 ms payment, 100 ms notification, and 100 ms response assembly. The point is not the exact numbers; each inner timeout must fit inside the outer deadline with room for cleanup and retry decisions.

## Solution 5

Validation error, fingerprint mismatch, and card declined are non-retryable without changed input. Connection timeout and inventory HTTP 503 may be retryable if the operation is idempotent and the request budget remains.

## Solution 6

Attempt 2: 50 ms. Attempt 3: 100 ms. Attempt 4: 200 ms. Attempt 5: capped at 300 ms. Jitter may reduce each value depending on the chosen policy.

## Solution 7

Full jitter spreads callers across the whole delay range. Equal jitter keeps a minimum delay and randomizes the rest. Tests should inject randomness so delay assertions are deterministic and failures are reproducible.

## Solution 8

A reasonable budget could be max 3 attempts, total elapsed limit 1.5 seconds, capped delay 200 ms, stop if the next delay would exceed the deadline, and retry only classified transient failures. There is no universal default; dependency risk and caller expectations decide.

## Solution 9

In CLOSED, calls run and failures are counted. After threshold is reached, the breaker opens and rejects calls. After open duration, one or a few probes enter HALF_OPEN. Success closes the breaker; failed probe reopens it.

## Solution 10

For low volume, require a minimum-call count to avoid opening on one isolated failure. A threshold of several failures over a rolling window and a modest open duration may be safer. False positives reject healthy traffic; false negatives allow overload to continue.

## Solution 11

Give the optional dependency a small semaphore, such as 4 to 8 concurrent calls, or a bounded executor separate from critical checkout work. The exact size should come from latency and capacity evidence. Always reject or degrade when full instead of queueing forever.

## Solution 12

Cached recommendations and skipped notification are usually safe if labeled. Read-only mode can be safe during storage recovery. Stale inventory and default shipping quote may be unsafe if they cause oversell or undercharge. Fallback safety depends on correctness impact.

## Solution 13

Record tenant or user scope, idempotency key, request fingerprint, status, stored response, creation time, expiration time, and operation type. Durable storage and a uniqueness constraint are preferred in production.

## Solution 14

Check-then-insert is not atomic: both threads can see no record and execute side effects. Use atomic `putIfAbsent`, a lock, or database uniqueness around the key and store an in-progress record before side effects.

## Solution 15

Overload is more offered work than useful capacity. Saturation is a resource at its limit. Admission control decides whether work enters. Load shedding rejects work early to preserve core behavior.

## Solution 16

Check `budget.isExpired()` before acquiring the permit. If expired, increment rejection metrics and return a deadline failure. Then try acquiring the permit; if unavailable, reject as saturated. Release the permit in `finally`.

## Solution 17

The slow database increases request duration; retries multiply database calls; threads fill while waiting; health checks fail because workers are unavailable. Mitigations include shorter timeouts, retry budgets with jitter, circuit breakers, bulkheads, and load shedding.

## Solution 18

Return a degraded status such as `checkout accepted; notification deferred`, record a recovery event, and enqueue or journal notification repair. Do not tell the user notification was sent.

## Solution 19

A personal blog can tolerate longer RTO/RPO. An online classroom may need moderate recovery to avoid lesson disruption. A payment ledger needs strict RPO and careful correctness, often prioritizing no data loss over speed.

## Solution 20

A good DR outline includes trigger conditions, owner, escalation, backup source, restore target, dependency order, communication cadence, smoke tests, data validation, and rollback or failback decision points.

## Solution 21

Hypothesis: checkout success remains above target while notification may degrade. Blast radius: staging or a small test tenant. Abort if latency or error rate exceeds threshold. Learning: whether deadlines, retries, and breaker settings behave as expected.

## Solution 22

The runbook lacks symptoms, scope checks, recent-change review, dependency checks, owner, escalation, rollback criteria, customer communication, validation, and post-incident evidence. Restart may help but is not a full recovery plan.

## Solution 23

The code swallows interruption. It should restore interrupt status with `Thread.currentThread().interrupt()` and exit or propagate so cancellation and shutdown can work.

## Solution 24

The executor should validate max attempts, classify exceptions, check budget before attempts and sleeps, calculate capped backoff, call an injected sleeper, preserve interrupt status, and preserve the original failure when exhausted. Tests should cover success, transient success, fatal failure, exhausted retries, budget exhaustion, delay cap, and invalid config.

## Solution 25

Start with SLO impact and time window, then inspect latency metrics, retry counts, duplicate idempotency records, notification error rates, dependency health, and recent changes. Use recovery journal entries to see whether checkout degraded honestly or repeated side effects.

## Solution 26

Ask whether the internal tool needs such low RTO, whether data conflicts are acceptable, who will operate active-active, how failback works, and whether backups plus tested restore or active-passive is enough. More architecture is not automatically more reliable.
