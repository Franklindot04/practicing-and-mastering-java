import java.time.Instant;

public class SafeErrorResponseDemo {
    public static void main(String[] args) {
        ApiError error = ApiError.notFound("/api/tasks/99", "Task was not found.");
        System.out.println(error);
    }

    record ApiError(int status, String message, String path, String timestamp) {
        static ApiError notFound(String path, String message) {
            return new ApiError(404, message, path, Instant.now().toString());
        }
    }
}
