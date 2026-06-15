import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RepositoryInterfaceDemo {
    public static void main(String[] args) {
        TaskRepository repository = new InMemoryTaskRepository();
        TaskEntity saved = repository.save(new TaskEntity(0, "Practice repositories", false));

        System.out.println(saved);
        System.out.println(repository.findById(saved.id()));
    }

    record TaskEntity(long id, String title, boolean completed) {
    }

    interface TaskRepository {
        TaskEntity save(TaskEntity task);

        Optional<TaskEntity> findById(long id);

        List<TaskEntity> findAll();
    }

    static class InMemoryTaskRepository implements TaskRepository {
        private final Map<Long, TaskEntity> tasks = new LinkedHashMap<>();
        private long nextId = 1;

        @Override
        public TaskEntity save(TaskEntity task) {
            long id = task.id() == 0 ? nextId++ : task.id();
            TaskEntity saved = new TaskEntity(id, task.title(), task.completed());
            tasks.put(id, saved);
            return saved;
        }

        @Override
        public Optional<TaskEntity> findById(long id) {
            return Optional.ofNullable(tasks.get(id));
        }

        @Override
        public List<TaskEntity> findAll() {
            return List.copyOf(tasks.values());
        }
    }
}
