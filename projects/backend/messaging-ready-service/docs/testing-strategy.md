# Testing Strategy

Messaging code should be tested in layers.

## Pure Java Tests

Pure Java tests verify:

- Envelope validation
- Domain event mapping
- Retry decisions
- Error classification
- Idempotency behavior
- Observability summaries

These tests do not need Kafka, RabbitMQ, Docker, or network access.

## Adapter Tests Later

Future broker adapters can be tested separately with contract tests or local integration environments.

Those tests should not be required for the pure domain model to compile.

## Operational Tests Later

Future operational tests can cover:

- Retry limit behavior
- Dead-letter routing
- Graceful shutdown
- Consumer lag or queue-depth alerts
- Replay safety
