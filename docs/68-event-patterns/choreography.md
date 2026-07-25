# Choreography

Choreography is a workflow style where each participant reacts to events and decides its own next step.

## Concept

```text
OrderCreated
  |
  v
Payment component reacts
  |
  v
PaymentAccepted
  |
  v
Fulfillment component reacts
```

There is no single central coordinator telling every participant what to do.

## Benefits

- Participants can remain loosely coupled.
- Each component owns its local decision.
- New reactions can sometimes be added without changing a coordinator.

## Tradeoffs

- The overall workflow can be hard to see.
- Debugging requires following a chain of events.
- Business rules can become scattered across consumers.
- Failure handling needs careful design.

## Questions

- Where is the workflow documented?
- Which events represent required progress?
- What happens when one participant cannot continue?
- How do operators see the whole business process?
