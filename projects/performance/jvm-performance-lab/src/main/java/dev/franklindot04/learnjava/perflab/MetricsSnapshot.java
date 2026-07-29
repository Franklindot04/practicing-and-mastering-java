package dev.franklindot04.learnjava.perflab;

public record MetricsSnapshot(int received, int accepted, int rejected, int duplicates) {
    public double acceptanceRate() {
        return received == 0 ? 0.0 : (double) accepted / received;
    }
}
