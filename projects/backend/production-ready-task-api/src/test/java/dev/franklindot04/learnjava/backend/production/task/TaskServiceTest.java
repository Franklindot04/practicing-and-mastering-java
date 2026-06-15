package dev.franklindot04.learnjava.backend.production.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(TaskService.class)
@ActiveProfiles("test")
class TaskServiceTest {
    @Autowired
    private TaskService service;

    @Test
    void createsTaskWithDatabaseGeneratedId() {
        TaskResponse created = service.create(new CreateTaskRequest("Prepare runbook", "Document startup checks"));

        assertThat(created.id()).isPositive();
        assertThat(created.title()).isEqualTo("Prepare runbook");
        assertThat(created.completed()).isFalse();
    }

    @Test
    void updatesExistingTask() {
        TaskResponse created = service.create(new CreateTaskRequest("Check logs", "Review application logs"));

        TaskResponse updated = service.update(created.id(),
                new UpdateTaskRequest("Check health", "Review health endpoint", true));

        assertThat(updated.title()).isEqualTo("Check health");
        assertThat(updated.completed()).isTrue();
    }

    @Test
    void throwsWhenTaskDoesNotExist() {
        assertThatThrownBy(() -> service.update(999L,
                new UpdateTaskRequest("Missing", "No matching task", false)))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task 999 was not found.");
    }
}
