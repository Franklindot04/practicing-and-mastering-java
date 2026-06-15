package dev.franklindot04.learnjava.backend.security.task;

public record TaskResponse(Long id, String title, String description, boolean completed) {
}
