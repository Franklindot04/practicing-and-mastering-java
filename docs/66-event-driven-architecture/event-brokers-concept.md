# Event Brokers Concept

An event broker is a component that sits between producers and consumers.

This note is conceptual only. It does not introduce any broker product, cloud service, queue, stream, or runnable infrastructure.

## Why Brokers Exist

Without a broker, producers may need to call every consumer directly.

```text
Producer
  |
  +--> Consumer A
  |
  +--> Consumer B
  |
  +--> Consumer C
```

That makes the producer responsible for knowing all consumers.

With a broker concept, the producer publishes the event to an intermediary.

```text
Producer
  |
  v
Event broker concept
  |
  +--> Consumer A
  |
  +--> Consumer B
  |
  +--> Consumer C
```

## Conceptual Responsibilities

A broker may help with:

- Accepting events from producers.
- Making events available to consumers.
- Buffering events when consumers are temporarily unavailable.
- Tracking which consumers still need to process work.
- Separating producers from consumer locations.

## What A Broker Does Not Decide

A broker does not automatically decide:

- Whether an event name is meaningful.
- Whether payload data is safe to share.
- Whether a consumer is idempotent.
- Whether failure handling is acceptable.
- Whether the architecture is simpler than a direct call.

Technology can move events. Design still decides whether the events are useful.
