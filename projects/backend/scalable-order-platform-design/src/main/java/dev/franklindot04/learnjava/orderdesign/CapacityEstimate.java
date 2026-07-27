package dev.franklindot04.learnjava.orderdesign;

public record CapacityEstimate(int averageWritesPerSecond, int averageReadsPerSecond, int peakMultiplier) {
    public CapacityEstimate {
        if (averageWritesPerSecond < 0 || averageReadsPerSecond < 0 || peakMultiplier <= 0) {
            throw new IllegalArgumentException("capacity inputs must be non-negative and peakMultiplier positive");
        }
    }

    public int peakWritesPerSecond() {
        return averageWritesPerSecond * peakMultiplier;
    }

    public int peakReadsPerSecond() {
        return averageReadsPerSecond * peakMultiplier;
    }

    public boolean readHeavy() {
        return averageReadsPerSecond > averageWritesPerSecond;
    }
}
