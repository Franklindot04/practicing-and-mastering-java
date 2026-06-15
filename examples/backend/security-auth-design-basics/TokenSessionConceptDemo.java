import java.time.Instant;
import java.util.Map;

public class TokenSessionConceptDemo {
    public static void main(String[] args) {
        String sessionId = "demo-session-123";
        Map<String, String> serverSessions = Map.of(sessionId, "learner@example.com");

        DemoToken token = new DemoToken("learner@example.com", Instant.parse("2026-01-01T10:15:00Z"));

        System.out.println("Session lookup: " + serverSessions.get(sessionId));
        System.out.println("Token-like value: " + token);
    }

    record DemoToken(String subject, Instant expiresAt) {
    }
}
