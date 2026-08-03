package dev.franklindot04.learnjava.qualityperformance;

import java.util.List;

public final class QualityRules {
    public boolean weaklyAcceptsPositive(int available, int requested) { return available >= 0; }
    public boolean stronglyAcceptsReservation(int available, int requested) { return requested > 0 && available >= requested; }
    public QualityGateResult architecture(List<String> dependencies) {
        boolean forbidden = dependencies.stream().anyMatch(d -> d.contains("controller") && d.contains("repository"));
        boolean boundary = dependencies.stream().anyMatch(d -> d.equals("domain->port"));
        return new QualityGateResult(!forbidden && boundary, forbidden ? "controller depends on repository" : "boundary rule satisfied");
    }
    public record QualityGateResult(boolean passed, String message) {}
}
