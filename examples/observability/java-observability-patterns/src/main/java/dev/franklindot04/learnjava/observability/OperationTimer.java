package dev.franklindot04.learnjava.observability;

public final class OperationTimer {
    private final long startedAtNanos;

    private OperationTimer(long startedAtNanos) {
        this.startedAtNanos = startedAtNanos;
    }

    public static OperationTimer start() {
        return new OperationTimer(System.nanoTime());
    }

    public long elapsedNanos() {
        return Math.max(0L, System.nanoTime() - startedAtNanos);
    }
}
