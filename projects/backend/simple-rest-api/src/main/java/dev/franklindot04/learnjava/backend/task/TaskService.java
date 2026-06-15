package dev.franklindot04.learnjava.backend.task;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final InMemoryTaskRepository repository;

    public TaskService(InMemoryTaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse findById(long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskResponse create(CreateTaskRequest request) {
        Task saved = repository.save(new Task(0, clean(request.title()), clean(request.description()), false));
        return toResponse(saved);
    }

    public TaskResponse update(long id, UpdateTaskRequest request) {
        findById(id);
        Task saved = repository.save(new Task(id, clean(request.title()), clean(request.description()), request.completed()));
        return toResponse(saved);
    }

    public void delete(long id) {
        if (!repository.deleteById(id)) {
            throw new TaskNotFoundException(id);
        }
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.id(), task.title(), task.description(), task.completed());
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
