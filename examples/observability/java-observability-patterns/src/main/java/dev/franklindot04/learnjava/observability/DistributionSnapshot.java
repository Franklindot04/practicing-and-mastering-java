package dev.franklindot04.learnjava.observability;

public record DistributionSnapshot(long count, long min, long max, double average) {
    public DistributionSnapshot {
        if (count == 0) {
            min = 0;
            max = 0;
            average = 0.0;
        }
    }
}
