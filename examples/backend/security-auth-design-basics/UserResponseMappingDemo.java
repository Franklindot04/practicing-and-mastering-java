public class UserResponseMappingDemo {
    public static void main(String[] args) {
        UserEntity entity = new UserEntity(1, "learner@example.com", "$2a$demoHash", Role.USER);
        UserResponse response = toResponse(entity);

        System.out.println(response);
    }

    enum Role {
        USER,
        ADMIN
    }

    record UserEntity(long id, String email, String passwordHash, Role role) {
    }

    record UserResponse(long id, String email, Role role) {
    }

    static UserResponse toResponse(UserEntity entity) {
        return new UserResponse(entity.id(), entity.email(), entity.role());
    }
}
