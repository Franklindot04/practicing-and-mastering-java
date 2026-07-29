package dev.franklindot04.learnjava.perflab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class JvmPerformanceLabTest {
    private static final List<String> RAW_EVENTS = List.of(
            "e1|c1|purchase|10.50|2026-07-29T10:00:00Z",
            "e2|c1|purchase|4.50|2026-07-29T10:01:00Z",
            "e3|c2|refund|2.00|2026-07-29T10:02:00Z",
            "e2|c1|purchase|4.50|2026-07-29T10:03:00Z");

    @Test
    void parserReadsValidEvent() {
        Event event = new EventParser().parse(RAW_EVENTS.get(0));

        assertEquals("e1", event.id());
        assertEquals("c1", event.customerId());
        assertEquals(new BigDecimal("10.50"), event.amount());
        assertEquals(Instant.parse("2026-07-29T10:00:00Z"), event.occurredAt());
    }

    @Test
    void parserRejectsMalformedInput() {
        assertThrows(IllegalArgumentException.class, () -> new EventParser().parse("too|short"));
    }

    @Test
    void validatorRejectsNegativeAmountAndDuplicates() {
        EventValidator validator = new EventValidator();
        Event valid = new Event("e1", "c1", "purchase", BigDecimal.ONE, Instant.EPOCH);
        Event negative = new Event("e2", "c1", "purchase", BigDecimal.valueOf(-1), Instant.EPOCH);

        assertTrue(validator.accept(valid));
        assertFalse(validator.accept(valid));
        assertFalse(validator.accept(negative));
    }

    @Test
    void aggregatorTotalsByCustomer() {
        EventAggregator aggregator = new EventAggregator();
        aggregator.add(new Event("e1", "c1", "purchase", BigDecimal.ONE, Instant.EPOCH));
        aggregator.add(new Event("e2", "c1", "purchase", BigDecimal.TEN, Instant.EPOCH));

        assertEquals(Map.of("c1", BigDecimal.valueOf(11)), aggregator.snapshotTotals());
        assertEquals(2, aggregator.acceptedEvents());
    }

    @Test
    void batchProcessorPreservesItems() {
        List<Integer> doubled = new BatchProcessor().process(List.of(1, 2, 3, 4, 5), 2,
                batch -> batch.stream().map(value -> value * 2).toList());

        assertEquals(List.of(2, 4, 6, 8, 10), doubled);
    }

    @Test
    void boundedBufferEnforcesCapacity() {
        BoundedEventBuffer buffer = new BoundedEventBuffer(1);
        Event event = new Event("e1", "c1", "purchase", BigDecimal.ONE, Instant.EPOCH);

        assertTrue(buffer.offer(event));
        assertFalse(buffer.offer(event));
        assertEquals(event, buffer.poll().orElseThrow());
    }

    @Test
    void cacheEvictsOlderEntries() {
        LruCache<String, String> cache = new LruCache<>(2);
        cache.put("a", "one");
        cache.put("b", "two");
        cache.put("c", "three");

        assertFalse(cache.get("a").isPresent());
        assertEquals(2, cache.size());
    }

    @Test
    void metricsCalculateAcceptanceRate() {
        assertEquals(0.75, new MetricsSnapshot(4, 3, 1, 1).acceptanceRate());
        assertEquals(0.0, new MetricsSnapshot(0, 0, 0, 0).acceptanceRate());
    }

    @Test
    void naiveProcessorAggregatesAndTracksDuplicate() {
        ProcessingResult result = new NaiveEventProcessor().process(RAW_EVENTS);

        assertEquals(new BigDecimal("15.00"), result.totals().get("c1"));
        assertEquals(3, result.metrics().accepted());
        assertEquals(1, result.metrics().duplicates());
    }

    @Test
    void optimizedProcessorMatchesNaiveProcessor() {
        assertEquals(new NaiveEventProcessor().process(RAW_EVENTS), new OptimizedEventProcessor().process(RAW_EVENTS));
    }

    @Test
    void concurrentProcessorCountsEverySubmittedItem() throws Exception {
        ConcurrentEventProcessor processor = new ConcurrentEventProcessor(new ExecutorConfiguration(2, 10));

        assertEquals(5, processor.processIds(List.of("a", "b", "c", "d", "e")));
    }

    @Test
    void executorConfigurationRejectsInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new ExecutorConfiguration(0, 10));
    }

    @Test
    void bufferPollReleasesReferenceForRetentionPrevention() {
        BoundedEventBuffer buffer = new BoundedEventBuffer(2);
        Event event = new Event("e1", "c1", "purchase", BigDecimal.ONE, Instant.EPOCH);
        buffer.offer(event);

        assertEquals(event, buffer.poll().orElseThrow());
        assertTrue(buffer.poll().isEmpty());
        assertEquals(0, buffer.size());
    }
}
