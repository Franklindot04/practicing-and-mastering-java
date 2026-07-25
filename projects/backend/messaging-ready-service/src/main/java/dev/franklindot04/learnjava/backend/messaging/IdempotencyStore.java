package dev.franklindot04.learnjava.backend.messaging;

public interface IdempotencyStore {
    boolean alreadyProcessed(MessageId messageId);

    void markProcessed(MessageId messageId);
}
