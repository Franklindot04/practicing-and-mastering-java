package dev.franklindot04.learnjava.messaging;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public final class MessagingFoundation {
    private MessagingFoundation() {
    }

    public enum ProcessingResult {
        ACKNOWLEDGED,
        RETRYABLE_FAILURE,
        PERMANENT_FAILURE
    }

    public record MessageHeaders(
            String correlationId,
            String causationId,
            String contentType,
            String routingKey,
            String partitionKey,
            int retryCount,
            Map<String, String> attributes) {
        public MessageHeaders {
            if (correlationId == null || correlationId.isBlank()) {
                throw new IllegalArgumentException("correlationId is required");
            }
            if (contentType == null || contentType.isBlank()) {
                throw new IllegalArgumentException("contentType is required");
            }
            attributes = Map.copyOf(attributes);
        }
    }

    public record MessageEnvelope<T>(
            String messageId,
            String messageType,
            int version,
            MessageHeaders headers,
            T payload) {
        public MessageEnvelope {
            if (messageId == null || messageId.isBlank()) {
                throw new IllegalArgumentException("messageId is required");
            }
            if (messageType == null || messageType.isBlank()) {
                throw new IllegalArgumentException("messageType is required");
            }
            if (version < 1) {
                throw new IllegalArgumentException("version must be positive");
            }
            Objects.requireNonNull(headers, "headers");
            Objects.requireNonNull(payload, "payload");
        }
    }

    public record Acknowledgement(String messageId, Instant acknowledgedAt) {
    }

    public record NegativeAcknowledgement(String messageId, String reason, boolean requeue) {
    }

    public record RetryMetadata(int attempt, int maxAttempts, String failureClass) {
        public boolean exhausted() {
            return attempt >= maxAttempts;
        }
    }

    public record DeadLetterRecord<T>(
            MessageEnvelope<T> envelope,
            RetryMetadata retryMetadata,
            String destination,
            String reason) {
    }

    public interface Producer<T> {
        SendResult send(MessageEnvelope<T> envelope);
    }

    public interface Consumer<T> {
        ProcessingResult consume(MessageEnvelope<T> envelope);
    }

    public record SendResult(boolean accepted, boolean uncertain, String brokerReference) {
    }

    public record KafkaProducerPlan(
            String topic,
            String key,
            String keySerializer,
            String valueSerializer,
            Map<String, String> properties) {
    }

    public record KafkaConsumerPlan(
            String topic,
            String groupId,
            String keyDeserializer,
            String valueDeserializer,
            boolean manualCommit,
            Duration pollTimeout) {
    }

    public static KafkaProducerPlan kafkaProducerPlan(MessageEnvelope<?> envelope, String topic) {
        return new KafkaProducerPlan(
                topic,
                envelope.headers().partitionKey(),
                "org.apache.kafka.common.serialization.StringSerializer",
                "example.JsonEnvelopeSerializer",
                Map.of("acks", "all", "enable.idempotence", "true", "retries", "3"));
    }

    public static KafkaConsumerPlan kafkaConsumerPlan(String topic, String groupId) {
        return new KafkaConsumerPlan(
                topic,
                groupId,
                "org.apache.kafka.common.serialization.StringDeserializer",
                "example.JsonEnvelopeDeserializer",
                true,
                Duration.ofMillis(500));
    }

    public record RabbitRoute(String exchange, String queue, String binding, String routingKey, int prefetch) {
    }

    public record RabbitDeliveryDecision(boolean ack, boolean nack, boolean requeue, Optional<String> deadLetterExchange) {
    }

    public static RabbitDeliveryDecision rabbitDecision(ProcessingResult result, RetryMetadata retry) {
        if (result == ProcessingResult.ACKNOWLEDGED) {
            return new RabbitDeliveryDecision(true, false, false, Optional.empty());
        }
        if (result == ProcessingResult.RETRYABLE_FAILURE && !retry.exhausted()) {
            return new RabbitDeliveryDecision(false, true, true, Optional.empty());
        }
        return new RabbitDeliveryDecision(false, true, false, Optional.of("orders.dlx"));
    }

    public record JmsSendPlan(String destinationName, boolean topic, String selector, int acknowledgementMode,
                              boolean localTransaction) {
    }

    public static JmsSendPlan jmsQueuePlan(String queueName) {
        return new JmsSendPlan(queueName, false, "eventType = 'OrderSubmitted'", 2, true);
    }

    public static JmsSendPlan jmsTopicPlan(String topicName) {
        return new JmsSendPlan(topicName, true, "tenant = 'learning'", 2, false);
    }

    public record OrderCreatedV1(String orderId, int cents) {
    }

    public record OrderCreatedV2(String orderId, int cents, String currency) {
    }

    public static OrderCreatedV2 readOrderCreated(Map<String, Object> payload, int version) {
        if (version > 2) {
            throw new IllegalArgumentException("unsupported version " + version);
        }
        Object orderId = payload.get("orderId");
        Object cents = payload.get("cents");
        if (!(orderId instanceof String) || !(cents instanceof Integer)) {
            throw new IllegalArgumentException("malformed OrderCreated payload");
        }
        Object currency = payload.getOrDefault("currency", "USD");
        if (!(currency instanceof String)) {
            throw new IllegalArgumentException("currency must be text");
        }
        return new OrderCreatedV2((String) orderId, (Integer) cents, (String) currency);
    }

    public record StreamEvent(String id, String status, int amount, Instant eventTime) {
    }

    public record WindowResult(Instant windowStart, String status, int count, int totalAmount, int lateEvents) {
    }

    public static List<WindowResult> aggregateByStatusWindow(
            List<StreamEvent> events,
            Duration windowSize,
            Instant watermark,
            Predicate<StreamEvent> filter) {
        Map<String, MutableWindow> windows = new LinkedHashMap<>();
        events.stream()
                .filter(filter)
                .sorted(Comparator.comparing(StreamEvent::eventTime))
                .forEach(event -> {
                    long epoch = event.eventTime().getEpochSecond();
                    long windowSeconds = windowSize.toSeconds();
                    Instant start = Instant.ofEpochSecond((epoch / windowSeconds) * windowSeconds);
                    String key = start + "|" + event.status();
                    MutableWindow window = windows.computeIfAbsent(key, ignored -> new MutableWindow(start, event.status()));
                    boolean late = event.eventTime().isBefore(watermark.minus(windowSize));
                    window.add(event.amount(), late);
                });
        return windows.values().stream().map(MutableWindow::toResult).toList();
    }

    public static final class IdempotencyStore {
        private final Set<String> processed = new LinkedHashSet<>();

        public boolean markFirstProcessing(String messageId) {
            return processed.add(messageId);
        }
    }

    private static final class MutableWindow {
        private final Instant start;
        private final String status;
        private int count;
        private int amount;
        private int late;

        private MutableWindow(Instant start, String status) {
            this.start = start;
            this.status = status;
        }

        private void add(int value, boolean lateEvent) {
            count++;
            amount += value;
            if (lateEvent) {
                late++;
            }
        }

        private WindowResult toResult() {
            return new WindowResult(start, status, count, amount, late);
        }
    }
}
