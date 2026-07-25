# Tracing Logging And Correlation

Asynchronous flows need identifiers that survive across service boundaries.

## Correlation Identifiers

A correlation identifier links the original request to later asynchronous work.

```text
HTTP request corr-123
  |
  v
TaskCreated message corr-123
  |
  v
Notification consumer log corr-123
```

## Structured Logging

Useful consumer logs include:

- `messageId`
- `correlationId`
- `messageType`
- `consumerName`
- `attempt`
- `outcome`
- `failureClass`

Avoid logging secrets, tokens, passwords, or large payloads.

## Distributed Tracing

Tracing asynchronous flows usually requires propagating trace context in message metadata.

The trace should show the producer span and the consumer span even though they happen at different times.

## Common Mistakes

- Logging only "failed to process message" without ids.
- Dropping correlation ids when publishing.
- Logging full payloads that contain unnecessary sensitive data.
- Treating trace gaps as harmless in critical workflows.
