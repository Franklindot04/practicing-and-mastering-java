package dev.franklindot04.learnjava.messaging;

import java.time.Instant;

public class SimpleMessageSerializer implements MessageSerializer {
    private static final String DELIMITER = "\\|";

    @Override
    public String serialize(MessageEnvelope message) {
        return String.join("|",
                escape(message.messageId()),
                escape(message.correlationId()),
                message.timestamp().toString(),
                escape(message.messageType()),
                escape(message.payload()));
    }

    @Override
    public MessageEnvelope deserialize(String serialized) {
        String[] parts = serialized.split(DELIMITER, -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Serialized message must have five parts");
        }
        return new MessageEnvelope(
                unescape(parts[0]),
                unescape(parts[1]),
                Instant.parse(parts[2]),
                unescape(parts[3]),
                unescape(parts[4]));
    }

    private String escape(String value) {
        return value.replace("%", "%25").replace("|", "%7C");
    }

    private String unescape(String value) {
        return value.replace("%7C", "|").replace("%25", "%");
    }
}
