package dev.franklindot04.learnjava.backend.messaging;

import java.time.Instant;
import java.util.Objects;

public record MessageEnvelope(
        MessageId messageId,
        CorrelationId correlationId,
        Instant occurredAt,
        String messageType,
        String payload
) {
    public MessageEnvelope {
        Objects.requireNonNull(messageId, "messageId must not be null");
        Objects.requireNonNull(correlationId, "correlationId must not be null");
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");
        Objects.requireNonNull(messageType, "messageType must not be null");
        Objects.requireNonNull(payload, "payload must not be null");
    }
}
