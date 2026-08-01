# Logical Clocks Causality And Identifiers

Logical clocks track ordering relationships without relying on physical time.

## Lamport Clock Concepts

A Lamport clock is a counter. A node increments it for local events and sends the value with messages. A receiver sets its counter to `max(local, received) + 1`.

```java
final class LamportCounter {
    private long value;

    long localEvent() {
        return ++value;
    }

    long receive(long received) {
        value = Math.max(value, received) + 1;
        return value;
    }
}
```

If event A causally happens before event B, A's Lamport value is less than B's. The reverse is not guaranteed: a smaller counter does not prove causality.

## Vector Clock Concepts

A vector clock keeps one counter per node. It can identify that one event happened before another, or that two events are concurrent. It costs more metadata and becomes harder as membership changes.

## Causal And Total Ordering

Causal ordering preserves cause and effect. Total ordering places every event in one sequence, even events that were concurrent. Total order often requires coordination or a single sequencer.

## Identifiers

`UUID.randomUUID()` is good for uniqueness in many applications but not for ordering. Sortable identifiers can help with indexes and logs, but sort order is not the same as causal order.

Duplicate identifiers can happen through bugs, bad randomness, clock misuse, or replay. Application protocols should decide whether repeated identifiers mean retry, duplicate command, conflict, or invalid input.

Idempotency relates identity to effect: if two requests carry the same command identity, the receiver can return the same outcome or reject unsafe duplicates.

## Review Questions

1. What is the Lamport receive rule?
2. Why can two events with different Lamport values still be concurrent?
3. When would a vector clock be more informative than a Lamport clock?
4. Why is sortable identity not the same as causality?
