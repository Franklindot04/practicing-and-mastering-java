package dev.franklindot04.learnjava.orderdesign;

import java.util.List;

public record ArchitectureDecisionRecord(
        String title,
        String decision,
        List<String> tradeoffs,
        String revisitTrigger) {
    public ArchitectureDecisionRecord {
        tradeoffs = List.copyOf(tradeoffs);
    }
}
