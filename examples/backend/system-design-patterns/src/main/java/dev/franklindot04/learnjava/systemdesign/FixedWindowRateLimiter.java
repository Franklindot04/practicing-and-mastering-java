package dev.franklindot04.learnjava.systemdesign;

import java.time.Clock;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class FixedWindowRateLimiter {
    private final int limit;
    private final Duration window;
    private final Clock clock;
    private final Map<String, Window> windows = new HashMap<>();

    public FixedWindowRateLimiter(int limit, Duration window, Clock clock) {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be positive");
        }
        this.limit = limit;
        this.window = window;
        this.clock = clock;
    }

    public boolean allow(String key) {
        long now = clock.millis();
        Window current = windows.get(key);
        if (current == null || now - current.startedAtMillis >= window.toMillis()) {
            windows.put(key, new Window(now, 1));
            return true;
        }
        if (current.count >= limit) {
            return false;
        }
        windows.put(key, new Window(current.startedAtMillis, current.count + 1));
        return true;
    }

    private record Window(long startedAtMillis, int count) {
    }
}
