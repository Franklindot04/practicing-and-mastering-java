package dev.franklindot04.learnjava.backend.persistence.task;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public TaskResponse create(CreateTaskRequest request) {
        TaskEntity saved = repository.save(new TaskEntity(clean(request.title()), clean(request.description()), false));
        return toResponse(saved);
    }

    @Transactional
    public TaskResponse update(Long id, UpdateTaskRequest request) {
        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        entity.update(clean(request.title()), clean(request.description()), request.completed());
        return toResponse(entity);
    }

    @Transactional
    public void delete(Long id) {
        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        repository.delete(entity);
    }

    private TaskResponse toResponse(TaskEntity entity) {
        return new TaskResponse(entity.getId(), entity.getTitle(), entity.getDescription(), entity.isCompleted());
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
