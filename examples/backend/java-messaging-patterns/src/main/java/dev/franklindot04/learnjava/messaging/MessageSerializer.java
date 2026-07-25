package dev.franklindot04.learnjava.messaging;

public interface MessageSerializer {
    String serialize(MessageEnvelope message);

    MessageEnvelope deserialize(String serialized);
}
