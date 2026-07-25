package dev.franklindot04.learnjava.backend.messaging;

public interface MessageConsumerPort {
    ConsumerResult consume(MessageEnvelope envelope);
}
