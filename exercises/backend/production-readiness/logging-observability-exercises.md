# Logging And Observability Exercises

## Exercise 1: Pick The Right Log Level

Difficulty: Beginner

Concepts practiced: log levels, operational judgment

Problem statement: Choose a log level for each event: app started, invalid task title, database unavailable, task created, detailed request parsing.

Hints:

- Not every unusual event is an error.
- Debug-level details are often too noisy for normal operation.
- Client mistakes should not flood error logs.

Stretch challenge: Rewrite one event as a structured log message.

## Exercise 2: Remove Sensitive Log Data

Difficulty: Beginner

Concepts practiced: safe logging, PII, secret handling

Problem statement: A login handler logs email, password, raw token, and IP address. Decide what should be removed, masked, or kept.

Hints:

- Passwords and raw tokens should not be logged.
- Email may need masking depending on policy.
- Keep enough context to investigate abuse safely.

Stretch challenge: Propose a safe log message for a failed login attempt.

## Exercise 3: Health Check Design

Difficulty: Intermediate

Concepts practiced: health checks, readiness, liveness

Problem statement: Design a simple health response for a local task API. Include status and at least two components.

Hints:

- Health checks should be fast.
- Avoid exposing sensitive details.
- A process can be alive but not ready.

Stretch challenge: Explain how a database outage should affect readiness.
