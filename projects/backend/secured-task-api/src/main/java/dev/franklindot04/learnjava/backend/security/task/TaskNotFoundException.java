package dev.franklindot04.learnjava.backend.security.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task " + id + " was not found");
    }
}
