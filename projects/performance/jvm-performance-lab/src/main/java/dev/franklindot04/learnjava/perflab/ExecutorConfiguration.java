package dev.franklindot04.learnjava.perflab;

public record ExecutorConfiguration(int threads, int queueCapacity) {
    public ExecutorConfiguration {
        if (threads <= 0 || queueCapacity <= 0) {
            throw new IllegalArgumentException("executor configuration must be positive");
        }
    }
}
