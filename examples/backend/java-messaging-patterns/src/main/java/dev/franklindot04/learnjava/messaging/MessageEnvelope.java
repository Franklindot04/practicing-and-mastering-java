package dev.franklindot04.learnjava.messaging;

import java.time.Instant;
import java.util.Objects;

public record MessageEnvelope(
        String messageId,
        String correlationId,
        Instant timestamp,
        String messageType,
        String payload
) {
    public MessageEnvelope {
        Objects.requireNonNull(messageId, "messageId must not be null");
        Objects.requireNonNull(correlationId, "correlationId must not be null");
        Objects.requireNonNull(timestamp, "timestamp must not be null");
        Objects.requireNonNull(messageType, "messageType must not be null");
        Objects.requireNonNull(payload, "payload must not be null");
    }
}
