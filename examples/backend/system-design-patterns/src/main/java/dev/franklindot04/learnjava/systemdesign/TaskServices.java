package dev.franklindot04.learnjava.systemdesign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class TaskServices {
    private TaskServices() {
    }

    public record Task(String id, String title, boolean completed) {
    }

    public interface TaskWriter {
        Task create(String id, String title);
    }

    public interface TaskReader {
        Optional<Task> findById(String id);

        List<Task> listOpen();
    }

    public static final class InMemoryTaskStore implements TaskWriter, TaskReader {
        private final Map<String, Task> tasks = new HashMap<>();

        @Override
        public Task create(String id, String title) {
            Task task = new Task(id, title, false);
            tasks.put(id, task);
            return task;
        }

        @Override
        public Optional<Task> findById(String id) {
            return Optional.ofNullable(tasks.get(id));
        }

        @Override
        public List<Task> listOpen() {
            return tasks.values().stream().filter(task -> !task.completed()).toList();
        }
    }
}
