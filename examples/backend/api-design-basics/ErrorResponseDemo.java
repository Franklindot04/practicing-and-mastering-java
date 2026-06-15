import java.time.Instant;

public class ErrorResponseDemo {
    public static void main(String[] args) {
        ErrorResponse error = new ErrorResponse(
                404,
                "Task was not found",
                "/tasks/99",
                Instant.parse("2026-01-01T10:00:00Z")
        );

        System.out.println(error);
    }

    record ErrorResponse(int status, String message, String path, Instant timestamp) {
    }
}
