package dev.franklindot04.learnjava.backend.production.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
        @NotBlank(message = "Title is required.")
        @Size(max = 80, message = "Title must be 80 characters or fewer.")
        String title,

        @Size(max = 200, message = "Description must be 200 characters or fewer.")
        String description,

        boolean completed) {
}
