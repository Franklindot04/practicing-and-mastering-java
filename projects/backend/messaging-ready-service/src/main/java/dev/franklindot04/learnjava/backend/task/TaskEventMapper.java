package dev.franklindot04.learnjava.backend.task;

import dev.franklindot04.learnjava.backend.messaging.CorrelationId;
import dev.franklindot04.learnjava.backend.messaging.MessageEnvelope;
import dev.franklindot04.learnjava.backend.messaging.MessageId;

public class TaskEventMapper {
    public MessageEnvelope toMessage(TaskCreatedEvent event, CorrelationId correlationId) {
        return new MessageEnvelope(
                new MessageId("task-created-" + event.taskId()),
                correlationId,
                event.occurredAt(),
                "TaskCreated",
                "{\"taskId\":\"" + event.taskId() + "\",\"title\":\"" + event.title() + "\"}");
    }
}
