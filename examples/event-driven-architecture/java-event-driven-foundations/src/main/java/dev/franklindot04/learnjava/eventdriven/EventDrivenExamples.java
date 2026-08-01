package dev.franklindot04.learnjava.eventdriven;

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
import java.util.function.Predicate;

public final class EventDrivenExamples {
    private EventDrivenExamples() {
    }

    public sealed interface DomainEvent permits OrderSubmitted, OrderStatusChanged {
        String aggregateId();

        int aggregateVersion();
    }

    public record OrderSubmitted(String aggregateId, int aggregateVersion, long totalCents)
            implements DomainEvent {
    }

    public record OrderStatusChanged(String aggregateId, int aggregateVersion, String status)
            implements DomainEvent {
    }

    public record EventId(String value) {
        public EventId {
            requireText(value, "eventId");
        }

        public static EventId random() {
            return new EventId(UUID.randomUUID().toString());
        }
    }

    public record EventType(String value) {
        public EventType {
            requireText(value, "eventType");
        }
    }

    public record EventVersion(int value) {
        public EventVersion {
            if (value < 1) {
                throw new IllegalArgumentException("eventVersion must be positive");
            }
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
            EventType type,
            EventVersion version,
            String source,
            Instant timestamp,
            CorrelationId correlationId,
            CausationId causationId,
            T payload,
            Map<String, String> metadata
    ) {
        public EventEnvelope {
            Objects.requireNonNull(eventId);
            Objects.requireNonNull(type);
            Objects.requireNonNull(version);
            requireText(source, "source");
            Objects.requireNonNull(timestamp);
            Objects.requireNonNull(correlationId);
            Objects.requireNonNull(causationId);
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
        public boolean succeeded() {
            return failures.isEmpty();
        }
    }

    public static final class InMemoryEventBus {
        private final Map<Class<? extends DomainEvent>, List<NamedHandler<? extends DomainEvent>>> handlers =
                new HashMap<>();
        private final List<EventEnvelope<? extends DomainEvent>> published = new ArrayList<>();

        public <T extends DomainEvent> void subscribe(
                Class<T> eventClass,
                String handlerName,
                EventHandler<T> handler
        ) {
            handlers.computeIfAbsent(eventClass, ignored -> new ArrayList<>())
                    .add(new NamedHandler<>(handlerName, handler));
        }

        public <T extends DomainEvent> DispatchReport publish(EventEnvelope<T> envelope) {
            published.add(envelope);
            List<HandlerFailure> failures = new ArrayList<>();
            List<NamedHandler<? extends DomainEvent>> registered =
                    handlers.getOrDefault(envelope.payload().getClass(), List.of());
            for (NamedHandler<? extends DomainEvent> registeredHandler : registered) {
                @SuppressWarnings("unchecked")
                NamedHandler<T> handler = (NamedHandler<T>) registeredHandler;
                try {
                    handler.handler().handle(envelope);
                } catch (RuntimeException failure) {
                    failures.add(new HandlerFailure(handler.name(), failure));
                }
            }
            return new DispatchReport(registered.size(), failures);
        }

        public List<EventEnvelope<? extends DomainEvent>> published() {
            return List.copyOf(published);
        }
    }

    private record NamedHandler<T extends DomainEvent>(String name, EventHandler<T> handler) {
    }

    public static final class ProcessedEventStore {
        private final Set<EventId> processed = new HashSet<>();

        public boolean markIfNew(EventId eventId) {
            return processed.add(eventId);
        }

        public boolean contains(EventId eventId) {
            return processed.contains(eventId);
        }
    }

    public static final class IdempotentConsumer<T extends DomainEvent> {
        private final ProcessedEventStore processedEventStore;
        private final EventHandler<T> sideEffect;

        public IdempotentConsumer(ProcessedEventStore processedEventStore, EventHandler<T> sideEffect) {
            this.processedEventStore = processedEventStore;
            this.sideEffect = sideEffect;
        }

        public boolean handle(EventEnvelope<T> envelope) {
            if (!processedEventStore.markIfNew(envelope.eventId())) {
                return false;
            }
            sideEffect.handle(envelope);
            return true;
        }
    }

    public static final class UnsafeCheckThenActConsumer<T extends DomainEvent> {
        private final ProcessedEventStore processedEventStore;
        private final EventHandler<T> sideEffect;

        public UnsafeCheckThenActConsumer(ProcessedEventStore processedEventStore, EventHandler<T> sideEffect) {
            this.processedEventStore = processedEventStore;
            this.sideEffect = sideEffect;
        }

        public void handleWithoutAtomicMark(EventEnvelope<T> envelope) {
            if (!processedEventStore.contains(envelope.eventId())) {
                sideEffect.handle(envelope);
                processedEventStore.markIfNew(envelope.eventId());
            }
        }
    }

    public record RetryPolicy(int maxAttempts) {
        public RetryPolicy {
            if (maxAttempts < 1) {
                throw new IllegalArgumentException("maxAttempts must be positive");
            }
        }

        public boolean shouldRetry(int attempt) {
            return attempt < maxAttempts;
        }

        public long simulatedBackoffMillis(int attempt) {
            return 100L * attempt * attempt;
        }
    }

    public record DeadLetter(
            EventEnvelope<? extends DomainEvent> envelope,
            int attempts,
            String reason,
            List<Long> simulatedBackoffs
    ) {
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

    public record RetryReport(boolean processed, int attempts, List<Long> simulatedBackoffs) {
    }

    public static final class RetrySimulator {
        private final RetryPolicy retryPolicy;
        private final DeadLetterStore deadLetterStore;

        public RetrySimulator(RetryPolicy retryPolicy, DeadLetterStore deadLetterStore) {
            this.retryPolicy = retryPolicy;
            this.deadLetterStore = deadLetterStore;
        }

        public RetryReport process(
                EventEnvelope<? extends DomainEvent> envelope,
                Predicate<Integer> succeedsOnAttempt
        ) {
            List<Long> backoffs = new ArrayList<>();
            for (int attempt = 1; attempt <= retryPolicy.maxAttempts(); attempt++) {
                if (succeedsOnAttempt.test(attempt)) {
                    return new RetryReport(true, attempt, backoffs);
                }
                if (retryPolicy.shouldRetry(attempt)) {
                    backoffs.add(retryPolicy.simulatedBackoffMillis(attempt));
                }
            }
            deadLetterStore.add(new DeadLetter(envelope, retryPolicy.maxAttempts(), "retry exhausted", backoffs));
            return new RetryReport(false, retryPolicy.maxAttempts(), backoffs);
        }
    }

    public enum ApplyResult {
        APPLIED,
        DUPLICATE_OR_STALE,
        DEFERRED
    }

    public static final class VersionedOrderProjection {
        private final Map<String, Integer> versions = new HashMap<>();
        private final Map<String, String> statuses = new HashMap<>();
        private final Map<String, Queue<OrderStatusChanged>> deferred = new HashMap<>();

        public ApplyResult apply(OrderStatusChanged event) {
            int currentVersion = versions.getOrDefault(event.aggregateId(), 0);
            if (event.aggregateVersion() <= currentVersion) {
                return ApplyResult.DUPLICATE_OR_STALE;
            }
            if (event.aggregateVersion() > currentVersion + 1) {
                deferred.computeIfAbsent(event.aggregateId(), ignored -> new ArrayDeque<>()).add(event);
                return ApplyResult.DEFERRED;
            }
            applyNow(event);
            applyDeferred(event.aggregateId());
            return ApplyResult.APPLIED;
        }

        public int version(String orderId) {
            return versions.getOrDefault(orderId, 0);
        }

        public String status(String orderId) {
            return statuses.get(orderId);
        }

        public List<OrderStatusChanged> deferred(String orderId) {
            return List.copyOf(deferred.getOrDefault(orderId, new ArrayDeque<>()));
        }

        private void applyDeferred(String orderId) {
            Queue<OrderStatusChanged> queue = deferred.getOrDefault(orderId, new ArrayDeque<>());
            List<OrderStatusChanged> sorted = queue.stream()
                    .sorted(Comparator.comparingInt(OrderStatusChanged::aggregateVersion))
                    .toList();
            queue.clear();
            for (OrderStatusChanged event : sorted) {
                if (event.aggregateVersion() == version(orderId) + 1) {
                    applyNow(event);
                } else {
                    queue.add(event);
                }
            }
        }

        private void applyNow(OrderStatusChanged event) {
            versions.put(event.aggregateId(), event.aggregateVersion());
            statuses.put(event.aggregateId(), event.status());
        }
    }

    public record OutboxEntry(EventId eventId, EventEnvelope<? extends DomainEvent> envelope, int attempts) {
        OutboxEntry incrementAttempts() {
            return new OutboxEntry(eventId, envelope, attempts + 1);
        }
    }

    public static final class TransactionalOutbox {
        private final Map<String, String> domainState = new HashMap<>();
        private final Queue<OutboxEntry> unpublished = new ArrayDeque<>();

        public void saveOrderAndEvent(String orderId, String status, EventEnvelope<? extends DomainEvent> envelope) {
            domainState.put(orderId, status);
            unpublished.add(new OutboxEntry(envelope.eventId(), envelope, 0));
        }

        public String status(String orderId) {
            return domainState.get(orderId);
        }

        public Optional<OutboxEntry> peek() {
            return Optional.ofNullable(unpublished.peek());
        }

        public void markPublished(EventId eventId) {
            if (unpublished.peek() != null && unpublished.peek().eventId().equals(eventId)) {
                unpublished.remove();
            }
        }

        public void retainWithAttempt(OutboxEntry entry) {
            unpublished.remove();
            unpublished.add(entry.incrementAttempts());
        }

        public int unpublishedCount() {
            return unpublished.size();
        }
    }

    public static final class OutboxRelay {
        private final TransactionalOutbox outbox;
        private final InMemoryEventBus eventBus;
        private boolean failNextPublication;

        public OutboxRelay(TransactionalOutbox outbox, InMemoryEventBus eventBus) {
            this.outbox = outbox;
            this.eventBus = eventBus;
        }

        public void failNextPublication() {
            failNextPublication = true;
        }

        public boolean publishNext() {
            Optional<OutboxEntry> next = outbox.peek();
            if (next.isEmpty()) {
                return false;
            }
            OutboxEntry entry = next.get();
            if (failNextPublication) {
                failNextPublication = false;
                outbox.retainWithAttempt(entry);
                return false;
            }
            eventBus.publish(entry.envelope());
            outbox.markPublished(entry.eventId());
            return true;
        }
    }

    public static <T extends DomainEvent> EventEnvelope<T> envelope(String id, T payload) {
        return new EventEnvelope<>(
                new EventId(id),
                new EventType(payload.getClass().getSimpleName()),
                new EventVersion(1),
                "java-event-driven-foundations",
                Instant.parse("2026-01-01T00:00:00Z"),
                new CorrelationId("correlation-" + payload.aggregateId()),
                new CausationId("causation-" + id),
                payload,
                Map.of("contentType", "application/java-record")
        );
    }

    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }
}
