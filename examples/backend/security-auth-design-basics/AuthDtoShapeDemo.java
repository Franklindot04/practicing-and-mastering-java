public class AuthDtoShapeDemo {
    public static void main(String[] args) {
        LoginRequest request = new LoginRequest("learner@example.com", "demo-password");
        LoginResponse response = new LoginResponse("learner@example.com", "USER", "demo-session-id");

        System.out.println("Login request for " + request.username());
        System.out.println(response);
    }

    record LoginRequest(String username, String password) {
    }

    record LoginResponse(String username, String role, String demoSessionId) {
    }
}
