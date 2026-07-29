package dev.franklindot04.learnjava.perflab;

import java.math.BigDecimal;
import java.time.Instant;

public final class EventParser {
    public Event parse(String raw) {
        String[] parts = raw.split("\\|", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("event must have 5 fields");
        }
        return new Event(parts[0], parts[1], parts[2], new BigDecimal(parts[3]), Instant.parse(parts[4]));
    }
}
