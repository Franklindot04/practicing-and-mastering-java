package dev.franklindot04.learnjava.perflab;

import java.util.ArrayList;
import java.util.List;

public final class OptimizedEventProcessor {
    private final EventParser parser = new EventParser();
    private final BatchProcessor batchProcessor = new BatchProcessor();

    public ProcessingResult process(List<String> rawEvents) {
        List<Event> parsed = new ArrayList<>(rawEvents.size());
        for (String raw : rawEvents) {
            parsed.add(parser.parse(raw));
        }
        EventValidator validator = new EventValidator();
        EventAggregator aggregator = new EventAggregator();
        int[] rejected = {0};
        batchProcessor.process(parsed, 3, batch -> {
            for (Event event : batch) {
                if (validator.accept(event)) {
                    aggregator.add(event);
                } else {
                    rejected[0]++;
                }
            }
            return List.of();
        });
        return new ProcessingResult(aggregator.snapshotTotals(),
                new MetricsSnapshot(rawEvents.size(), aggregator.acceptedEvents(), rejected[0], rejected[0]));
    }
}
