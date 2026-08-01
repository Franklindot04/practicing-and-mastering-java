package dev.franklindot04.learnjava.eventdriven;

import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.DeadLetterStore;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.EventEnvelope;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.EventId;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.IdempotentConsumer;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.InMemoryEventBus;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.OrderStatusChanged;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.OrderSubmitted;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.OutboxRelay;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.ProcessedEventStore;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.RetryPolicy;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.RetrySimulator;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.TransactionalOutbox;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.UnsafeCheckThenActConsumer;
import dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.VersionedOrderProjection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.ApplyResult.APPLIED;
import static dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.ApplyResult.DEFERRED;
import static dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.ApplyResult.DUPLICATE_OR_STALE;
import static dev.franklindot04.learnjava.eventdriven.EventDrivenExamples.envelope;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventDrivenExamplesTest {
    @Test
    void eventEnvelopeValidatesRequiredFields() {
        OrderSubmitted event = new OrderSubmitted("order-1", 1, 2500);

        EventEnvelope<OrderSubmitted> envelope = envelope("event-1", event);

        assertEquals("event-1", envelope.eventId().value());
        assertEquals("OrderSubmitted", envelope.type().value());
        assertEquals("correlation-order-1", envelope.correlationId().value());
        assertEquals("causation-event-1", envelope.causationId().value());
        assertThrows(IllegalArgumentException.class, () -> new EventId(" "));
    }

    @Test
    void inMemoryBusPublishesToMultipleConsumersAndIsolatesFailure() {
        InMemoryEventBus bus = new InMemoryEventBus();
        List<String> handled = new ArrayList<>();
        bus.subscribe(OrderSubmitted.class, "projection", event -> handled.add("projection"));
        bus.subscribe(OrderSubmitted.class, "broken", event -> {
            throw new IllegalStateException("simulated failure");
        });
        bus.subscribe(OrderSubmitted.class, "audit", event -> handled.add("audit"));

        var report = bus.publish(envelope("event-2", new OrderSubmitted("order-2", 1, 5000)));

        assertEquals(List.of("projection", "audit"), handled);
        assertEquals(3, report.deliveredHandlers());
        assertEquals(1, report.failures().size());
        assertFalse(report.succeeded());
    }

    @Test
    void idempotentConsumerAppliesOneLogicalSideEffectForDuplicateDelivery() {
        ProcessedEventStore store = new ProcessedEventStore();
        List<String> sideEffects = new ArrayList<>();
        IdempotentConsumer<OrderSubmitted> consumer =
                new IdempotentConsumer<>(store, event -> sideEffects.add(event.payload().aggregateId()));
        EventEnvelope<OrderSubmitted> duplicate = envelope("event-3", new OrderSubmitted("order-3", 1, 7000));

        assertTrue(consumer.handle(duplicate));
        assertFalse(consumer.handle(duplicate));

        assertEquals(List.of("order-3"), sideEffects);
    }

    @Test
    void unsafeCheckThenActShapeCanRepeatSideEffectBeforeMarking() {
        ProcessedEventStore store = new ProcessedEventStore();
        List<String> sideEffects = new ArrayList<>();
        UnsafeCheckThenActConsumer<OrderSubmitted> consumer =
                new UnsafeCheckThenActConsumer<>(store, event -> sideEffects.add(event.payload().aggregateId()));
        EventEnvelope<OrderSubmitted> event = envelope("event-4", new OrderSubmitted("order-4", 1, 9000));

        consumer.handleWithoutAtomicMark(event);
        store = new ProcessedEventStore();
        consumer = new UnsafeCheckThenActConsumer<>(store, e -> sideEffects.add(e.payload().aggregateId()));
        consumer.handleWithoutAtomicMark(event);

        assertEquals(List.of("order-4", "order-4"), sideEffects);
    }

    @Test
    void retrySimulatorRecordsBackoffDataAndSucceedsWithoutSleeping() {
        DeadLetterStore deadLetters = new DeadLetterStore();
        RetrySimulator simulator = new RetrySimulator(new RetryPolicy(3), deadLetters);

        var report = simulator.process(
                envelope("event-5", new OrderSubmitted("order-5", 1, 1000)),
                attempt -> attempt == 3
        );

        assertTrue(report.processed());
        assertEquals(3, report.attempts());
        assertEquals(List.of(100L, 400L), report.simulatedBackoffs());
        assertTrue(deadLetters.all().isEmpty());
    }

    @Test
    void retryExhaustionCreatesDeadLetterAndReplayCanSucceedAfterCorrection() {
        DeadLetterStore deadLetters = new DeadLetterStore();
        RetrySimulator simulator = new RetrySimulator(new RetryPolicy(2), deadLetters);
        EventEnvelope<OrderSubmitted> event = envelope("event-6", new OrderSubmitted("order-6", 1, 1000));

        var failed = simulator.process(event, attempt -> false);
        var deadLetter = deadLetters.takeFirst().orElseThrow();
        var replay = simulator.process(deadLetter.envelope(), attempt -> true);

        assertFalse(failed.processed());
        assertEquals(2, deadLetter.attempts());
        assertTrue(replay.processed());
    }

    @Test
    void projectionDefersMissingVersionAndConvergesWhenGapArrives() {
        VersionedOrderProjection projection = new VersionedOrderProjection();

        assertEquals(DEFERRED, projection.apply(new OrderStatusChanged("order-7", 2, "PAID")));
        assertEquals(0, projection.version("order-7"));
        assertEquals(APPLIED, projection.apply(new OrderStatusChanged("order-7", 1, "SUBMITTED")));

        assertEquals(2, projection.version("order-7"));
        assertEquals("PAID", projection.status("order-7"));
        assertTrue(projection.deferred("order-7").isEmpty());
    }

    @Test
    void projectionRejectsStaleAndDuplicateVersions() {
        VersionedOrderProjection projection = new VersionedOrderProjection();

        assertEquals(APPLIED, projection.apply(new OrderStatusChanged("order-8", 1, "SUBMITTED")));
        assertEquals(DUPLICATE_OR_STALE, projection.apply(new OrderStatusChanged("order-8", 1, "SUBMITTED")));
        assertEquals(DUPLICATE_OR_STALE, projection.apply(new OrderStatusChanged("order-8", 0, "OLD")));
    }

    @Test
    void transactionalOutboxRetainsEventAfterPublicationFailureThenPublishes() {
        InMemoryEventBus bus = new InMemoryEventBus();
        TransactionalOutbox outbox = new TransactionalOutbox();
        OutboxRelay relay = new OutboxRelay(outbox, bus);
        outbox.saveOrderAndEvent(
                "order-9",
                "SUBMITTED",
                envelope("event-9", new OrderSubmitted("order-9", 1, 3400))
        );

        relay.failNextPublication();
        assertFalse(relay.publishNext());
        assertEquals(1, outbox.unpublishedCount());
        assertEquals("SUBMITTED", outbox.status("order-9"));
        assertTrue(relay.publishNext());

        assertEquals(0, outbox.unpublishedCount());
        assertEquals(1, bus.published().size());
    }

    @Test
    void duplicateOutboxPublicationRemainsSafeWithIdempotentConsumer() {
        InMemoryEventBus bus = new InMemoryEventBus();
        ProcessedEventStore processed = new ProcessedEventStore();
        List<String> sideEffects = new ArrayList<>();
        IdempotentConsumer<OrderSubmitted> consumer =
                new IdempotentConsumer<>(processed, event -> sideEffects.add(event.payload().aggregateId()));
        bus.subscribe(OrderSubmitted.class, "idempotent-consumer", consumer::handle);
        EventEnvelope<OrderSubmitted> event = envelope("event-10", new OrderSubmitted("order-10", 1, 4400));

        bus.publish(event);
        bus.publish(event);

        assertEquals(List.of("order-10"), sideEffects);
    }
}
