package dev.franklindot04.learnjava.backend.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(long id) {
        super("Task " + id + " was not found");
    }
}
