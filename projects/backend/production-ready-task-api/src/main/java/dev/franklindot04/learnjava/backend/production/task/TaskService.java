package dev.franklindot04.learnjava.backend.production.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public TaskResponse create(CreateTaskRequest request) {
        TaskEntity saved = repository.save(new TaskEntity(request.title(), request.description()));
        LOGGER.info("task.created taskId={}", saved.getId());
        return toResponse(saved);
    }

    @Transactional
    public TaskResponse update(Long id, UpdateTaskRequest request) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.update(request.title(), request.description(), request.completed());
        LOGGER.info("task.updated taskId={} completed={}", id, request.completed());
        return toResponse(task);
    }

    private TaskResponse toResponse(TaskEntity task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
    }
}
