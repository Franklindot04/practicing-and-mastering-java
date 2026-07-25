package dev.franklindot04.learnjava.backend.messaging;

public interface SerializationBoundary {
    String serialize(MessageEnvelope envelope);

    MessageEnvelope deserialize(String serialized);
}
