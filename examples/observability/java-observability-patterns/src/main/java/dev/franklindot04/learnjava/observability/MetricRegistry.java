package dev.franklindot04.learnjava.observability;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

public final class MetricRegistry {
    private final Map<String, AtomicLong> counters = new ConcurrentHashMap<>();
    private final Map<String, LongSupplier> gauges = new ConcurrentHashMap<>();
    private final Map<String, List<Long>> timers = new ConcurrentHashMap<>();

    public void increment(String name) {
        counters.computeIfAbsent(requireName(name), ignored -> new AtomicLong()).incrementAndGet();
    }

    public long counterValue(String name) {
        return counters.getOrDefault(requireName(name), new AtomicLong()).get();
    }

    public void gauge(String name, LongSupplier supplier) {
        gauges.put(requireName(name), Objects.requireNonNull(supplier));
    }

    public long gaugeValue(String name) {
        LongSupplier supplier = gauges.get(requireName(name));
        return supplier == null ? 0L : supplier.getAsLong();
    }

    public void recordTimer(String name, long nanos) {
        if (nanos < 0) {
            throw new IllegalArgumentException("timer value cannot be negative");
        }
        timers.computeIfAbsent(requireName(name), ignored -> new ArrayList<>()).add(nanos);
    }

    public DistributionSnapshot timerSnapshot(String name) {
        List<Long> values = timers.getOrDefault(requireName(name), List.of());
        DoubleSummaryStatistics stats = values.stream().mapToDouble(Long::doubleValue).summaryStatistics();
        return new DistributionSnapshot(values.size(), (long) stats.getMin(), (long) stats.getMax(), stats.getAverage());
    }

    private static String requireName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("metric name is required");
        }
        return name.trim();
    }
}
