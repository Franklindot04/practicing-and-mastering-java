import java.util.ArrayList;
import java.util.List;

public class ValidationBeforeAuthenticationDemo {
    public static void main(String[] args) {
        LoginRequest request = new LoginRequest("", "short");
        System.out.println(validate(request));
    }

    record LoginRequest(String username, String password) {
    }

    static List<String> validate(LoginRequest request) {
        List<String> errors = new ArrayList<>();

        if (request.username() == null || request.username().isBlank()) {
            errors.add("username is required");
        }

        if (request.password() == null || request.password().length() < 8) {
            errors.add("password must be at least 8 characters");
        }

        return errors;
    }
}
