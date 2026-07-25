# Benefits And Tradeoffs

Event-driven architecture is useful when the design needs independent reactions to meaningful facts. It is not automatically better than direct calls.

## Benefits

- Producers can be less coupled to consumers.
- Several consumers can react to the same event.
- Non-critical work can happen outside the main request.
- Events can create a useful history of domain activity.
- Systems can absorb temporary consumer slowness more gracefully.

## Tradeoffs

- Debugging can require following work across time and boundaries.
- Consumers may receive duplicate events.
- Events may arrive later than users expect.
- Ordering can be hard when several events affect the same entity.
- Schema changes require compatibility planning.
- Failures may be delayed instead of immediately visible.

## Design Questions

- What business fact does this event represent?
- Who owns the source data?
- Which consumers need the event now?
- Which consumers might need it later?
- What happens if a consumer processes the same event twice?
- What happens if a consumer is unavailable for an hour?
- How will operators know an event flow is failing?

## Healthy Use

Use events when they clarify boundaries and make independent reactions explicit.

Avoid events when they hide a simple required call behind extra indirection.
