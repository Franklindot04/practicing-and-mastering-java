# Choreography Flow

## Scenario

An order workflow progresses because each participant reacts to events and emits the next meaningful event.

## Participants

- Order boundary.
- Payment boundary.
- Fulfillment boundary.
- Notification boundary.
- Operations reviewer.

## Flow

```text
OrderCreated
  |
  v
Payment boundary reacts
  |
  v
PaymentAccepted
  |
  v
Fulfillment boundary reacts
  |
  v
FulfillmentStarted
  |
  v
Notification boundary reacts
```

## Failure Considerations

- The full workflow is spread across participants.
- Operators need a way to see where the order stopped.
- Payment failure should produce a clear event or state for later handling.
- Each participant should avoid assuming every previous consumer finished successfully.

## Review Questions

- Where would you document the full business workflow?
- Which event shows payment success?
- What event or state should represent payment failure?
- When might orchestration be clearer than choreography?
