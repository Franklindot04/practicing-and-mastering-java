# Transactional Messaging Problem

The transactional messaging problem appears when one business action needs both a state change and a message or event.

## Example

```text
Create order request
  |
  +--> Save order in database
  |
  +--> Publish OrderCreated event
```

The system wants both actions to happen together. In practice, one can succeed while the other fails.

## Failure Cases

```text
Save order succeeds
Publish event fails
```

Consumers never learn about the order.

```text
Publish event succeeds
Save order fails
```

Consumers react to an order that does not exist.

## Why It Matters

The user may see a successful response while downstream work never happens. Or consumers may process an event that does not match durable state.

## Design Questions

- Which fact must be durable before an event is visible?
- What should happen if publishing fails after the state change?
- Can the operation be retried safely?
- How will operators detect unpublished events?
