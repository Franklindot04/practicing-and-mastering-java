# Retry Solutions

## Exercise 1

A reasonable policy uses a short timeout, at most three attempts, exponential backoff with jitter, and no retries for validation, authorization, or permanent business-rule failures. The policy should record final failure instead of retrying forever.

## Exercise 2

Without idempotency, retrying may create or send a duplicate notification. With an idempotency key, the notification service can recognize the retry and return the original result instead of repeating the side effect.

