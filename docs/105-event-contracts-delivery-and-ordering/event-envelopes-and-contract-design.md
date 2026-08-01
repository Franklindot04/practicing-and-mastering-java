# Event Envelopes And Contract Design

An event contract has two parts: the envelope and the payload. The envelope carries operational and routing information. The payload carries the business fact.

```java
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

record EventEnvelope<T>(
        String eventId,
        String eventType,
        int eventVersion,
        String source,
        Instant occurredAt,
        String correlationId,
        String causationId,
        String tenantKey,
        String partitionKey,
        T payload,
        Map<String, String> metadata
) {
    EventEnvelope {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(eventType);
        if (eventVersion < 1) {
            throw new IllegalArgumentException("eventVersion must be positive");
        }
        Objects.requireNonNull(source);
        Objects.requireNonNull(occurredAt);
        Objects.requireNonNull(correlationId);
        Objects.requireNonNull(payload);
        metadata = Map.copyOf(metadata);
    }
}
```

## Envelope Fields

`eventId` uniquely identifies one event instance. Consumers use it for deduplication and processed-event tracking.

`eventType` names the contract, such as `OrderSubmitted`.

`eventVersion` identifies the payload contract version.

`source` names the producing service, module, or bounded context.

`occurredAt` records event time. It is not the same as processing time.

`correlationId` groups related events and requests across a workflow.

`causationId` points to the command, event, or request that caused this event.

`tenantKey` supports multi-tenant routing and isolation when relevant.

`partitionKey` groups events that need per-key ordering, such as all events for one order.

`payload` contains the business data.

`metadata` carries non-business details such as trace IDs, content type, producer version, or validation result. Metadata should not hide required business facts.

## Validation And Serialization Boundary

Validate events before publication and at the consumer boundary. The serialization boundary is where an in-memory Java record becomes bytes or text and later returns to a Java type. Compatibility problems often appear there.

Stage 26 examples can validate records directly. Production systems also need validation around serialization, deserialization, malformed input, authorization, and schema compatibility.

## Schema And Contract Ownership

The producer owns the event contract because it owns the fact. Contract ownership includes documentation, compatibility rules, rollout plans, and deprecation.

Consumers own tolerance. A tolerant consumer ignores unknown fields, handles optional fields safely, and fails clearly when required meaning is missing.

## Contract Design Checklist

- Is the event name a clear business fact?
- Is there a stable event ID?
- Can events in the same aggregate be partitioned by a stable key?
- Are correlation and causation propagated?
- Is the payload typed rather than a loose map?
- Are required fields validated at the boundary?
- Is metadata separate from business meaning?
- Does the contract say who owns future changes?
