package dev.franklindot04.learnjava.backend.persistence.task;

public record TaskResponse(Long id, String title, String description, boolean completed) {
}
