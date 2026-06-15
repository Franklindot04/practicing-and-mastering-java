package dev.franklindot04.learnjava.backend.deployment.task;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    List<TaskResponse> findAll() {
        LOGGER.info("task.list.requested");
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        LOGGER.info("task.create.requested titleLength={}", request.title().length());
        return service.create(request);
    }

    @PatchMapping("/{id}")
    TaskResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {
        LOGGER.info("task.update.requested taskId={}", id);
        return service.update(id, request);
    }
}
