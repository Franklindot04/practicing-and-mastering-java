package dev.franklindot04.learnjava.messaging;

public record DeadLetterMessage(MessageEnvelope message, String reason, int attempts) {
}
