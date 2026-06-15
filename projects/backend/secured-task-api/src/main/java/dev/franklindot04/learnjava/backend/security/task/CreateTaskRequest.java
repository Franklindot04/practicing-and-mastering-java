package dev.franklindot04.learnjava.backend.security.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
        @NotBlank(message = "title is required")
        @Size(max = 80, message = "title must be 80 characters or fewer")
        String title,

        @Size(max = 200, message = "description must be 200 characters or fewer")
        String description
) {
}
