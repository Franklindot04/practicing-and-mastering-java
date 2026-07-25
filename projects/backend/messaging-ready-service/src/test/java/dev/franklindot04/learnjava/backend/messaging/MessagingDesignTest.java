package dev.franklindot04.learnjava.backend.messaging;

import dev.franklindot04.learnjava.backend.task.TaskCreatedEvent;
import dev.franklindot04.learnjava.backend.task.TaskEventMapper;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessagingDesignTest {
    @Test
    void mapsDomainEventToMessageEnvelope() {
        TaskEventMapper mapper = new TaskEventMapper();

        MessageEnvelope envelope = mapper.toMessage(
                new TaskCreatedEvent("123", "Learn messaging", Instant.parse("2026-07-25T00:00:00Z")),
                new CorrelationId("request-1"));

        assertEquals(new MessageId("task-created-123"), envelope.messageId());
        assertEquals("TaskCreated", envelope.messageType());
        assertTrue(envelope.payload().contains("Learn messaging"));
    }

    @Test
    void idempotencyStoreTracksProcessedMessages() {
        InMemoryIdempotencyStore store = new InMemoryIdempotencyStore();
        MessageId messageId = new MessageId("msg-1");

        assertFalse(store.alreadyProcessed(messageId));
        store.markProcessed(messageId);

        assertTrue(store.alreadyProcessed(messageId));
    }

    @Test
    void retryPolicyStopsAtMaximumAttempt() {
        RetryPolicy retryPolicy = new RetryPolicy(3);

        assertTrue(retryPolicy.canRetry(1));
        assertTrue(retryPolicy.canRetry(2));
        assertFalse(retryPolicy.canRetry(3));
    }

    @Test
    void validatesIdentifiers() {
        assertThrows(IllegalArgumentException.class, () -> new MessageId(" "));
        assertThrows(IllegalArgumentException.class, () -> new CorrelationId(""));
    }
}
