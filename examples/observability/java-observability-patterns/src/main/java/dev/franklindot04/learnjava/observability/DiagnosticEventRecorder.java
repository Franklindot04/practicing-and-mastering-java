package dev.franklindot04.learnjava.observability;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class DiagnosticEventRecorder {
    private final int capacity;
    private final ArrayDeque<StructuredEvent> events = new ArrayDeque<>();

    public DiagnosticEventRecorder(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
    }

    public synchronized void record(StructuredEvent event) {
        if (events.size() == capacity) {
            events.removeFirst();
        }
        events.addLast(event);
    }

    public synchronized List<StructuredEvent> snapshot() {
        return List.copyOf(new ArrayList<>(events));
    }
}
