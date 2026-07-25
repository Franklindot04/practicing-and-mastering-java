package dev.franklindot04.learnjava.backend.messaging;

import java.util.HashSet;
import java.util.Set;

public class InMemoryIdempotencyStore implements IdempotencyStore {
    private final Set<MessageId> processed = new HashSet<>();

    @Override
    public boolean alreadyProcessed(MessageId messageId) {
        return processed.contains(messageId);
    }

    @Override
    public void markProcessed(MessageId messageId) {
        processed.add(messageId);
    }
}
