package dev.franklindot04.learnjava.messaging;

import java.util.ArrayList;
import java.util.List;

public class DeadLetterCollector {
    private final List<DeadLetterMessage> messages = new ArrayList<>();

    public void collect(MessageEnvelope message, String reason, int attempts) {
        messages.add(new DeadLetterMessage(message, reason, attempts));
    }

    public List<DeadLetterMessage> messages() {
        return List.copyOf(messages);
    }
}
