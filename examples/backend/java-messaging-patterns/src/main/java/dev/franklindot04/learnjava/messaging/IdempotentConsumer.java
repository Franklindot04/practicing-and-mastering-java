package dev.franklindot04.learnjava.messaging;

import java.util.HashSet;
import java.util.Set;

public class IdempotentConsumer implements MessageConsumer {
    private final MessageConsumer delegate;
    private final Set<String> processedMessageIds = new HashSet<>();

    public IdempotentConsumer(MessageConsumer delegate) {
        this.delegate = delegate;
    }

    @Override
    public void handle(MessageEnvelope message) {
        if (processedMessageIds.add(message.messageId())) {
            delegate.handle(message);
        }
    }

    public boolean hasProcessed(String messageId) {
        return processedMessageIds.contains(messageId);
    }
}
