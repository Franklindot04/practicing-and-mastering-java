package dev.franklindot04.learnjava.systemdesign;

import java.util.List;

public record ArchitectureDecisionRecord(
        String title,
        String context,
        String decision,
        List<String> alternatives,
        List<String> consequences,
        String revisitTrigger) {
    public ArchitectureDecisionRecord {
        alternatives = List.copyOf(alternatives);
        consequences = List.copyOf(consequences);
    }
}
