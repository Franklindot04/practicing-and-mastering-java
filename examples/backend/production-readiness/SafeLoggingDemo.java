import java.util.logging.Logger;

public class SafeLoggingDemo {
    private static final Logger LOGGER = Logger.getLogger(SafeLoggingDemo.class.getName());

    public static void main(String[] args) {
        LoginAttempt attempt = new LoginAttempt("learner@example.com", "demo-password");

        LOGGER.info(() -> "login.attempt email=" + maskEmail(attempt.email()));
        LOGGER.info("login.result status=REJECTED reason=demo-only");
    }

    private static String maskEmail(String email) {
        int at = email.indexOf('@');
        if (at <= 1) {
            return "***";
        }
        return email.charAt(0) + "***" + email.substring(at);
    }

    record LoginAttempt(String email, String password) {
    }
}
