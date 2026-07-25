package dev.franklindot04.learnjava.backend.messaging;

public interface DeadLetterPublisher {
    void publish(MessageEnvelope envelope, String reason, int attempts);
}
