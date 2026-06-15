import java.time.Instant;
import java.util.Map;

public class HealthCheckShapeDemo {
    public static void main(String[] args) {
        HealthResponse response = new HealthResponse(
                "UP",
                Instant.now().toString(),
                Map.of("database", "UP", "configuration", "UP")
        );

        System.out.println(response.toJsonLikeString());
    }

    record HealthResponse(String status, String checkedAt, Map<String, String> components) {
        String toJsonLikeString() {
            return "{status=%s, checkedAt=%s, components=%s}".formatted(status, checkedAt, components);
        }
    }
}
