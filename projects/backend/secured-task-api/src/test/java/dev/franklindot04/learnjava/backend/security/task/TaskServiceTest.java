package dev.franklindot04.learnjava.backend.security.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskServiceTest {
    @Test
    void createsTaskWithGeneratedId() {
        TaskService service = new TaskService(new InMemoryTaskRepository());

        TaskResponse created = service.create(new CreateTaskRequest(" Secure API ", " Demo "));

        assertTrue(created.id() > 0);
        assertEquals("Secure API", created.title());
        assertEquals("Demo", created.description());
    }

    @Test
    void throwsWhenTaskIsMissing() {
        TaskService service = new TaskService(new InMemoryTaskRepository());

        assertThrows(TaskNotFoundException.class, () -> service.findById(99L));
    }

    private static class InMemoryTaskRepository extends TaskRepositoryAdapter {
    }
}
