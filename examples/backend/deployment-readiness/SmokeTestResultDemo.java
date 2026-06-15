import java.time.Instant;

public class SmokeTestResultDemo {
    public static void main(String[] args) {
        SmokeTestResult result = new SmokeTestResult("/actuator/health", 200, true, Instant.now().toString());
        System.out.println(result);
    }

    record SmokeTestResult(String endpoint, int statusCode, boolean passed, String checkedAt) {
    }
}
