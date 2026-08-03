# Retries Dead Letters And Replay

Retries are useful only when the failure may stop being true. Event-driven systems need policies that separate transient failures from permanent failures and preserve failed events for recovery.

## Failure Classification

A transient failure may succeed later: a timeout, temporary dependency outage, rate limit, or connection reset.

A permanent failure will not succeed without a change: invalid schema, unknown event type, impossible state transition, unsupported tenant, or missing required business data.

Retry eligibility should be explicit:

```java
enum FailureKind {
    TRANSIENT,
    PERMANENT
}

record RetryDecision(boolean retry, int nextAttempt, String reason) {}
```

## Bounded Retries Backoff And Jitter

Bounded retries stop after a maximum attempt count or retry budget. Exponential backoff increases delay between attempts. Jitter adds randomness so many consumers do not retry at the exact same instant.

Stage 26 examples should simulate backoff as data, not use real sleeping tests.

Retry storms happen when many events retry aggressively and overload a recovering dependency. Retry budgets protect the system by limiting how much retry traffic is allowed.

## Delayed Retry And Poison Events

Delayed retry keeps an event out of immediate processing until its retry time. A poison event repeatedly fails for the same permanent reason and should not block unrelated events forever.

After retry exhaustion, store the failed event with:

- event ID
- event type and version
- attempts
- failure class
- error message
- first and last failure time
- owning consumer
- correlation ID
- replay eligibility

## Dead Letters Parking Lots And Quarantine

A dead-letter queue or topic is a holding area for events that could not be processed. Stage 26 uses the term conceptually; it does not create a production queue or topic.

A parking-lot queue usually holds events that need delayed human or automated repair. Quarantine isolates suspicious or unsafe events until they can be inspected.

## Replay And Manual Intervention

Replay processes an event again after code, data, configuration, or contract issues are corrected. Replay must be safe for duplicates and old event versions.

Manual intervention should be auditable. Operators need to know who repaired the issue, what changed, which events were replayed, and how recovery was verified.

## Recovery Verification

Recovery is not complete when replay finishes. Verify business outcomes:

- order reached the expected status
- payment was not duplicated
- inventory was not over-reserved
- notification was not sent twice
- read model converged
- dead-letter volume returned to normal
