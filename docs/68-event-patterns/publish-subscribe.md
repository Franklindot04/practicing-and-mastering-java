# Publish Subscribe

Publish/subscribe is a pattern where a producer publishes an event and interested consumers subscribe to receive it.

## Concept

```text
Producer
  |
  v
Published event
  |
  +--> Subscriber A
  |
  +--> Subscriber B
```

The producer does not call each subscriber directly.

## Why It Helps

- New consumers can be added without changing producer code.
- Different consumers can react for different reasons.
- The producer can focus on recording the fact.

## Design Risk

Publish/subscribe can hide dependencies. If ten consumers depend on one event, changing the event contract may affect more of the system than expected.

## Questions

- Who owns the event contract?
- Which consumers are required for the business process?
- Which consumers are optional side effects?
- How will the team know when a subscriber is failing?
