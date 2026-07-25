package dev.franklindot04.learnjava.messaging;

@FunctionalInterface
public interface MessageConsumer {
    void handle(MessageEnvelope message);
}
