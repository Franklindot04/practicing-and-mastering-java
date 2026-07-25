# Monitoring Event-Driven Systems

Monitoring event-driven systems means watching both event movement and consumer outcomes.

## Useful Signals

- Events waiting to be published.
- Events waiting to be processed.
- Oldest unprocessed event age.
- Consumer success count.
- Consumer failure count.
- Retry count.
- Duplicate event count.
- Poison event count.
- End-to-end processing delay.

## Conceptual View

```text
Producer
  |
  v
Event records
  |
  v
Consumer
  |
  v
Consumer result
```

Each step can be healthy or failing.

## Questions

- Is new work arriving?
- Is old work draining?
- Which consumer is failing?
- Are failures user-visible?
- Is retry making progress or repeating the same failure?
