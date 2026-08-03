# Retry Dead Letter And Replay Infrastructure

## Retry Classification

Retry only when repetition can plausibly succeed. A temporary database outage, broker leader movement, HTTP 503, or rate limit may be retryable. Malformed JSON, unsupported schema version, missing required field, or invalid tenant is usually permanent until repaired.

Use retry metadata:

- attempt count
- first failure time
- last failure time
- failure class
- original destination
- original offset or delivery tag when available
- correlation ID

## Retry Designs

Immediate retry is simple but can amplify load. Delayed retry uses retry topics, retry queues, scheduled messages, or external schedulers. Exponential delay and jitter reduce synchronized retry storms. Retry budgets protect the system from infinite work.

Retries affect ordering. Moving one record to a delayed retry destination can let later records for the same key proceed unless the design explicitly blocks them.

## Dead Letters And Parking Lots

Dead-letter topics, dead-letter exchanges, dead-letter queues, quarantine stores, and parking-lot destinations hold messages that need repair or manual review. They require ownership, alerts, retention, privacy review, and replay tooling.

A dead-letter destination should not be a silent archive. It is an operational queue with a service-level expectation.

## Replay

Replay can rebuild projections, recover from bugs, or reprocess repaired messages. Replay tooling should make the target, time range, schema version, side-effect mode, and operator identity explicit.

Do not replay into handlers that send emails, charge cards, or call external systems unless those effects are disabled or protected by strong idempotency. Message persistence and replay support do not guarantee safe business repair.
