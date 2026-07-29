package dev.franklindot04.learnjava.performance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class PerformancePatternsTest {
    @Test
    void indexedLookupMatchesRepeatedLookup() {
        List<String> ids = List.of("a", "b", "c");
        Set<String> index = PerformancePatterns.buildIndex(ids);

        assertEquals(PerformancePatterns.repeatedLookup(ids, "b"), PerformancePatterns.indexedLookup(index, "b"));
        assertFalse(PerformancePatterns.indexedLookup(index, "missing"));
    }

    @Test
    void stringBuilderJoinPreservesValues() {
        assertEquals("alpha,beta,gamma", PerformancePatterns.joinWithBuilder(List.of("alpha", "beta", "gamma")));
    }

    @Test
    void primitiveAndBoxedSumsAreEquivalent() {
        assertEquals(15, PerformancePatterns.boxedSum(List.of(1, 2, 3, 4, 5)));
        assertEquals(15, PerformancePatterns.primitiveSum(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    void defensiveCopyCannotBeChangedThroughOriginalList() {
        List<String> mutable = new ArrayList<>(List.of("one"));
        List<String> copy = PerformancePatterns.defensiveCopy(mutable);

        mutable.add("two");

        assertEquals(List.of("one"), copy);
    }

    @Test
    void chunksSplitInputWithoutDroppingItems() {
        assertEquals(List.of(List.of("a", "b"), List.of("c", "d"), List.of("e")),
                PerformancePatterns.chunks(List.of("a", "b", "c", "d", "e"), 2));
    }

    @Test
    void chunkSizeMustBePositive() {
        assertThrows(IllegalArgumentException.class, () -> PerformancePatterns.chunks(List.of("a"), 0));
    }

    @Test
    void totalsByCustomerAggregatesLines() {
        List<PerformancePatterns.OrderLine> lines = List.of(
                new PerformancePatterns.OrderLine("c1", BigDecimal.valueOf(10), 2),
                new PerformancePatterns.OrderLine("c1", BigDecimal.valueOf(5), 3),
                new PerformancePatterns.OrderLine("c2", BigDecimal.valueOf(7), 1));

        assertEquals(BigDecimal.valueOf(35), PerformancePatterns.totalsByCustomer(lines).get("c1"));
        assertEquals(BigDecimal.valueOf(7), PerformancePatterns.totalsByCustomer(lines).get("c2"));
    }

    @Test
    void parseOnceNormalizesEventType() {
        List<PerformancePatterns.ParsedEvent> parsed = PerformancePatterns.parseOnce(List.of("e1,2026-07-29,created"));

        assertEquals("CREATED", parsed.get(0).type());
    }

    @Test
    void shortCircuitFindsMatchingPrefix() {
        assertTrue(PerformancePatterns.anyStartsWith(List.of("cold", "hot-1", "hot-2"), "hot"));
        assertEquals(List.of("hot-1", "hot-2"),
                PerformancePatterns.expensiveFilter(List.of("cold", "hot-1", "hot-2"), "hot"));
    }

    @Test
    void boundedCacheEvictsLeastRecentlyUsedEntry() {
        BoundedCache<String, Integer> cache = new BoundedCache<>(2);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.get("a");
        cache.put("c", 3);

        assertTrue(cache.get("a").isPresent());
        assertTrue(cache.get("c").isPresent());
        assertFalse(cache.get("b").isPresent());
        assertEquals(2, cache.size());
    }

    @Test
    void memoizerComputesEachKeyOnce() {
        AtomicInteger calls = new AtomicInteger();
        Memoizer<String, String> memoizer = new Memoizer<>(key -> {
            calls.incrementAndGet();
            return key.toUpperCase();
        });

        assertEquals("ALPHA", memoizer.get("alpha"));
        assertEquals("ALPHA", memoizer.get("alpha"));
        assertEquals(1, calls.get());
        assertEquals(1, memoizer.cachedValues());
    }

    @Test
    void boundedExecutorCollectsResults() throws Exception {
        try (BoundedExecutor executor = new BoundedExecutor(2)) {
            List<Callable<Integer>> tasks = List.of(() -> 1, () -> 2, () -> 3);

            assertEquals(List.of(1, 2, 3), executor.invokeAll(tasks));
        }
    }

    @Test
    void threadPoolSizingFormulaAccountsForBlockingFraction() {
        assertEquals(8, BoundedExecutor.suggestedThreads(4, 0.5));
        assertThrows(IllegalArgumentException.class, () -> BoundedExecutor.suggestedThreads(4, 1.0));
    }

    @Test
    void concurrentCounterPreservesAllIncrements() {
        PerformancePatterns.Counter counter = new PerformancePatterns.Counter();
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }

        assertEquals(10, counter.sum());
    }

    @Test
    void elapsedTimeMeasurementReturnsNonNegativeDuration() {
        assertTrue(PerformancePatterns.measureElapsedNanos(() -> {
        }) >= 0L);
    }
}
