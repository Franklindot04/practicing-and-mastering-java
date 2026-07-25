package dev.franklindot04.learnjava.messaging;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryMessageBrokerTest {
    @Test
    void drainsQueueStyleMessagesToOneConsumer() {
        InMemoryMessageBroker broker = new InMemoryMessageBroker();
        DeadLetterCollector deadLetters = new DeadLetterCollector();
        List<String> handled = new ArrayList<>();

        broker.send("tasks", message("m1", "TaskCreated"));
        broker.send("tasks", message("m2", "TaskClosed"));

        int count = broker.drainQueue("tasks", envelope -> handled.add(envelope.messageType()), new RetryPolicy(1), deadLetters);

        assertEquals(2, count);
        assertEquals(List.of("TaskCreated", "TaskClosed"), handled);
        assertTrue(deadLetters.messages().isEmpty());
    }

    @Test
    void publishesToAllTopicSubscribers() {
        InMemoryMessageBroker broker = new InMemoryMessageBroker();
        List<String> audit = new ArrayList<>();
        List<String> notifications = new ArrayList<>();

        broker.subscribe("task-events", envelope -> audit.add(envelope.messageId()));
        broker.subscribe("task-events", envelope -> notifications.add(envelope.messageId()));

        broker.publish("task-events", message("m1", "TaskCreated"));

        assertEquals(List.of("m1"), audit);
        assertEquals(List.of("m1"), notifications);
    }

    @Test
    void idempotentConsumerSkipsDuplicateMessageIds() {
        InMemoryMessageBroker broker = new InMemoryMessageBroker();
        DeadLetterCollector deadLetters = new DeadLetterCollector();
        AtomicInteger sideEffects = new AtomicInteger();
        IdempotentConsumer consumer = new IdempotentConsumer(envelope -> sideEffects.incrementAndGet());

        broker.simulateDuplicateDelivery("tasks", message("same-id", "TaskCreated"));
        int count = broker.drainQueue("tasks", consumer, new RetryPolicy(1), deadLetters);

        assertEquals(2, count);
        assertEquals(1, sideEffects.get());
        assertTrue(consumer.hasProcessed("same-id"));
    }

    @Test
    void retriesThenDeadLettersFailedMessages() {
        InMemoryMessageBroker broker = new InMemoryMessageBroker();
        DeadLetterCollector deadLetters = new DeadLetterCollector();
        AtomicInteger attempts = new AtomicInteger();

        broker.send("tasks", message("m1", "TaskCreated"));
        int count = broker.drainQueue("tasks", envelope -> {
            attempts.incrementAndGet();
            throw new IllegalStateException("consumer unavailable");
        }, new RetryPolicy(3), deadLetters);

        assertEquals(0, count);
        assertEquals(3, attempts.get());
        assertEquals(1, deadLetters.messages().size());
        assertEquals("consumer unavailable", deadLetters.messages().get(0).reason());
    }

    @Test
    void serializesAndDeserializesEnvelopeAtBoundary() {
        SimpleMessageSerializer serializer = new SimpleMessageSerializer();
        MessageEnvelope original = new MessageEnvelope(
                "m|1",
                "corr%1",
                Instant.parse("2026-07-25T10:15:30Z"),
                "TaskCreated",
                "task|payload");

        MessageEnvelope copy = serializer.deserialize(serializer.serialize(original));

        assertEquals(original, copy);
    }

    private MessageEnvelope message(String id, String type) {
        return new MessageEnvelope(id, "corr-" + id, Instant.parse("2026-07-25T00:00:00Z"), type, "{}");
    }
}
