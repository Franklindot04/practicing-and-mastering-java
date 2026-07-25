package dev.franklindot04.learnjava.backend.messaging;

public interface MessageProducerPort {
    void publish(MessageEnvelope envelope);
}
