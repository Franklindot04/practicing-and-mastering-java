package dev.franklindot04.learnjava.perflab;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public final class BoundedEventBuffer {
    private final int capacity;
    private final Queue<Event> queue = new ArrayDeque<>();

    public BoundedEventBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
    }

    public boolean offer(Event event) {
        if (queue.size() == capacity) {
            return false;
        }
        return queue.offer(event);
    }

    public Optional<Event> poll() {
        return Optional.ofNullable(queue.poll());
    }

    public int size() {
        return queue.size();
    }
}
