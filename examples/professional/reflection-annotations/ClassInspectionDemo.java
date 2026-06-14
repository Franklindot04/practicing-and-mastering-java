import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassInspectionDemo {
    public static void main(String[] args) {
        Class<Account> type = Account.class;

        for (Field field : type.getDeclaredFields()) {
            System.out.println("Field: " + field.getName());
        }

        for (Method method : type.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());
        }
    }
}

class Account {
    private final String owner;

    Account(String owner) {
        this.owner = owner;
    }

    String owner() {
        return owner;
    }
}
