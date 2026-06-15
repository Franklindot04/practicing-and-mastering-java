package dev.franklindot04.learnjava.backend.task;

public record TaskResponse(long id, String title, String description, boolean completed) {
}
