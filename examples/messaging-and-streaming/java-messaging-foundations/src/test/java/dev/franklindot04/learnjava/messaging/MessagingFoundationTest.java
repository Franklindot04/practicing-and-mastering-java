package dev.franklindot04.learnjava.messaging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class MessagingFoundationTest {
    private final MessagingFoundation.MessageHeaders headers = new MessagingFoundation.MessageHeaders(
            "corr-1",
            "cause-1",
            "application/json",
            "orders.created",
            "order-123",
            0,
            Map.of("tenant", "learning"));

    @Test
    void validatesBrokerNeutralEnvelopeAndTracksIdempotency() {
        var envelope = new MessagingFoundation.MessageEnvelope<>("msg-1", "OrderCreated", 1, headers, "payload");
        var store = new MessagingFoundation.IdempotencyStore();

        assertEquals("orders.created", envelope.headers().routingKey());
        assertTrue(store.markFirstProcessing(envelope.messageId()));
        assertFalse(store.markFirstProcessing(envelope.messageId()));
        assertThrows(IllegalArgumentException.class,
                () -> new MessagingFoundation.MessageEnvelope<>("", "OrderCreated", 1, headers, "payload"));
    }

    @Test
    void buildsKafkaAdapterPlansWithoutConnectingToKafka() {
        var envelope = new MessagingFoundation.MessageEnvelope<>("msg-2", "OrderCreated", 1, headers, "payload");
        var producer = MessagingFoundation.kafkaProducerPlan(envelope, "orders.events");
        var consumer = MessagingFoundation.kafkaConsumerPlan("orders.events", "order-projector");

        assertEquals("order-123", producer.key());
        assertEquals("all", producer.properties().get("acks"));
        assertTrue(consumer.manualCommit());
        assertEquals("order-projector", consumer.groupId());
    }

    @Test
    void mapsRabbitMqResultsToAcknowledgementOrDeadLetterDecisions() {
        var retry = new MessagingFoundation.RetryMetadata(1, 3, "TimeoutException");
        var exhausted = new MessagingFoundation.RetryMetadata(3, 3, "MalformedMessage");

        assertTrue(MessagingFoundation.rabbitDecision(MessagingFoundation.ProcessingResult.ACKNOWLEDGED, retry).ack());
        assertTrue(MessagingFoundation.rabbitDecision(MessagingFoundation.ProcessingResult.RETRYABLE_FAILURE, retry).requeue());
        assertEquals("orders.dlx", MessagingFoundation.rabbitDecision(
                MessagingFoundation.ProcessingResult.PERMANENT_FAILURE, exhausted).deadLetterExchange().orElseThrow());
    }

    @Test
    void representsJmsQueueAndTopicSemanticsWithoutClaimingProviderEquivalence() {
        var queue = MessagingFoundation.jmsQueuePlan("queue.orders");
        var topic = MessagingFoundation.jmsTopicPlan("topic.orders");

        assertFalse(queue.topic());
        assertTrue(queue.localTransaction());
        assertTrue(topic.topic());
        assertEquals("tenant = 'learning'", topic.selector());
    }

    @Test
    void readsCompatibleSchemaAndRejectsMalformedOrUnsupportedMessages() {
        var oldPayload = Map.<String, Object>of("orderId", "order-1", "cents", 1200);
        var newPayload = Map.<String, Object>of("orderId", "order-2", "cents", 900, "currency", "EUR");

        assertEquals("USD", MessagingFoundation.readOrderCreated(oldPayload, 1).currency());
        assertEquals("EUR", MessagingFoundation.readOrderCreated(newPayload, 2).currency());
        assertThrows(IllegalArgumentException.class,
                () -> MessagingFoundation.readOrderCreated(Map.of("orderId", "bad"), 1));
        assertThrows(IllegalArgumentException.class,
                () -> MessagingFoundation.readOrderCreated(newPayload, 99));
    }

    @Test
    void simulatesFilteringGroupingWindowsAndLateEventsDeterministically() {
        Instant base = Instant.parse("2026-08-03T10:00:00Z");
        List<MessagingFoundation.StreamEvent> events = List.of(
                new MessagingFoundation.StreamEvent("1", "PAID", 100, base.plusSeconds(5)),
                new MessagingFoundation.StreamEvent("2", "PAID", 150, base.plusSeconds(30)),
                new MessagingFoundation.StreamEvent("3", "CANCELLED", 20, base.plusSeconds(45)),
                new MessagingFoundation.StreamEvent("4", "PAID", 200, base.minusSeconds(120)));

        var results = MessagingFoundation.aggregateByStatusWindow(
                events,
                Duration.ofMinutes(1),
                base.plusSeconds(30),
                event -> event.amount() >= 100);

        assertEquals(2, results.size());
        assertEquals(1, results.get(0).lateEvents());
        assertEquals(250, results.get(1).totalAmount());
    }
}
