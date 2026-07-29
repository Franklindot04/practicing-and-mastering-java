package dev.franklindot04.learnjava.performance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;

public final class PerformancePatterns {
    private PerformancePatterns() {
    }

    public static boolean repeatedLookup(List<String> ids, String target) {
        return ids.contains(target);
    }

    public static Set<String> buildIndex(List<String> ids) {
        return new HashSet<>(ids);
    }

    public static boolean indexedLookup(Set<String> index, String target) {
        return index.contains(target);
    }

    public static String joinWithBuilder(List<String> values) {
        StringBuilder builder = new StringBuilder(values.size() * 8);
        for (String value : values) {
            if (!builder.isEmpty()) {
                builder.append(',');
            }
            builder.append(value);
        }
        return builder.toString();
    }

    public static int boxedSum(List<Integer> values) {
        int total = 0;
        for (Integer value : values) {
            total += value;
        }
        return total;
    }

    public static int primitiveSum(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    public static List<String> defensiveCopy(List<String> values) {
        return List.copyOf(values);
    }

    public static List<List<String>> chunks(List<String> values, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        List<List<String>> chunks = new ArrayList<>();
        for (int start = 0; start < values.size(); start += size) {
            chunks.add(List.copyOf(values.subList(start, Math.min(start + size, values.size()))));
        }
        return chunks;
    }

    public static Map<String, BigDecimal> totalsByCustomer(List<OrderLine> lines) {
        Map<String, BigDecimal> totals = new HashMap<>();
        for (OrderLine line : lines) {
            totals.merge(line.customerId(), line.total(), BigDecimal::add);
        }
        return totals;
    }

    public static List<ParsedEvent> parseOnce(List<String> rawEvents) {
        List<ParsedEvent> parsed = new ArrayList<>(rawEvents.size());
        for (String rawEvent : rawEvents) {
            String[] parts = rawEvent.split(",", -1);
            parsed.add(new ParsedEvent(parts[0], LocalDate.parse(parts[1]), parts[2].toUpperCase(Locale.ROOT)));
        }
        return parsed;
    }

    public static long measureElapsedNanos(Runnable work) {
        Objects.requireNonNull(work, "work");
        long start = System.nanoTime();
        work.run();
        return System.nanoTime() - start;
    }

    public static List<String> expensiveFilter(List<String> values, String prefix) {
        List<String> matches = new ArrayList<>();
        for (String value : values) {
            if (value.startsWith(prefix)) {
                matches.add(value);
            }
        }
        return matches;
    }

    public static boolean anyStartsWith(List<String> values, String prefix) {
        for (String value : values) {
            if (value.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static final class Counter {
        private final LongAdder adder = new LongAdder();

        public void increment() {
            adder.increment();
        }

        public long sum() {
            return adder.sum();
        }
    }

    public record OrderLine(String customerId, BigDecimal price, int quantity) {
        public OrderLine {
            if (quantity < 0) {
                throw new IllegalArgumentException("quantity must not be negative");
            }
        }

        BigDecimal total() {
            return price.multiply(BigDecimal.valueOf(quantity));
        }
    }

    public record ParsedEvent(String id, LocalDate date, String type) {
    }
}
