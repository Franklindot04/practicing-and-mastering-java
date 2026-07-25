# Event Notification

Event notification is a pattern where an event tells consumers that something changed, but does not carry all the data consumers may need.

## Example

```text
TaskUpdated
  taskId: 42
```

A consumer that needs details may call back to the owning system or read from its own data source.

## Why Use It

- Payloads stay small.
- Consumers always fetch the latest current state when they need it.
- Sensitive or large fields are not copied into every event.

## Tradeoffs

- Consumers may need extra calls.
- The source system can become a bottleneck.
- A consumer may observe newer state than the state that existed when the event was emitted.

## Questions

- Is the event enough to react safely?
- Does the consumer need historical state or current state?
- What happens if the source system is unavailable when the consumer reacts?
