package dev.franklindot04.learnjava.systemdesign;

import java.util.List;

public record HealthStatus(String component, boolean healthy, String detail) {
    public static HealthStatus aggregate(String component, List<HealthStatus> statuses) {
        boolean healthy = statuses.stream().allMatch(HealthStatus::healthy);
        String detail = statuses.stream()
                .filter(status -> !status.healthy())
                .map(status -> status.component() + ":" + status.detail())
                .findFirst()
                .orElse("all dependencies healthy");
        return new HealthStatus(component, healthy, detail);
    }
}
