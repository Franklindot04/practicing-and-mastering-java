package dev.franklindot04.learnjava.observability;

import java.util.List;

public record HealthReport(HealthStatus overallStatus, List<ComponentHealth> components) {
    public HealthReport {
        components = List.copyOf(components);
    }
}
