# Retry Exercises

## Exercise 1: Build A Retry Policy

Difficulty: Intermediate

Concepts practiced: timeouts, retry limits, backoff, jitter.

Problem statement:
Create a retry policy for a task API calling a notification boundary. Include timeout, maximum attempts, backoff style, and which errors should not be retried.

Hints:

- Validation errors should not be retried.
- Temporary network errors may be retried.
- Retries need limits.

Stretch challenge:
Explain how your policy avoids a retry storm.

## Exercise 2: Response Lost

Difficulty: Intermediate

Concepts practiced: uncertain outcomes, duplicate side effects.

Problem statement:
The task API sends a request to create a notification. The notification service succeeds, but the response is lost. Explain what could go wrong on retry and how idempotency changes the outcome.

Hints:

- The caller does not know whether downstream work happened.
- Duplicate sends can confuse users.
- Store request identity somewhere durable.

Stretch challenge:
Draw the request and retry timeline.

