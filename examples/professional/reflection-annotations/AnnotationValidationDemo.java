import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationValidationDemo {
    public static void main(String[] args) throws IllegalAccessException {
        RegistrationForm form = new RegistrationForm("", "franklin@example.com");
        List<String> errors = SimpleValidator.validate(form);

        System.out.println(errors);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Required {
}

record RegistrationForm(@Required String username, @Required String email) {
}

class SimpleValidator {
    static List<String> validate(Object target) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();

        for (Field field : target.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Required.class)) {
                continue;
            }

            field.setAccessible(true);
            Object value = field.get(target);
            if (value == null || value.toString().isBlank()) {
                errors.add(field.getName() + " is required");
            }
        }

        return errors;
    }
}
