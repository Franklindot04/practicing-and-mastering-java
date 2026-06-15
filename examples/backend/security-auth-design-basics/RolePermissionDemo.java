import java.util.Set;

public class RolePermissionDemo {
    public static void main(String[] args) {
        User admin = new User("admin@example.com", Role.ADMIN);
        User learner = new User("learner@example.com", Role.USER);

        System.out.println(canAccessAdminReport(admin));
        System.out.println(canAccessAdminReport(learner));
    }

    enum Permission {
        TASK_READ,
        TASK_WRITE,
        ADMIN_READ
    }

    enum Role {
        USER(Set.of(Permission.TASK_READ, Permission.TASK_WRITE)),
        ADMIN(Set.of(Permission.TASK_READ, Permission.TASK_WRITE, Permission.ADMIN_READ));

        private final Set<Permission> permissions;

        Role(Set<Permission> permissions) {
            this.permissions = permissions;
        }

        boolean has(Permission permission) {
            return permissions.contains(permission);
        }
    }

    record User(String email, Role role) {
    }

    static boolean canAccessAdminReport(User user) {
        return user.role().has(Permission.ADMIN_READ);
    }
}
