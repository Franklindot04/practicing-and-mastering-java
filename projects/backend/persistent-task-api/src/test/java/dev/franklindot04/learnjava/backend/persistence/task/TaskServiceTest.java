package dev.franklindot04.learnjava.backend.persistence.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TaskService.class)
class TaskServiceTest {
    @Autowired
    private TaskService service;

    @Test
    void createsTaskWithDatabaseGeneratedId() {
        TaskResponse created = service.create(new CreateTaskRequest(" Learn JPA ", " Use H2 "));

        assertTrue(created.id() > 0);
        assertEquals("Learn JPA", created.title());
        assertEquals("Use H2", created.description());
        assertFalse(created.completed());
    }

    @Test
    void updatesExistingTask() {
        TaskResponse created = service.create(new CreateTaskRequest("Draft", ""));

        TaskResponse updated = service.update(created.id(), new UpdateTaskRequest("Published", "Done", true));

        assertEquals("Published", updated.title());
        assertEquals("Done", updated.description());
        assertTrue(updated.completed());
    }

    @Test
    void throwsWhenTaskDoesNotExist() {
        assertThrows(TaskNotFoundException.class, () -> service.findById(999L));
    }
}
