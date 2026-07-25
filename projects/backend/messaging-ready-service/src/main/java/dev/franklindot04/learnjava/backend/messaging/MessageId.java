package dev.franklindot04.learnjava.backend.messaging;

public record MessageId(String value) {
    public MessageId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("message id must not be blank");
        }
    }
}
