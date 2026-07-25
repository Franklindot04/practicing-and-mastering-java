# Fan Out

Fan-out means one event leads to several independent processing paths.

## Example

```text
OrderPaid
  |
  +--> Update reporting
  |
  +--> Start fulfillment
  |
  +--> Prepare receipt
  |
  +--> Record audit entry
```

Each consumer receives the same fact and decides what to do locally.

## When It Fits

- Several parts of the system care about the same event.
- Consumers can work independently.
- Failure in one reaction should not automatically block every other reaction.

## Tradeoffs

- More consumers means more operational visibility is needed.
- Event contracts become more important.
- Duplicate handling matters because each consumer may see the same event more than once.

## Learning Rule

Fan-out is not just "send more messages." It is one fact creating multiple independent responsibilities.
