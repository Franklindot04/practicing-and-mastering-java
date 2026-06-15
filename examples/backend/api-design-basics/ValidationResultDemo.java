import java.util.ArrayList;
import java.util.List;

public class ValidationResultDemo {
    public static void main(String[] args) {
        CreateTaskRequest request = new CreateTaskRequest("", "URGENT");
        ValidationResult result = TaskValidator.validate(request);

        if (!result.valid()) {
            System.out.println("Request rejected:");
            result.errors().forEach(System.out::println);
        }
    }

    record CreateTaskRequest(String title, String priority) {
    }

    record ValidationResult(boolean valid, List<String> errors) {
        static ValidationResult ok() {
            return new ValidationResult(true, List.of());
        }

        static ValidationResult failed(List<String> errors) {
            return new ValidationResult(false, List.copyOf(errors));
        }
    }

    static class TaskValidator {
        static ValidationResult validate(CreateTaskRequest request) {
            List<String> errors = new ArrayList<>();

            if (request.title() == null || request.title().isBlank()) {
                errors.add("title is required");
            }

            if (!List.of("LOW", "MEDIUM", "HIGH").contains(request.priority())) {
                errors.add("priority must be LOW, MEDIUM, or HIGH");
            }

            return errors.isEmpty() ? ValidationResult.ok() : ValidationResult.failed(errors);
        }
    }
}
