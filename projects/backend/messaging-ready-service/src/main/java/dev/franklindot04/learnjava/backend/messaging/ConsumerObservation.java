package dev.franklindot04.learnjava.backend.messaging;

public record ConsumerObservation(
        String messageType,
        CorrelationId correlationId,
        String outcome,
        int attempts
) {
}
