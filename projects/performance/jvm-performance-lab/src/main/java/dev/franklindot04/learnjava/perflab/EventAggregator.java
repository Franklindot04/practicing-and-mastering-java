package dev.franklindot04.learnjava.perflab;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class EventAggregator {
    private final Map<String, BigDecimal> totals = new HashMap<>();
    private int acceptedEvents;

    public void add(Event event) {
        totals.merge(event.customerId(), event.amount(), BigDecimal::add);
        acceptedEvents++;
    }

    public Map<String, BigDecimal> snapshotTotals() {
        return Map.copyOf(totals);
    }

    public int acceptedEvents() {
        return acceptedEvents;
    }
}
