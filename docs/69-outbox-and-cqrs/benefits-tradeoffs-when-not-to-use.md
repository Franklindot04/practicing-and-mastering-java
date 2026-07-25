# Benefits Tradeoffs And When Not To Use

Outbox and CQRS can help with reliability and clarity, but they also add moving parts.

## Outbox Benefits

- Reduces dual-write risk.
- Gives failed publication a durable record.
- Supports retry and investigation.
- Separates user request latency from publication work.

## Outbox Tradeoffs

- Requires an outbox table or equivalent durable record.
- Requires a publisher process or job.
- Can publish duplicate events.
- Needs monitoring, retention, and cleanup.

## CQRS Benefits

- Keeps write rules focused.
- Lets read models match screen or reporting needs.
- Makes command and query responsibilities clearer.
- Can reduce pressure to make one model serve every purpose.

## CQRS Tradeoffs

- Adds conceptual complexity.
- Read models may become stale.
- More mappings and synchronization logic may be needed.
- Teams may over-separate a simple application.

## When Not To Use These Patterns

Avoid these patterns when:

- A direct transaction is simple and sufficient.
- There is no real asynchronous consumer.
- The team cannot monitor delayed work.
- The data model is small and does not have competing read/write needs.
- The pattern would hide a simple business operation behind unnecessary ceremony.

## Healthy Use

Use outbox when durable state changes and event publication must be coordinated.

Use CQRS when reads and writes have different enough needs that separating their models makes the design clearer.
