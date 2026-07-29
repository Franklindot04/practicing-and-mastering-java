package dev.franklindot04.learnjava.perflab;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ConcurrentEventProcessor {
    private final ExecutorConfiguration configuration;

    public ConcurrentEventProcessor(ExecutorConfiguration configuration) {
        this.configuration = configuration;
    }

    public int processIds(List<String> ids) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(configuration.threads());
        try {
            java.util.concurrent.atomic.LongAdder counter = new java.util.concurrent.atomic.LongAdder();
            for (String ignored : ids) {
                executor.submit(counter::increment);
            }
            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
            return counter.intValue();
        } finally {
            executor.shutdownNow();
        }
    }
}
