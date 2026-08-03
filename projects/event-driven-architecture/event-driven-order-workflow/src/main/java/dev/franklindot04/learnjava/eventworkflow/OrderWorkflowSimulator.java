package dev.franklindot04.learnjava.eventworkflow;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

public final class OrderWorkflowSimulator {
    private OrderWorkflowSimulator() {
    }

    public sealed interface DomainEvent permits OrderSubmitted, InventoryReserved, InventoryReservationFailed,
            PaymentAuthorizationRequested, PaymentAuthorized, PaymentRejected, OrderConfirmed, OrderCancelled,
            InventoryReleased, NotificationRequested, NotificationFailed {
        String orderId();

        int version();
    }

    public record OrderSubmitted(String orderId, int version, long totalCents) implements DomainEvent {
    }

    public record InventoryReserved(String orderId, int version) implements DomainEvent {
    }

    public record InventoryReservationFailed(String orderId, int version, String reason) implements DomainEvent {
    }

    public record PaymentAuthorizationRequested(String orderId, int version, long amountCents) implements DomainEvent {
    }

    public record PaymentAuthorized(String orderId, int version) implements DomainEvent {
    }

    public record PaymentRejected(String orderId, int version, String reason) implements DomainEvent {
    }

    public record OrderConfirmed(String orderId, int version) implements DomainEvent {
    }

    public record OrderCancelled(String orderId, int version, String reason) implements DomainEvent {
    }

    public record InventoryReleased(String orderId, int version) implements DomainEvent {
    }

    public record NotificationRequested(String orderId, int version) implements DomainEvent {
    }

    public record NotificationFailed(String orderId, int version, String reason) implements DomainEvent {
    }

    public record EventId(String value) {
        public EventId {
            requireText(value, "eventId");
        }

        public static EventId random() {
            return new EventId(UUID.randomUUID().toString());
        }
    }

    public record CorrelationId(String value) {
        public CorrelationId {
            requireText(value, "correlationId");
        }
    }

    public record CausationId(String value) {
        public CausationId {
            requireText(value, "causationId");
        }
    }

    public record EventEnvelope<T extends DomainEvent>(
            EventId eventId,
            String eventType,
            int eventVersion,
            String source,
            Instant timestamp,
            CorrelationId correlationId,
            CausationId causationId,
            String partitionKey,
            T payload,
            Map<String, String> metadata
    ) {
        public EventEnvelope {
            Objects.requireNonNull(eventId);
            requireText(eventType, "eventType");
            if (eventVersion < 1) {
                throw new IllegalArgumentException("eventVersion must be positive");
            }
            requireText(source, "source");
            Objects.requireNonNull(timestamp);
            Objects.requireNonNull(correlationId);
            Objects.requireNonNull(causationId);
            requireText(partitionKey, "partitionKey");
            Objects.requireNonNull(payload);
            metadata = Map.copyOf(metadata);
        }
    }

    @FunctionalInterface
    public interface EventHandler<T extends DomainEvent> {
        void handle(EventEnvelope<T> envelope);
    }

    public record HandlerFailure(String handlerName, RuntimeException failure) {
    }

    public record DispatchReport(int deliveredHandlers, List<HandlerFailure> failures) {
    }

    public static final class EventBus {
        private final Map<Class<? extends DomainEvent>, List<NamedHandler<? extends DomainEvent>>> handlers =
                new HashMap<>();

        public <T extends DomainEvent> void subscribe(Class<T> eventClass, String name, EventHandler<T> handler) {
            handlers.computeIfAbsent(eventClass, ignored -> new ArrayList<>()).add(new NamedHandler<>(name, handler));
        }

        public <T extends DomainEvent> DispatchReport publish(EventEnvelope<T> envelope) {
            List<HandlerFailure> failures = new ArrayList<>();
            List<NamedHandler<? extends DomainEvent>> registered =
                    handlers.getOrDefault(envelope.payload().getClass(), List.of());
            for (NamedHandler<? extends DomainEvent> handler : registered) {
                @SuppressWarnings("unchecked")
                NamedHandler<T> typed = (NamedHandler<T>) handler;
                try {
                    typed.handler().handle(envelope);
                } catch (RuntimeException failure) {
                    failures.add(new HandlerFailure(typed.name(), failure));
                }
            }
            return new DispatchReport(registered.size(), failures);
        }
    }

    private record NamedHandler<T extends DomainEvent>(String name, EventHandler<T> handler) {
    }

    public static final class EventLog {
        private final List<EventEnvelope<? extends DomainEvent>> events = new ArrayList<>();

        void append(EventEnvelope<? extends DomainEvent> envelope) {
            events.add(envelope);
        }

        public List<EventEnvelope<? extends DomainEvent>> events() {
            return List.copyOf(events);
        }

        public List<String> eventTypes() {
            return events.stream().map(EventEnvelope::eventType).toList();
        }
    }

    public static final class ProcessedEventStore {
        private final Set<String> processed = new HashSet<>();

        boolean markIfNew(String consumer, EventId eventId) {
            return processed.add(consumer + ":" + eventId.value());
        }

        public int size() {
            return processed.size();
        }
    }

    public record RetryPolicy(int maxAttempts) {
        public RetryPolicy {
            if (maxAttempts < 1) {
                throw new IllegalArgumentException("maxAttempts must be positive");
            }
        }
    }

    public record DeadLetter(EventEnvelope<? extends DomainEvent> envelope, String consumer, int attempts, String reason) {
    }

    public static final class DeadLetterStore {
        private final List<DeadLetter> deadLetters = new ArrayList<>();

        void add(DeadLetter deadLetter) {
            deadLetters.add(deadLetter);
        }

        public List<DeadLetter> all() {
            return List.copyOf(deadLetters);
        }

        public Optional<DeadLetter> takeFirst() {
            if (deadLetters.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(deadLetters.remove(0));
        }
    }

    public enum WorkflowStatus {
        NEW,
        SUBMITTED,
        INVENTORY_RESERVED,
        PAYMENT_PENDING,
        CONFIRMED,
        NOTIFICATION_REQUESTED,
        COMPLETED,
        CANCELLED,
        FAILED
    }

    public record SagaState(
            String orderId,
            WorkflowStatus status,
            int version,
            boolean inventoryReserved,
            boolean compensationRequired,
            boolean compensationFailed,
            String failureReason
    ) {
        SagaState withStatus(WorkflowStatus newStatus, int newVersion) {
            return new SagaState(orderId, newStatus, newVersion, inventoryReserved, compensationRequired,
                    compensationFailed, failureReason);
        }

        SagaState inventoryReserved(int newVersion) {
            return new SagaState(orderId, WorkflowStatus.INVENTORY_RESERVED, newVersion, true, compensationRequired,
                    compensationFailed, failureReason);
        }

        SagaState compensationRequired(String reason) {
            return new SagaState(orderId, WorkflowStatus.CANCELLED, version, inventoryReserved, true,
                    compensationFailed, reason);
        }

        SagaState compensationFailed(String reason) {
            return new SagaState(orderId, WorkflowStatus.FAILED, version, inventoryReserved, true, true, reason);
        }
    }

    public static final class FailureInjector {
        private final Set<String> inventoryUnavailable = new HashSet<>();
        private final Set<String> paymentRejected = new HashSet<>();
        private final Map<String, Integer> transientPaymentFailures = new HashMap<>();
        private final Set<String> notificationFailure = new HashSet<>();
        private final Set<String> compensationFailure = new HashSet<>();
        private final Set<String> poisonOrders = new HashSet<>();

        public void inventoryUnavailable(String orderId) {
            inventoryUnavailable.add(orderId);
        }

        public void paymentRejected(String orderId) {
            paymentRejected.add(orderId);
        }

        public void transientPaymentFailures(String orderId, int failures) {
            transientPaymentFailures.put(orderId, failures);
        }

        public void notificationFailure(String orderId) {
            notificationFailure.add(orderId);
        }

        public void compensationFailure(String orderId) {
            compensationFailure.add(orderId);
        }

        public void poisonOrder(String orderId) {
            poisonOrders.add(orderId);
        }
    }

    public record OutboxEntry(EventEnvelope<? extends DomainEvent> envelope, int attempts) {
        OutboxEntry increment() {
            return new OutboxEntry(envelope, attempts + 1);
        }
    }

    public static final class Outbox {
        private final Queue<OutboxEntry> unpublished = new ArrayDeque<>();

        void add(EventEnvelope<? extends DomainEvent> envelope) {
            unpublished.add(new OutboxEntry(envelope, 0));
        }

        Optional<OutboxEntry> peek() {
            return Optional.ofNullable(unpublished.peek());
        }

        void published() {
            unpublished.remove();
        }

        void retainWithAttempt() {
            OutboxEntry entry = unpublished.remove();
            unpublished.add(entry.increment());
        }

        public int unpublishedCount() {
            return unpublished.size();
        }
    }

    public static final class OutboxRelay {
        private final Outbox outbox;
        private final EventBus bus;
        private boolean failNextPublication;

        OutboxRelay(Outbox outbox, EventBus bus) {
            this.outbox = outbox;
            this.bus = bus;
        }

        public void failNextPublication() {
            failNextPublication = true;
        }

        public boolean publishNext() {
            Optional<OutboxEntry> next = outbox.peek();
            if (next.isEmpty()) {
                return false;
            }
            if (failNextPublication) {
                failNextPublication = false;
                outbox.retainWithAttempt();
                return false;
            }
            publishUntyped(next.get().envelope());
            outbox.published();
            return true;
        }

        public void publishAll() {
            while (publishNext()) {
                // Keep draining deterministic in-memory outbox entries.
            }
        }

        private <T extends DomainEvent> void publishUntyped(EventEnvelope<T> envelope) {
            bus.publish(envelope);
        }
    }

    public record WorkflowReport(
            int eventCount,
            int deadLetterCount,
            int duplicateEventCount,
            int retryCount,
            int outboxBacklog,
            List<String> eventTypes
    ) {
    }

    public static final class OrderWorkflow {
        private final EventBus bus = new EventBus();
        private final EventLog eventLog = new EventLog();
        private final ProcessedEventStore processed = new ProcessedEventStore();
        private final DeadLetterStore deadLetters = new DeadLetterStore();
        private final FailureInjector failures;
        private final RetryPolicy retryPolicy = new RetryPolicy(3);
        private final Outbox outbox = new Outbox();
        private final OutboxRelay relay = new OutboxRelay(outbox, bus);
        private final Map<String, SagaState> sagas = new HashMap<>();
        private final List<String> sideEffects = new ArrayList<>();
        private int duplicateEvents;
        private int retries;

        public OrderWorkflow(FailureInjector failures) {
            this.failures = failures;
            subscribe(OrderSubmitted.class, "order-submitted", this::onOrderSubmitted);
            subscribe(InventoryReserved.class, "inventory-reserved", this::onInventoryReserved);
            subscribe(InventoryReservationFailed.class, "inventory-failed", this::onInventoryFailed);
            subscribe(PaymentAuthorizationRequested.class, "payment-requested", this::onPaymentRequested);
            subscribe(PaymentAuthorized.class, "payment-authorized", this::onPaymentAuthorized);
            subscribe(PaymentRejected.class, "payment-rejected", this::onPaymentRejected);
            subscribe(OrderConfirmed.class, "order-confirmed", this::onOrderConfirmed);
            subscribe(NotificationRequested.class, "notification-requested", this::onNotificationRequested);
            subscribe(NotificationFailed.class, "notification-failed", this::onNotificationFailed);
            subscribe(OrderCancelled.class, "order-cancelled", envelope -> sideEffects.add("cancelled:" + envelope.payload().orderId()));
            subscribe(InventoryReleased.class, "inventory-released", envelope -> sideEffects.add("released:" + envelope.payload().orderId()));
        }

        public void submit(String orderId, long totalCents) {
            if (sagas.containsKey(orderId)) {
                duplicateEvents++;
                return;
            }
            sagas.put(orderId, new SagaState(orderId, WorkflowStatus.NEW, 0, false, false, false, null));
            EventEnvelope<OrderSubmitted> submitted = envelope(new OrderSubmitted(orderId, 1, totalCents),
                    new CorrelationId("corr-" + orderId), new CausationId("submit-" + orderId));
            eventLog.append(submitted);
            outbox.add(submitted);
            relay.publishAll();
        }

        public void publishDuplicate(EventEnvelope<? extends DomainEvent> envelope) {
            publishUntyped(envelope);
            publishUntyped(envelope);
        }

        public void replayFirstDeadLetter() {
            deadLetters.takeFirst().ifPresent(deadLetter -> publishUntyped(deadLetter.envelope()));
        }

        public OutboxRelay relay() {
            return relay;
        }

        public Outbox outbox() {
            return outbox;
        }

        public EventLog eventLog() {
            return eventLog;
        }

        public DeadLetterStore deadLetters() {
            return deadLetters;
        }

        public SagaState state(String orderId) {
            return sagas.get(orderId);
        }

        public List<String> sideEffects() {
            return List.copyOf(sideEffects);
        }

        public WorkflowReport report() {
            return new WorkflowReport(eventLog.events().size(), deadLetters.all().size(), duplicateEvents, retries,
                    outbox.unpublishedCount(), eventLog.eventTypes());
        }

        private <T extends DomainEvent> void subscribe(Class<T> type, String consumer, EventHandler<T> handler) {
            bus.subscribe(type, consumer, envelope -> {
                if (!processed.markIfNew(consumer, envelope.eventId())) {
                    duplicateEvents++;
                    return;
                }
                for (int attempt = 1; attempt <= retryPolicy.maxAttempts(); attempt++) {
                    try {
                        handler.handle(envelope);
                        return;
                    } catch (TransientWorkflowException failure) {
                        retries++;
                        if (attempt == retryPolicy.maxAttempts()) {
                            deadLetters.add(new DeadLetter(envelope, consumer, attempt, failure.getMessage()));
                            return;
                        }
                    } catch (PermanentWorkflowException failure) {
                        deadLetters.add(new DeadLetter(envelope, consumer, attempt, failure.getMessage()));
                        return;
                    }
                }
            });
        }

        private void onOrderSubmitted(EventEnvelope<OrderSubmitted> envelope) {
            String orderId = envelope.payload().orderId();
            sagas.putIfAbsent(orderId, new SagaState(orderId, WorkflowStatus.NEW, 0, false, false, false, null));
            if (failures.poisonOrders.contains(orderId)) {
                throw new PermanentWorkflowException("poison order");
            }
            if (failures.inventoryUnavailable.contains(orderId)) {
                publish(new InventoryReservationFailed(orderId, 2, "inventory unavailable"), envelope);
                return;
            }
            sideEffects.add("inventory-reserved:" + orderId);
            sagas.put(orderId, sagas.get(orderId).inventoryReserved(2));
            publish(new InventoryReserved(orderId, 2), envelope);
        }

        private void onInventoryReserved(EventEnvelope<InventoryReserved> envelope) {
            String orderId = envelope.payload().orderId();
            sagas.put(orderId, sagas.get(orderId).withStatus(WorkflowStatus.PAYMENT_PENDING, 3));
            publish(new PaymentAuthorizationRequested(orderId, 3, 1000), envelope);
        }

        private void onInventoryFailed(EventEnvelope<InventoryReservationFailed> envelope) {
            String orderId = envelope.payload().orderId();
            sagas.put(orderId, sagas.get(orderId).withStatus(WorkflowStatus.CANCELLED, envelope.payload().version()));
            publish(new OrderCancelled(orderId, 3, envelope.payload().reason()), envelope);
        }

        private void onPaymentRequested(EventEnvelope<PaymentAuthorizationRequested> envelope) {
            String orderId = envelope.payload().orderId();
            int remainingFailures = failures.transientPaymentFailures.getOrDefault(orderId, 0);
            if (remainingFailures > 0) {
                failures.transientPaymentFailures.put(orderId, remainingFailures - 1);
                throw new TransientWorkflowException("payment timeout");
            }
            if (failures.paymentRejected.contains(orderId)) {
                publish(new PaymentRejected(orderId, 4, "payment rejected"), envelope);
                return;
            }
            sideEffects.add("payment-authorized:" + orderId);
            publish(new PaymentAuthorized(orderId, 4), envelope);
        }

        private void onPaymentAuthorized(EventEnvelope<PaymentAuthorized> envelope) {
            String orderId = envelope.payload().orderId();
            sagas.put(orderId, sagas.get(orderId).withStatus(WorkflowStatus.CONFIRMED, 5));
            publish(new OrderConfirmed(orderId, 5), envelope);
        }

        private void onPaymentRejected(EventEnvelope<PaymentRejected> envelope) {
            String orderId = envelope.payload().orderId();
            SagaState state = sagas.get(orderId).compensationRequired(envelope.payload().reason());
            sagas.put(orderId, state);
            if (failures.compensationFailure.contains(orderId)) {
                sagas.put(orderId, state.compensationFailed("inventory release failed"));
                return;
            }
            publish(new InventoryReleased(orderId, 5), envelope);
            publish(new OrderCancelled(orderId, 6, envelope.payload().reason()), envelope);
        }

        private void onOrderConfirmed(EventEnvelope<OrderConfirmed> envelope) {
            String orderId = envelope.payload().orderId();
            sagas.put(orderId, sagas.get(orderId).withStatus(WorkflowStatus.NOTIFICATION_REQUESTED, 6));
            publish(new NotificationRequested(orderId, 6), envelope);
        }

        private void onNotificationRequested(EventEnvelope<NotificationRequested> envelope) {
            String orderId = envelope.payload().orderId();
            if (failures.notificationFailure.contains(orderId)) {
                publish(new NotificationFailed(orderId, 7, "notification gateway unavailable"), envelope);
                return;
            }
            sideEffects.add("notification-requested:" + orderId);
            sagas.put(orderId, sagas.get(orderId).withStatus(WorkflowStatus.COMPLETED, 7));
        }

        private void onNotificationFailed(EventEnvelope<NotificationFailed> envelope) {
            sideEffects.add("notification-failed:" + envelope.payload().orderId());
        }

        private void publish(DomainEvent event, EventEnvelope<? extends DomainEvent> cause) {
            publishUntyped(envelope(event, cause.correlationId(), new CausationId(cause.eventId().value())));
        }

        private <T extends DomainEvent> void publishUntyped(EventEnvelope<T> envelope) {
            eventLog.append(envelope);
            bus.publish(envelope);
        }
    }

    public enum TransitionResult {
        APPLIED,
        STALE_OR_DUPLICATE,
        OUT_OF_ORDER
    }

    public static final class OrderingGuard {
        private final Map<String, Integer> versions = new HashMap<>();
        private final Queue<DomainEvent> deferred = new ArrayDeque<>();

        public TransitionResult apply(DomainEvent event) {
            int current = versions.getOrDefault(event.orderId(), 0);
            if (event.version() <= current) {
                return TransitionResult.STALE_OR_DUPLICATE;
            }
            if (event.version() > current + 1) {
                deferred.add(event);
                return TransitionResult.OUT_OF_ORDER;
            }
            versions.put(event.orderId(), event.version());
            applyDeferred(event.orderId());
            return TransitionResult.APPLIED;
        }

        public int version(String orderId) {
            return versions.getOrDefault(orderId, 0);
        }

        public int deferredCount() {
            return deferred.size();
        }

        private void applyDeferred(String orderId) {
            List<DomainEvent> sorted = deferred.stream()
                    .filter(event -> event.orderId().equals(orderId))
                    .sorted(Comparator.comparingInt(DomainEvent::version))
                    .toList();
            deferred.removeIf(event -> event.orderId().equals(orderId));
            for (DomainEvent event : sorted) {
                if (event.version() == version(orderId) + 1) {
                    versions.put(orderId, event.version());
                } else {
                    deferred.add(event);
                }
            }
        }
    }

    public static <T extends DomainEvent> EventEnvelope<T> envelope(
            T event,
            CorrelationId correlationId,
            CausationId causationId
    ) {
        return new EventEnvelope<>(
                EventId.random(),
                event.getClass().getSimpleName(),
                1,
                "event-driven-order-workflow",
                Instant.parse("2026-01-01T00:00:00Z"),
                correlationId,
                causationId,
                event.orderId(),
                event,
                Map.of("model", "educational")
        );
    }

    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

    public static final class TransientWorkflowException extends RuntimeException {
        TransientWorkflowException(String message) {
            super(message);
        }
    }

    public static final class PermanentWorkflowException extends RuntimeException {
        PermanentWorkflowException(String message) {
            super(message);
        }
    }
}
