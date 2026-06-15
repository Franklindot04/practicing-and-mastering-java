import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceValidationBeforeSaveDemo {
    public static void main(String[] args) {
        TaskService service = new TaskService(new TaskRepository());
        System.out.println(service.create(new CreateTaskRequest("  Validate first  ")));
    }

    record CreateTaskRequest(String title) {
    }

    record TaskEntity(long id, String title) {
    }

    record TaskResponse(long id, String title) {
    }

    static class TaskService {
        private final TaskRepository repository;

        TaskService(TaskRepository repository) {
            this.repository = repository;
        }

        TaskResponse create(CreateTaskRequest request) {
            if (request.title() == null || request.title().isBlank()) {
                throw new IllegalArgumentException("title is required");
            }

            TaskEntity saved = repository.save(new TaskEntity(0, request.title().trim()));
            return new TaskResponse(saved.id(), saved.title());
        }
    }

    static class TaskRepository {
        private final Map<Long, TaskEntity> tasks = new LinkedHashMap<>();
        private long nextId = 1;

        TaskEntity save(TaskEntity task) {
            TaskEntity saved = new TaskEntity(nextId++, task.title());
            tasks.put(saved.id(), saved);
            return saved;
        }
    }
}
