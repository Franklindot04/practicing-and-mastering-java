package dev.franklindot04.learnjava.backend.production.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task %d was not found.".formatted(id));
    }
}
