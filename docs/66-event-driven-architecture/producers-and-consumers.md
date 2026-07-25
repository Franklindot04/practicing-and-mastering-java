# Producers And Consumers

Event-driven systems usually have producers and consumers.

## Producers

A producer creates an event when something meaningful happens.

```text
Task API
  |
  v
TaskCompleted event
```

A good producer:

- Emits events only after the business fact is valid.
- Uses names that describe facts, not implementation details.
- Includes enough data for consumers to understand the event.
- Avoids assuming exactly which consumers will exist.

## Consumers

A consumer reacts to an event.

```text
TaskCompleted event
  |
  +--> Update reporting view
  |
  +--> Send completion notification
```

A good consumer:

- Handles duplicate events safely when possible.
- Records failures in a way operators can investigate.
- Does not rely on every other consumer finishing first.
- Treats event data as a contract, not a private object.

## Ownership

The producer owns the meaning of the event. Consumers own their own reactions.

This separation helps keep boundaries clear:

- The task boundary can say `TaskCompleted`.
- The reporting boundary can update metrics.
- The notification boundary can send a message.

The producer should not need to know the internals of reporting or notifications.
