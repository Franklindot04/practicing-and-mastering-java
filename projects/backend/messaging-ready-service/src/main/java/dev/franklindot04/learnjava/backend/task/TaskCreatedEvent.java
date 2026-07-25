package dev.franklindot04.learnjava.backend.task;

import java.time.Instant;

public record TaskCreatedEvent(String taskId, String title, Instant occurredAt) {
}
