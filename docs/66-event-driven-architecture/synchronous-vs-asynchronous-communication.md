# Synchronous Vs Asynchronous Communication

Synchronous communication means the caller waits for a response before continuing.

Asynchronous communication means the sender records or sends work and continues without waiting for every reaction to finish.

## Synchronous Flow

```text
Client
  |
  v
Task API ----request----> Notification Service
  ^                            |
  |                            v
  +----------response----------+
```

The caller knows whether the immediate request succeeded, failed, or timed out.

## Asynchronous Flow

```text
Task API
  |
  v
TaskCreated event
  |
  +--> Notification consumer
```

The API can finish its own work before the notification is sent.

## When Synchronous Fits

- The user needs an immediate answer.
- The next step depends on the response.
- Failure should block the current operation.
- The call is simple and reliable enough for the request path.

## When Asynchronous Fits

- Work can happen after the main action succeeds.
- Several consumers may react independently.
- Temporary consumer failure should not always fail the user request.
- The system needs a record of important domain changes.

## Tradeoff

Asynchronous communication can improve responsiveness and decoupling, but it makes timing, ordering, retries, duplicate handling, and debugging more important.
