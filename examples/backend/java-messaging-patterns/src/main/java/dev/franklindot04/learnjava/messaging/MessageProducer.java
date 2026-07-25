package dev.franklindot04.learnjava.messaging;

public interface MessageProducer {
    void send(String destination, MessageEnvelope message);
}
