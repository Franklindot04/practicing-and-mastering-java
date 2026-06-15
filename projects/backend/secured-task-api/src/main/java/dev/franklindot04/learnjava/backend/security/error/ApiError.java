package dev.franklindot04.learnjava.backend.security.error;

import java.time.Instant;
import java.util.List;

public record ApiError(int status, String message, String path, Instant timestamp, List<String> details) {
    public static ApiError of(int status, String message, String path) {
        return new ApiError(status, message, path, Instant.now(), List.of());
    }

    public static ApiError withDetails(int status, String message, String path, List<String> details) {
        return new ApiError(status, message, path, Instant.now(), List.copyOf(details));
    }
}
