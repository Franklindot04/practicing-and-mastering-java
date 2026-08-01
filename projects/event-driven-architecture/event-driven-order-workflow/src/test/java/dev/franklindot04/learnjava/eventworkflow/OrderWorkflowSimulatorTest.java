package dev.franklindot04.learnjava.eventworkflow;

import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.CausationId;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.CorrelationId;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.EventBus;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.EventEnvelope;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.EventId;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.FailureInjector;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.InventoryReserved;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.NotificationRequested;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.OrderConfirmed;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.OrderSubmitted;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.OrderWorkflow;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.OrderingGuard;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.PaymentAuthorized;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.PaymentRejected;
import dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.WorkflowStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.TransitionResult.APPLIED;
import static dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.TransitionResult.OUT_OF_ORDER;
import static dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.TransitionResult.STALE_OR_DUPLICATE;
import static dev.franklindot04.learnjava.eventworkflow.OrderWorkflowSimulator.envelope;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderWorkflowSimulatorTest {
    @Test
    void envelopeValidatesRequiredFields() {
        OrderSubmitted event = new OrderSubmitted("order-1", 1, 1200);

        EventEnvelope<OrderSubmitted> envelope =
                envelope(event, new CorrelationId("corr-1"), new CausationId("request-1"));

        assertEquals("OrderSubmitted", envelope.eventType());
        assertEquals("order-1", envelope.partitionKey());
        assertEquals("corr-1", envelope.correlationId().value());
        assertEquals("request-1", envelope.causationId().value());
        assertThrows(IllegalArgumentException.class, () -> new EventId(""));
    }

    @Test
    void eventIdsAreUnique() {
        assertNotEquals(EventId.random(), EventId.random());
    }

    @Test
    void correlationAndCausationPropagateAcrossWorkflow() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());

        workflow.submit("order-2", 2000);

        List<EventEnvelope<? extends OrderWorkflowSimulator.DomainEvent>> events = workflow.eventLog().events();
        assertTrue(events.stream().allMatch(event -> event.correlationId().value().equals("corr-order-2")));
        assertEquals(events.get(0).eventId().value(), events.get(1).causationId().value());
    }

    @Test
    void eventBusRegistersMultipleHandlersInDeterministicOrder() {
        EventBus bus = new EventBus();
        List<String> handled = new ArrayList<>();
        bus.subscribe(OrderSubmitted.class, "first", event -> handled.add("first"));
        bus.subscribe(OrderSubmitted.class, "second", event -> handled.add("second"));

        var report = bus.publish(envelope(new OrderSubmitted("order-3", 1, 3000),
                new CorrelationId("corr-3"), new CausationId("cause-3")));

        assertEquals(List.of("first", "second"), handled);
        assertEquals(2, report.deliveredHandlers());
    }

    @Test
    void eventBusReportsHandlerFailureWithoutSkippingOtherHandlers() {
        EventBus bus = new EventBus();
        List<String> handled = new ArrayList<>();
        bus.subscribe(OrderSubmitted.class, "broken", event -> {
            throw new IllegalStateException("boom");
        });
        bus.subscribe(OrderSubmitted.class, "after", event -> handled.add("after"));

        var report = bus.publish(envelope(new OrderSubmitted("order-4", 1, 4000),
                new CorrelationId("corr-4"), new CausationId("cause-4")));

        assertEquals(List.of("after"), handled);
        assertEquals(1, report.failures().size());
    }

    @Test
    void healthyWorkflowCompletes() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());

        workflow.submit("order-5", 5000);

        assertEquals(WorkflowStatus.COMPLETED, workflow.state("order-5").status());
        assertEquals(List.of(
                "OrderSubmitted",
                "InventoryReserved",
                "PaymentAuthorizationRequested",
                "PaymentAuthorized",
                "OrderConfirmed",
                "NotificationRequested"
        ), workflow.eventLog().eventTypes());
        assertTrue(workflow.sideEffects().contains("notification-requested:order-5"));
    }

    @Test
    void duplicateSubmissionDoesNotDuplicateInventoryOrPayment() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());

        workflow.submit("order-6", 6000);
        workflow.submit("order-6", 6000);

        assertEquals(1, workflow.sideEffects().stream().filter("inventory-reserved:order-6"::equals).count());
        assertEquals(1, workflow.sideEffects().stream().filter("payment-authorized:order-6"::equals).count());
        assertEquals(1, workflow.report().duplicateEventCount());
    }

    @Test
    void duplicateEventDeliveryIsIdempotent() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());
        EventEnvelope<OrderSubmitted> event = envelope(new OrderSubmitted("order-7", 1, 7000),
                new CorrelationId("corr-7"), new CausationId("cause-7"));

        workflow.publishDuplicate(event);

        assertEquals(1, workflow.sideEffects().stream().filter("inventory-reserved:order-7"::equals).count());
        assertEquals(1, workflow.report().duplicateEventCount());
    }

    @Test
    void inventoryUnavailableCancelsWithoutPayment() {
        FailureInjector failures = new FailureInjector();
        failures.inventoryUnavailable("order-8");
        OrderWorkflow workflow = new OrderWorkflow(failures);

        workflow.submit("order-8", 8000);

        assertEquals(WorkflowStatus.CANCELLED, workflow.state("order-8").status());
        assertFalse(workflow.sideEffects().contains("payment-authorized:order-8"));
    }

    @Test
    void paymentRejectedCompensatesWithInventoryReleaseAndCancellation() {
        FailureInjector failures = new FailureInjector();
        failures.paymentRejected("order-9");
        OrderWorkflow workflow = new OrderWorkflow(failures);

        workflow.submit("order-9", 9000);

        assertEquals(WorkflowStatus.CANCELLED, workflow.state("order-9").status());
        assertTrue(workflow.state("order-9").compensationRequired());
        assertTrue(workflow.sideEffects().contains("released:order-9"));
        assertTrue(workflow.sideEffects().contains("cancelled:order-9"));
    }

    @Test
    void compensationFailureIsVisibleInSagaState() {
        FailureInjector failures = new FailureInjector();
        failures.paymentRejected("order-10");
        failures.compensationFailure("order-10");
        OrderWorkflow workflow = new OrderWorkflow(failures);

        workflow.submit("order-10", 10000);

        assertEquals(WorkflowStatus.FAILED, workflow.state("order-10").status());
        assertTrue(workflow.state("order-10").compensationFailed());
    }

    @Test
    void transientPaymentFailureRetriesAndWorkflowContinues() {
        FailureInjector failures = new FailureInjector();
        failures.transientPaymentFailures("order-11", 2);
        OrderWorkflow workflow = new OrderWorkflow(failures);

        workflow.submit("order-11", 11000);

        assertEquals(WorkflowStatus.COMPLETED, workflow.state("order-11").status());
        assertEquals(2, workflow.report().retryCount());
        assertEquals(0, workflow.report().deadLetterCount());
    }

    @Test
    void poisonEventIsDeadLetteredAfterPermanentFailure() {
        FailureInjector failures = new FailureInjector();
        failures.poisonOrder("order-12");
        OrderWorkflow workflow = new OrderWorkflow(failures);

        workflow.submit("order-12", 12000);

        assertEquals(1, workflow.deadLetters().all().size());
        assertEquals("poison order", workflow.deadLetters().all().get(0).reason());
        assertEquals(1, workflow.report().deadLetterCount());
    }

    @Test
    void deadLetterReplayAfterRepairConvergesWorkflow() {
        FailureInjector failures = new FailureInjector();
        failures.poisonOrder("order-13");
        OrderWorkflow workflow = new OrderWorkflow(failures);
        workflow.submit("order-13", 13000);

        failures = new FailureInjector();
        OrderWorkflow repaired = new OrderWorkflow(failures);
        repaired.publishDuplicate(workflow.deadLetters().all().get(0).envelope());

        assertEquals(WorkflowStatus.COMPLETED, repaired.state("order-13").status());
    }

    @Test
    void notificationFailureRecordsFailureEventButOrderRemainsConfirmed() {
        FailureInjector failures = new FailureInjector();
        failures.notificationFailure("order-14");
        OrderWorkflow workflow = new OrderWorkflow(failures);

        workflow.submit("order-14", 14000);

        assertEquals(WorkflowStatus.NOTIFICATION_REQUESTED, workflow.state("order-14").status());
        assertTrue(workflow.eventLog().eventTypes().contains("NotificationFailed"));
        assertTrue(workflow.sideEffects().contains("notification-failed:order-14"));
    }

    @Test
    void orderingGuardDefersOutOfOrderEventAndConverges() {
        OrderingGuard guard = new OrderingGuard();

        assertEquals(OUT_OF_ORDER, guard.apply(new PaymentAuthorized("order-15", 4)));
        assertEquals(APPLIED, guard.apply(new OrderSubmitted("order-15", 1, 15000)));
        assertEquals(APPLIED, guard.apply(new InventoryReserved("order-15", 2)));
        assertEquals(APPLIED, guard.apply(new PaymentAuthorized("order-15", 3)));

        assertEquals(4, guard.version("order-15"));
        assertEquals(0, guard.deferredCount());
    }

    @Test
    void orderingGuardRejectsStaleVersion() {
        OrderingGuard guard = new OrderingGuard();

        assertEquals(APPLIED, guard.apply(new OrderSubmitted("order-16", 1, 16000)));
        assertEquals(STALE_OR_DUPLICATE, guard.apply(new OrderSubmitted("order-16", 1, 16000)));
    }

    @Test
    void invalidEnvelopeTransitionIsRejectedByOrderingGuard() {
        OrderingGuard guard = new OrderingGuard();

        assertEquals(OUT_OF_ORDER, guard.apply(new OrderConfirmed("order-17", 5)));

        assertEquals(0, guard.version("order-17"));
        assertEquals(1, guard.deferredCount());
    }

    @Test
    void outboxPublicationFailureRetainsEventThenPublishesEventually() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());
        workflow.relay().failNextPublication();
        workflow.outbox().add(envelope(new OrderSubmitted("order-18", 1, 18000),
                new CorrelationId("corr-18"), new CausationId("submit-18")));

        assertFalse(workflow.relay().publishNext());
        assertEquals(1, workflow.outbox().unpublishedCount());
        assertTrue(workflow.relay().publishNext());

        assertEquals(0, workflow.outbox().unpublishedCount());
    }

    @Test
    void duplicatePublicationRemainsSafe() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());
        EventEnvelope<OrderSubmitted> event = envelope(new OrderSubmitted("order-19", 1, 19000),
                new CorrelationId("corr-19"), new CausationId("submit-19"));

        workflow.publishDuplicate(event);

        assertEquals(1, workflow.sideEffects().stream().filter("inventory-reserved:order-19"::equals).count());
        assertEquals(1, workflow.sideEffects().stream().filter("payment-authorized:order-19"::equals).count());
    }

    @Test
    void eventLogPreservesDeterministicEventOrdering() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());

        workflow.submit("order-20", 20000);

        assertEquals("OrderSubmitted", workflow.eventLog().eventTypes().get(0));
        assertEquals("NotificationRequested", workflow.eventLog().eventTypes().get(5));
    }

    @Test
    void workflowReportSummarizesOperationalState() {
        FailureInjector failures = new FailureInjector();
        failures.poisonOrder("order-21");
        OrderWorkflow workflow = new OrderWorkflow(failures);

        workflow.submit("order-21", 21000);

        assertEquals(1, workflow.report().eventCount());
        assertEquals(1, workflow.report().deadLetterCount());
        assertEquals(0, workflow.report().outboxBacklog());
        assertEquals(List.of("OrderSubmitted"), workflow.report().eventTypes());
    }

    @Test
    void envelopeMetadataIsImmutable() {
        EventEnvelope<OrderSubmitted> envelope = new EventEnvelope<>(
                EventId.random(),
                "OrderSubmitted",
                1,
                "test",
                java.time.Instant.parse("2026-01-01T00:00:00Z"),
                new CorrelationId("corr-22"),
                new CausationId("cause-22"),
                "order-22",
                new OrderSubmitted("order-22", 1, 22000),
                Map.of("schema", "v1")
        );

        assertThrows(UnsupportedOperationException.class, () -> envelope.metadata().put("schema", "v2"));
    }

    @Test
    void paymentRejectedEventCanBeHandledAsAStandaloneCompensationTrigger() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());
        workflow.submit("order-23", 23000);

        workflow.publishDuplicate(envelope(new PaymentRejected("order-23", 8, "manual rejection"),
                new CorrelationId("corr-order-23"), new CausationId("manual")));

        assertTrue(workflow.sideEffects().contains("released:order-23"));
    }

    @Test
    void notificationRequestedEventIsPartOfHealthyFlow() {
        OrderWorkflow workflow = new OrderWorkflow(new FailureInjector());

        workflow.submit("order-24", 24000);

        assertTrue(workflow.eventLog().events().stream()
                .anyMatch(event -> event.payload() instanceof NotificationRequested));
    }
}
