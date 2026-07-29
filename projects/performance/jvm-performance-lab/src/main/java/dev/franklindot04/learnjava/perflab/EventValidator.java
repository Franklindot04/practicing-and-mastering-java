package dev.franklindot04.learnjava.perflab;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public final class EventValidator {
    private final Set<String> seenIds = new HashSet<>();

    public boolean accept(Event event) {
        if (event.id().isBlank() || event.customerId().isBlank() || event.amount().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        return seenIds.add(event.id());
    }
}
