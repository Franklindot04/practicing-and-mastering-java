# Message Contracts Serialization And Schema Evolution

## Envelope Design

A useful message envelope separates metadata from payload:

```java
record MessageEnvelope<T>(
    String messageId,
    String eventType,
    int version,
    String contentType,
    String correlationId,
    String causationId,
    Map<String, String> headers,
    T payload
) {
}
```

Headers should carry routing and observability metadata. Payloads should carry business facts. Avoid leaking secrets, credentials, internal stack traces, or unnecessary personal data.

## Serialization Choices

JSON is readable and easy for examples, but it needs validation and clear compatibility rules. Avro and Protocol Buffers use stronger schemas and binary encodings. Schema registries can coordinate versions and compatibility checks, but they are operational infrastructure and must be treated as dependencies.

Every consumer should handle malformed messages, unsupported versions, missing required fields, extra unknown fields, and serialization failures. Untrusted payloads must not be deserialized into dangerous types or executed as code.

## Schema Evolution

Usually safer changes:

- add optional fields
- add fields with defaults
- add new event types instead of changing old meaning
- let tolerant readers ignore unknown fields

Risky changes:

- removing required fields
- renaming fields in place
- changing a field's meaning without a new version
- changing numeric units without clear contract migration
- narrowing enum values while old producers still exist

Backward compatibility lets new consumers read old messages. Forward compatibility lets old consumers survive newer messages. Deployment ordering should account for both.

## Contract Tests

Contract tests should verify that real serialized examples can be read by consumers. Keep sample messages in test resources when useful. Test malformed payloads and incompatible versions intentionally.

Contracts are owned APIs. A message contract deserves review, versioning, documentation, and observability just like an HTTP API.
