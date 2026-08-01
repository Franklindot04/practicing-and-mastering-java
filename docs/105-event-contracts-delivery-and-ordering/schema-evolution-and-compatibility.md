# Schema Evolution And Compatibility

Event contracts change while producers and consumers are running different versions. Safe evolution lets producers roll forward without breaking consumers and lets consumers tolerate events from older or newer producers.

## Compatibility

Backward compatibility means new consumers can read old events. Forward compatibility means old consumers can tolerate new events.

Additive changes are usually safest:

```java
record OrderSubmittedV2(
        String orderId,
        String customerId,
        long totalCents,
        String currency,
        String salesChannel
) {}
```

If `salesChannel` is optional or has a default, older events can still be interpreted.

## Risky Changes

Renamed fields can break consumers that deserialize by name.

Removed fields break consumers that still require them.

Changed meaning is a semantic breaking change even if the field type stays the same. Changing `totalCents` from pre-tax to post-tax is dangerous because code may still compile while business behavior becomes wrong.

Changed type can break both deserialization and calculations.

## Versioning Strategies

One strategy is versioned event names, such as `OrderSubmittedV1` and `OrderSubmittedV2`. This is explicit but can multiply handlers.

Another strategy is a stable event type with `eventVersion` in the envelope. This centralizes the name but requires upcasting or branching during deserialization.

Upcasting converts older payloads into the current in-memory representation:

```java
OrderSubmittedV2 upcast(OrderSubmittedV1 old) {
    return new OrderSubmittedV2(old.orderId(), old.customerId(), old.totalCents(), "USD", "UNKNOWN");
}
```

Upcasting is a concept here, not a production schema registry implementation.

## Schema Registry Concepts

A schema registry is a shared place to publish schemas, compatibility rules, and versions. Stage 26 only introduces the concept. A real registry has tooling, access control, compatibility checks, and deployment integration.

## Contract Testing

Contract tests verify that producers publish what consumers expect and that consumers tolerate allowed evolution. Useful checks include:

- required fields are present
- unknown fields are ignored
- optional fields use safe defaults
- semantic examples remain valid
- incompatible changes are rejected before rollout

## Rolling Upgrades

During a rolling upgrade, old and new producers or consumers may run at the same time. Safe event evolution assumes mixed versions. Deploy consumers that tolerate the new shape before producers require it.

## Evolution Rules

- Prefer additive fields.
- Make new fields optional first.
- Define defaults explicitly.
- Avoid renaming fields in place.
- Do not remove fields until all consumers are known to be safe.
- Treat semantic changes as breaking changes.
- Keep examples of old and new events in tests.
