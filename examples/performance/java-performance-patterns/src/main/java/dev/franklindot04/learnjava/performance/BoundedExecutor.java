package dev.franklindot04.learnjava.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class BoundedExecutor implements AutoCloseable {
    private final ExecutorService executor;

    public BoundedExecutor(int threads) {
        if (threads <= 0) {
            throw new IllegalArgumentException("threads must be positive");
        }
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public <T> List<T> invokeAll(List<Callable<T>> tasks) throws Exception {
        List<Future<T>> futures = executor.invokeAll(tasks);
        List<T> results = new ArrayList<>(futures.size());
        for (Future<T> future : futures) {
            results.add(future.get());
        }
        return results;
    }

    public static int suggestedThreads(int cpuCores, double blockingFraction) {
        if (cpuCores <= 0 || blockingFraction < 0.0 || blockingFraction >= 1.0) {
            throw new IllegalArgumentException("invalid sizing inputs");
        }
        return Math.max(1, (int) Math.ceil(cpuCores / (1.0 - blockingFraction)));
    }

    @Override
    public void close() {
        executor.shutdown();
    }
}
