# Traffic Reliability Solutions

## Exercise 1: Plan A Canary

A reasonable canary starts with a small percentage, such as a conceptual 5 or 10 percent, then increases only after observing error rate, latency percentiles, retry volume, resource saturation, and business correctness. Rollback criteria should be written before traffic shifts.

A canary may be rolled back even when HTTP status codes look healthy if message contents, audit behavior, or downstream side effects are incorrect.

## Exercise 2: Review Retry Safety

Retrying task creation after timeout can create duplicate tasks if the original request completed but the caller did not receive the response. A safer policy is to use strict timeouts, avoid automatic retries for non-idempotent creation, and add idempotency design before considering retries.

Idempotency keys can let the service recognize repeated create attempts as the same operation instead of creating duplicates.

