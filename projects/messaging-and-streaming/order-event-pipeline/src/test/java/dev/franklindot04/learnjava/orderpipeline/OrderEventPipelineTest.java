package dev.franklindot04.learnjava.orderpipeline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class OrderEventPipelineTest {
    @Test
    void healthyPipelineUpdatesProjectionAnalyticsAndNotification() {
        var pipeline = new OrderEventPipeline();

        pipeline.runHealthyWorkflow("order-1");

        assertEquals(OrderEventPipeline.OrderStatus.NOTIFICATION_REQUESTED, pipeline.projection().get("order-1"));
        assertEquals(List.of("order-1"), pipeline.notifications());
        assertEquals(6, pipeline.metrics().processed());
        assertFalse(pipeline.aggregateByStatus(Instant.parse("2026-08-03T10:02:00Z")).isEmpty());
    }

    @Test
    void duplicateMessageDoesBusinessSideEffectOnce() {
        var pipeline = new OrderEventPipeline();
        var submitted = pipeline.submit("order-2", 1500);

        assertEquals(OrderEventPipeline.ProcessingOutcome.ACKNOWLEDGED,
                pipeline.process(submitted, OrderEventPipeline.FailureMode.NONE));
        assertEquals(OrderEventPipeline.ProcessingOutcome.DUPLICATE,
                pipeline.process(submitted, OrderEventPipeline.FailureMode.NONE));
        assertEquals(1, pipeline.metrics().duplicates());
    }

    @Test
    void consumerFailureBeforeAcknowledgementCanBeSafelyRedelivered() {
        var pipeline = new OrderEventPipeline();
        var submitted = pipeline.submit("order-3", 1800);

        assertEquals(OrderEventPipeline.ProcessingOutcome.REDELIVER,
                pipeline.process(submitted, OrderEventPipeline.FailureMode.CRASH_BEFORE_ACK));
        assertEquals(OrderEventPipeline.ProcessingOutcome.ACKNOWLEDGED,
                pipeline.process(submitted, OrderEventPipeline.FailureMode.NONE));
        assertEquals(OrderEventPipeline.OrderStatus.SUBMITTED, pipeline.projection().get("order-3"));
    }

    @Test
    void poisonMessageIsDeadLetteredAndReported() {
        var pipeline = new OrderEventPipeline();
        var submitted = pipeline.submit("order-4", 2100);

        assertEquals(OrderEventPipeline.ProcessingOutcome.DEAD_LETTERED,
                pipeline.process(submitted, OrderEventPipeline.FailureMode.PERMANENT));
        assertEquals("orders.dlq", pipeline.deadLetters().get(0).destination());
        assertEquals(1, pipeline.metrics().retryExhausted());
    }

    @Test
    void partitionOrderingAndRebalanceAreRepresented() {
        var pipeline = new OrderEventPipeline();
        var first = pipeline.submit("order-5", 1100);
        var second = pipeline.submit("order-5", 1200);
        var assignment = pipeline.rebalance(List.of("consumer-a", "consumer-b"), 3);

        assertEquals(first.partition(), second.partition());
        assertTrue(second.offset() > first.offset());
        assertEquals("consumer-a", assignment.assignments().get(0));
        assertEquals("consumer-b", assignment.assignments().get(1));
    }

    @Test
    void schemaEvolutionAcceptsCompatiblePayloadAndRejectsIncompatibleVersion() {
        var pipeline = new OrderEventPipeline();

        assertTrue(pipeline.deserialize(Map.of("orderId", "order-6", "newField", "ignored"), 2).compatible());
        assertFalse(pipeline.deserialize(Map.of("orderId", "order-6"), 9).compatible());
        assertFalse(pipeline.deserialize(Map.of("missing", "orderId"), 1).compatible());
    }

    @Test
    void replayRebuildsProjectionWithoutRepeatingProtectedNotifications() {
        var pipeline = new OrderEventPipeline();
        pipeline.runHealthyWorkflow("order-7");

        pipeline.replayProjectionOnly();

        assertEquals(OrderEventPipeline.OrderStatus.NOTIFICATION_REQUESTED, pipeline.projection().get("order-7"));
        assertEquals(1, pipeline.notifications().size());
    }

    @Test
    void rabbitMqRoutingPublisherConfirmAndJmsComparisonAreModeled() {
        var pipeline = new OrderEventPipeline();
        var submitted = pipeline.submit("order-8", 1300);

        assertEquals(List.of("payments.queue"), pipeline.routeRabbit("direct", "orders.paid").queues());
        assertEquals(List.of("orders.audit.queue", "orders.analytics.queue"), pipeline.routeRabbit("topic", "orders.any").queues());
        assertEquals(List.of("notifications.queue", "analytics.queue"), pipeline.routeRabbit("fanout", "").queues());
        assertTrue(pipeline.publish(submitted, true, false).accepted());
        assertTrue(pipeline.jmsComparison().queueBehavior().contains("competing consumers"));
        assertTrue(pipeline.jmsComparison().topicBehavior().contains("broadcasts"));
    }

    @Test
    void lagGracefulShutdownAndLateEventsAreVisible() {
        var pipeline = new OrderEventPipeline();
        var submitted = pipeline.submit("order-9", 1000);
        pipeline.process(submitted, OrderEventPipeline.FailureMode.NONE);

        assertEquals(1, pipeline.lag("order-9", 1));
        assertTrue(pipeline.aggregateByStatus(Instant.parse("2026-08-03T10:03:00Z"))
                .stream()
                .anyMatch(window -> window.lateEvents() > 0));
        pipeline.shutdown();
        var next = pipeline.submit("order-10", 1000);
        assertEquals(OrderEventPipeline.ProcessingOutcome.SHUTDOWN,
                pipeline.process(next, OrderEventPipeline.FailureMode.NONE));
    }
}
