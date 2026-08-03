package dev.franklindot04.learnjava.orderpipeline;

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

public final class OrderEventPipeline {
    private final List<MessageEnvelope<OrderMessage>> eventLog = new ArrayList<>();
    private final Set<String> processedMessages = new LinkedHashSet<>();
    private final Map<String, OrderStatus> projection = new LinkedHashMap<>();
    private final Map<String, Long> committedOffsets = new LinkedHashMap<>();
    private final List<DeadLetterRecord> deadLetters = new ArrayList<>();
    private final List<String> notifications = new ArrayList<>();
    private final PipelineMetrics metrics = new PipelineMetrics();
    private boolean stopped;

    public MessageEnvelope<OrderMessage> submit(String orderId, int cents) {
        return append("msg-" + (eventLog.size() + 1), orderId, 1, new OrderSubmitted(orderId, cents));
    }

    public ProcessingOutcome process(MessageEnvelope<OrderMessage> envelope, FailureMode failureMode) {
        if (stopped) {
            return ProcessingOutcome.SHUTDOWN;
        }
        envelope.validate();
        if (!processedMessages.add(envelope.messageId())) {
            metrics.duplicates++;
            return ProcessingOutcome.DUPLICATE;
        }
        if (failureMode == FailureMode.CRASH_BEFORE_ACK) {
            processedMessages.remove(envelope.messageId());
            metrics.redeliveries++;
            return ProcessingOutcome.REDELIVER;
        }
        if (failureMode == FailureMode.PERMANENT || envelope.version() > 2) {
            metrics.retryExhausted++;
            deadLetters.add(new DeadLetterRecord(envelope, "orders.dlq", "permanent failure or incompatible schema"));
            return ProcessingOutcome.DEAD_LETTERED;
        }
        apply(envelope);
        committedOffsets.put(envelope.partitionKey(), envelope.offset());
        metrics.processed++;
        return ProcessingOutcome.ACKNOWLEDGED;
    }

    public List<MessageEnvelope<OrderMessage>> runHealthyWorkflow(String orderId) {
        List<MessageEnvelope<OrderMessage>> events = List.of(
                append("msg-" + (eventLog.size() + 1), orderId, 1, new OrderSubmitted(orderId, 1200)),
                append("msg-" + (eventLog.size() + 1), orderId, 1, new OrderValidated(orderId)),
                append("msg-" + (eventLog.size() + 1), orderId, 1, new InventoryReserved(orderId)),
                append("msg-" + (eventLog.size() + 1), orderId, 1, new PaymentProcessed(orderId)),
                append("msg-" + (eventLog.size() + 1), orderId, 1, new ShipmentPrepared(orderId)),
                append("msg-" + (eventLog.size() + 1), orderId, 1, new NotificationRequested(orderId)));
        events.forEach(event -> process(event, FailureMode.NONE));
        return events;
    }

    public List<MessageEnvelope<OrderMessage>> replayProjectionOnly() {
        projection.clear();
        eventLog.stream()
                .sorted(Comparator.comparing(MessageEnvelope::offset))
                .forEach(this::applyProjectionOnly);
        return List.copyOf(eventLog);
    }

    public PartitionAssignment rebalance(List<String> members, int partitions) {
        Map<Integer, String> assignment = new LinkedHashMap<>();
        for (int partition = 0; partition < partitions; partition++) {
            assignment.put(partition, members.get(partition % members.size()));
        }
        return new PartitionAssignment(assignment);
    }

    public RoutingResult routeRabbit(String exchangeType, String routingKey) {
        return switch (exchangeType) {
            case "direct" -> new RoutingResult(List.of(routingKey.equals("orders.paid") ? "payments.queue" : "orders.dlq"), true);
            case "topic" -> new RoutingResult(List.of("orders.audit.queue", "orders.analytics.queue"), true);
            case "fanout" -> new RoutingResult(List.of("notifications.queue", "analytics.queue"), true);
            default -> new RoutingResult(List.of("orders.dlq"), false);
        };
    }

    public JmsComparison jmsComparison() {
        return new JmsComparison("queue.orders validates competing consumers", "topic.orders broadcasts to subscribers");
    }

    public List<WindowCount> aggregateByStatus(Instant watermark) {
        Map<String, WindowCount> counts = new LinkedHashMap<>();
        for (MessageEnvelope<OrderMessage> envelope : eventLog) {
            String key = projection.getOrDefault(envelope.orderId(), OrderStatus.SUBMITTED).name();
            WindowCount current = counts.getOrDefault(key, new WindowCount(key, 0, 0));
            int late = envelope.eventTime().isBefore(watermark.minusSeconds(60)) ? 1 : 0;
            counts.put(key, new WindowCount(key, current.count() + 1, current.lateEvents() + late));
        }
        return List.copyOf(counts.values());
    }

    public SerializationResult deserialize(Map<String, Object> payload, int version) {
        if (version > 2) {
            return new SerializationResult(false, "unsupported schema version");
        }
        if (!(payload.get("orderId") instanceof String)) {
            return new SerializationResult(false, "malformed order message");
        }
        return new SerializationResult(true, "compatible");
    }

    public PublisherConfirm publish(MessageEnvelope<OrderMessage> envelope, boolean brokerAccepted, boolean timedOut) {
        return new PublisherConfirm(envelope.messageId(), brokerAccepted, timedOut);
    }

    public void shutdown() {
        stopped = true;
    }

    public Map<String, OrderStatus> projection() {
        return Map.copyOf(projection);
    }

    public List<DeadLetterRecord> deadLetters() {
        return List.copyOf(deadLetters);
    }

    public PipelineMetrics metrics() {
        return metrics.snapshot();
    }

    public List<String> notifications() {
        return List.copyOf(notifications);
    }

    public long lag(String partitionKey, long latestOffset) {
        return latestOffset - committedOffsets.getOrDefault(partitionKey, -1L);
    }

    private MessageEnvelope<OrderMessage> append(String messageId, String orderId, int version, OrderMessage message) {
        long offset = eventLog.size();
        MessageEnvelope<OrderMessage> envelope = new MessageEnvelope<>(
                messageId,
                message.getClass().getSimpleName(),
                version,
                orderId,
                Math.floorMod(orderId.hashCode(), 3),
                offset,
                Instant.parse("2026-08-03T10:00:00Z").plusSeconds(offset * 10),
                message);
        eventLog.add(envelope);
        return envelope;
    }

    private void apply(MessageEnvelope<OrderMessage> envelope) {
        applyProjectionOnly(envelope);
        if (envelope.payload() instanceof NotificationRequested requested) {
            notifications.add(requested.orderId());
        }
    }

    private void applyProjectionOnly(MessageEnvelope<OrderMessage> envelope) {
        OrderMessage payload = envelope.payload();
        if (payload instanceof OrderSubmitted) {
            projection.put(envelope.orderId(), OrderStatus.SUBMITTED);
        } else if (payload instanceof OrderValidated) {
            projection.put(envelope.orderId(), OrderStatus.VALIDATED);
        } else if (payload instanceof InventoryReserved) {
            projection.put(envelope.orderId(), OrderStatus.INVENTORY_RESERVED);
        } else if (payload instanceof PaymentProcessed) {
            projection.put(envelope.orderId(), OrderStatus.PAID);
        } else if (payload instanceof ShipmentPrepared) {
            projection.put(envelope.orderId(), OrderStatus.SHIPMENT_PREPARED);
        } else if (payload instanceof NotificationRequested) {
            projection.put(envelope.orderId(), OrderStatus.NOTIFICATION_REQUESTED);
        }
    }

    public sealed interface OrderMessage permits OrderSubmitted, OrderValidated, InventoryReserved, PaymentProcessed,
            ShipmentPrepared, NotificationRequested {
        String orderId();
    }

    public record OrderSubmitted(String orderId, int cents) implements OrderMessage {
    }

    public record OrderValidated(String orderId) implements OrderMessage {
    }

    public record InventoryReserved(String orderId) implements OrderMessage {
    }

    public record PaymentProcessed(String orderId) implements OrderMessage {
    }

    public record ShipmentPrepared(String orderId) implements OrderMessage {
    }

    public record NotificationRequested(String orderId) implements OrderMessage {
    }

    public record MessageEnvelope<T extends OrderMessage>(
            String messageId,
            String messageType,
            int version,
            String orderId,
            int partition,
            long offset,
            Instant eventTime,
            T payload) {
        public MessageEnvelope {
            Objects.requireNonNull(payload, "payload");
        }

        public String partitionKey() {
            return orderId;
        }

        public String routingKey() {
            return "orders." + messageType.toLowerCase();
        }

        public void validate() {
            if (messageId == null || messageId.isBlank() || orderId == null || orderId.isBlank()) {
                throw new IllegalArgumentException("messageId and orderId are required");
            }
        }
    }

    public enum OrderStatus {
        SUBMITTED,
        VALIDATED,
        INVENTORY_RESERVED,
        PAID,
        SHIPMENT_PREPARED,
        NOTIFICATION_REQUESTED
    }

    public enum FailureMode {
        NONE,
        CRASH_BEFORE_ACK,
        PERMANENT
    }

    public enum ProcessingOutcome {
        ACKNOWLEDGED,
        DUPLICATE,
        REDELIVER,
        DEAD_LETTERED,
        SHUTDOWN
    }

    public record DeadLetterRecord(MessageEnvelope<OrderMessage> envelope, String destination, String reason) {
    }

    public record PartitionAssignment(Map<Integer, String> assignments) {
    }

    public record RoutingResult(List<String> queues, boolean publisherConfirmed) {
    }

    public record JmsComparison(String queueBehavior, String topicBehavior) {
    }

    public record WindowCount(String status, int count, int lateEvents) {
    }

    public record SerializationResult(boolean compatible, String reason) {
    }

    public record PublisherConfirm(String messageId, boolean accepted, boolean uncertain) {
    }

    public static final class PipelineMetrics {
        private int processed;
        private int duplicates;
        private int redeliveries;
        private int retryExhausted;

        private PipelineMetrics snapshot() {
            PipelineMetrics copy = new PipelineMetrics();
            copy.processed = processed;
            copy.duplicates = duplicates;
            copy.redeliveries = redeliveries;
            copy.retryExhausted = retryExhausted;
            return copy;
        }

        public int processed() {
            return processed;
        }

        public int duplicates() {
            return duplicates;
        }

        public int redeliveries() {
            return redeliveries;
        }

        public int retryExhausted() {
            return retryExhausted;
        }
    }
}
