# Java Event-Driven Foundations

This standalone Maven module demonstrates Stage 26 event-driven architecture foundations with plain Java.

## What It Demonstrates

- typed event envelopes
- deterministic in-memory event bus
- multiple consumers and handler failure isolation
- duplicate delivery and idempotent consumers
- unsafe check-then-act handling versus safer atomic marking
- bounded retry decisions without `Thread.sleep`
- dead-letter storage and replay after correction
- out-of-order aggregate-version handling
- transactional outbox simulation with relay retry and duplicate publication safety

## Run

```bash
mvn test
```

## Educational Limits

The in-memory bus is not a durable broker. The outbox is not a real database table. The retry simulator does not provide network delivery guarantees. These examples do not implement durable messaging, production-grade broker behavior, exactly-once guarantees, real distributed transactions, production schema registry behavior, durable event storage, or production saga orchestration.

Stage 27 is where concrete messaging and streaming technology belongs.
