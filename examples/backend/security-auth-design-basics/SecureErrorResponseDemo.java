import java.time.Instant;

public class SecureErrorResponseDemo {
    public static void main(String[] args) {
        ApiError error = new ApiError(401, "Invalid credentials", "/api/auth/login", Instant.now());
        System.out.println(error);
    }

    record ApiError(int status, String message, String path, Instant timestamp) {
    }
}
