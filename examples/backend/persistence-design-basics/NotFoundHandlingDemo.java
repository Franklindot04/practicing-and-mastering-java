import java.util.Map;
import java.util.Optional;

public class NotFoundHandlingDemo {
    public static void main(String[] args) {
        TaskService service = new TaskService(new TaskRepository());

        try {
            service.findById(99);
        } catch (TaskNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    record TaskEntity(long id, String title) {
    }

    record TaskResponse(long id, String title) {
    }

    static class TaskNotFoundException extends RuntimeException {
        TaskNotFoundException(long id) {
            super("Task " + id + " was not found");
        }
    }

    static class TaskService {
        private final TaskRepository repository;

        TaskService(TaskRepository repository) {
            this.repository = repository;
        }

        TaskResponse findById(long id) {
            TaskEntity entity = repository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));
            return new TaskResponse(entity.id(), entity.title());
        }
    }

    static class TaskRepository {
        private final Map<Long, TaskEntity> tasks = Map.of();

        Optional<TaskEntity> findById(long id) {
            return Optional.ofNullable(tasks.get(id));
        }
    }
}
