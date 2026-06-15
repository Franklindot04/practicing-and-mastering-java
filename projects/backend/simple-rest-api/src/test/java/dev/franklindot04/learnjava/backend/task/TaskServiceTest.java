package dev.franklindot04.learnjava.backend.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskServiceTest {
    @Test
    void createsTaskWithGeneratedId() {
        TaskService service = new TaskService(new InMemoryTaskRepository());

        TaskResponse created = service.create(new CreateTaskRequest(" Learn APIs ", " Start simple "));

        assertEquals(1, created.id());
        assertEquals("Learn APIs", created.title());
        assertEquals("Start simple", created.description());
        assertFalse(created.completed());
    }

    @Test
    void updatesExistingTask() {
        TaskService service = new TaskService(new InMemoryTaskRepository());
        TaskResponse created = service.create(new CreateTaskRequest("Draft", ""));

        TaskResponse updated = service.update(created.id(), new UpdateTaskRequest("Publish", "Done", true));

        assertEquals("Publish", updated.title());
        assertEquals("Done", updated.description());
        assertTrue(updated.completed());
    }

    @Test
    void throwsWhenTaskIsMissing() {
        TaskService service = new TaskService(new InMemoryTaskRepository());

        assertThrows(TaskNotFoundException.class, () -> service.findById(99));
    }
}
