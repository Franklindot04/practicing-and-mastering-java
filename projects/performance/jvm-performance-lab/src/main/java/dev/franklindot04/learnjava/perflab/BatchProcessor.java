package dev.franklindot04.learnjava.perflab;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class BatchProcessor {
    public <T, R> List<R> process(List<T> values, int batchSize, Function<List<T>, List<R>> processor) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("batchSize must be positive");
        }
        List<R> results = new ArrayList<>();
        for (int start = 0; start < values.size(); start += batchSize) {
            results.addAll(processor.apply(List.copyOf(values.subList(start, Math.min(start + batchSize, values.size())))));
        }
        return results;
    }
}
