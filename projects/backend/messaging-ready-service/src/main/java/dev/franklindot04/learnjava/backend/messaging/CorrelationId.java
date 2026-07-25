package dev.franklindot04.learnjava.backend.messaging;

public record CorrelationId(String value) {
    public CorrelationId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("correlation id must not be blank");
        }
    }
}
