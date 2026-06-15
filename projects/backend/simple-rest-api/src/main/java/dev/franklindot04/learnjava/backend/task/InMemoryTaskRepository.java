package dev.franklindot04.learnjava.backend.task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTaskRepository {
    private final Map<Long, Task> tasks = new LinkedHashMap<>();
    private long nextId = 1;

    public synchronized List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public synchronized Optional<Task> findById(long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public synchronized Task save(Task task) {
        long id = task.id() == 0 ? nextId++ : task.id();
        Task saved = new Task(id, task.title(), task.description(), task.completed());
        tasks.put(id, saved);
        return saved;
    }

    public synchronized boolean deleteById(long id) {
        return tasks.remove(id) != null;
    }
}
