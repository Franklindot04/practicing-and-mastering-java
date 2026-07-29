package dev.franklindot04.learnjava.observability;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class HealthCheckRunner {
    public HealthReport run(Map<String, Supplier<ComponentHealth>> checks) {
        List<ComponentHealth> components = checks.entrySet().stream()
                .map(entry -> safeRun(entry.getKey(), entry.getValue()))
                .toList();
        HealthStatus overall = components.stream()
                .map(ComponentHealth::status)
                .reduce(HealthStatus.UP, HealthStatus::worst);
        return new HealthReport(overall, components);
    }

    private ComponentHealth safeRun(String name, Supplier<ComponentHealth> check) {
        try {
            return check.get();
        } catch (RuntimeException ex) {
            return new ComponentHealth(name, HealthStatus.DOWN, ex.getClass().getSimpleName());
        }
    }
}
