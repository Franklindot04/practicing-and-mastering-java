import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControllerServiceRepositoryDemo {
    public static void main(String[] args) {
        TaskRepository repository = new InMemoryTaskRepository();
        TaskService service = new TaskService(repository);
        TaskController controller = new TaskController(service);

        System.out.println(controller.create(new CreateTaskRequest("Build a layered API", "HIGH")));
        System.out.println(controller.findAll());
        System.out.println(controller.findById(99));
    }

    record CreateTaskRequest(String title, String priority) {
    }

    record TaskResponse(long id, String title, String priority, boolean completed) {
    }

    record ApiResponse(int status, Object body) {
    }

    record ErrorResponse(int status, String message) {
    }

    record Task(long id, String title, String priority, boolean completed) {
    }

    interface TaskRepository {
        Task save(Task task);

        List<Task> findAll();

        Optional<Task> findById(long id);
    }

    static class InMemoryTaskRepository implements TaskRepository {
        private final Map<Long, Task> tasks = new LinkedHashMap<>();
        private long nextId = 1;

        @Override
        public Task save(Task task) {
            long id = task.id() == 0 ? nextId++ : task.id();
            Task saved = new Task(id, task.title(), task.priority(), task.completed());
            tasks.put(id, saved);
            return saved;
        }

        @Override
        public List<Task> findAll() {
            return List.copyOf(tasks.values());
        }

        @Override
        public Optional<Task> findById(long id) {
            return Optional.ofNullable(tasks.get(id));
        }
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

            Task saved = repository.save(new Task(0, request.title(), request.priority(), false));
            return toResponse(saved);
        }

        List<TaskResponse> findAll() {
            return repository.findAll().stream()
                    .map(this::toResponse)
                    .toList();
        }

        Optional<TaskResponse> findById(long id) {
            return repository.findById(id).map(this::toResponse);
        }

        private TaskResponse toResponse(Task task) {
            return new TaskResponse(task.id(), task.title(), task.priority(), task.completed());
        }
    }

    static class TaskController {
        private final TaskService service;

        TaskController(TaskService service) {
            this.service = service;
        }

        ApiResponse create(CreateTaskRequest request) {
            try {
                return new ApiResponse(201, service.create(request));
            } catch (IllegalArgumentException exception) {
                return new ApiResponse(400, new ErrorResponse(400, exception.getMessage()));
            }
        }

        ApiResponse findAll() {
            return new ApiResponse(200, service.findAll());
        }

        ApiResponse findById(long id) {
            return service.findById(id)
                    .<ApiResponse>map(task -> new ApiResponse(200, task))
                    .orElseGet(() -> new ApiResponse(404, new ErrorResponse(404, "task not found")));
        }
    }
}
