# Logging Basics

Logs are timestamped records of application events. They help developers understand startup, requests, failures, background work, and important state changes.

## Logging Levels

- `TRACE`: extremely detailed diagnostic information. Usually disabled outside local debugging.
- `DEBUG`: useful details for developers during investigation.
- `INFO`: normal important events, such as startup or a task being created.
- `WARN`: something unexpected happened, but the app can continue.
- `ERROR`: something failed and needs attention.

Use levels deliberately. If every message is `ERROR`, real errors become harder to find.

## What To Log

Useful log entries often include:

- Application startup and shutdown.
- Important configuration choices that are safe to reveal.
- Request handling milestones.
- Business events such as creating or completing a task.
- Validation failures at a summary level.
- Unexpected exceptions with enough context to debug.

## What Not To Log

Avoid logging:

- Passwords.
- Password hashes.
- Raw authentication tokens.
- JWT contents.
- API keys.
- Private keys.
- Full payment or identity data.
- Entire request bodies when they may contain sensitive values.

If a value could harm a user or system when exposed, treat it as sensitive.

## Structured Logging Basics

Structured logs use consistent fields instead of only free-form text.

Free-form:

```text
Created task 42 for user 7
```

Structured-style:

```text
event=task.created taskId=42 userId=7
```

Real systems often emit JSON logs. Learning projects can start by choosing clear, repeatable key names.

## Correlation And Request IDs

A request ID is a unique value attached to one request. It lets you connect all logs produced while handling that request.

Example:

```text
requestId=abc-123 event=task.create.started
requestId=abc-123 event=task.create.completed taskId=42
```

Do not put secrets in request IDs. They should be identifiers, not credentials.

## Common Logging Mistakes

- Logging sensitive values.
- Logging too much at `INFO`.
- Logging errors without context.
- Catching an exception, logging it, and silently continuing when the app cannot recover.
- Using inconsistent field names.
- Depending on logs instead of tests for expected behavior.
