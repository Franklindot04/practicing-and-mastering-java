package dev.franklindot04.learnjava.perflab;

import java.util.List;

public final class NaiveEventProcessor {
    private final EventParser parser = new EventParser();

    public ProcessingResult process(List<String> rawEvents) {
        EventValidator validator = new EventValidator();
        EventAggregator aggregator = new EventAggregator();
        int rejected = 0;
        for (String raw : rawEvents) {
            Event event = parser.parse(raw);
            if (validator.accept(event)) {
                aggregator.add(event);
            } else {
                rejected++;
            }
        }
        return new ProcessingResult(aggregator.snapshotTotals(),
                new MetricsSnapshot(rawEvents.size(), aggregator.acceptedEvents(), rejected, rejected));
    }
}
