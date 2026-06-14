import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CustomAnnotationDemo {
    public static void main(String[] args) {
        Class<UserController> type = UserController.class;
        Route route = type.getAnnotation(Route.class);

        if (route != null) {
            System.out.println(type.getSimpleName() + " handles " + route.path());
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Route {
    String path();
}

@Route(path = "/users")
class UserController {
}
