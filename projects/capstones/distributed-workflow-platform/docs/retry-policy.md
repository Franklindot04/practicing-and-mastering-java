# Retry Policy

Retries are represented by attempts and exponential backoff capped at sixty seconds. Tests validate the representation without sleeping.

## Purpose

The retry policy demonstrates bounded recovery. It teaches that retries are a budgeted decision, not an automatic loop that runs until success.

## Assumptions And Constraints

- Backoff is calculated, not slept, in default tests.
- Attempt counts live with task state.
- Poison tasks and irreversible failures bypass normal retry and move to quarantine.
- The simulation does not call real dependencies.

## Trade-Offs

Exponential backoff reduces pressure on failing dependencies, but it increases completion latency. A fixed delay is easier to predict, but it can synchronize retry storms. A jittered policy is often better in production, though deterministic tests would need seeded randomness or direct schedule assertions.

## Operational Implications

Operators should monitor retry count, retry age, task type, and dependency status. High retry volume can mean a dependency outage, bad input, capacity pressure, or a code regression. Retrying without classification can turn a small failure into a larger incident.

## Future Improvements

Add maximum attempt policies per task type, retry-after hints, jittered backoff with seeded tests, and explicit retry exhaustion events.
