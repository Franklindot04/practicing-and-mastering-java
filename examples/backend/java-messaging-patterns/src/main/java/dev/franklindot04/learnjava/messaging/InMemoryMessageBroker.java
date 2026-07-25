package dev.franklindot04.learnjava.messaging;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class InMemoryMessageBroker implements MessageProducer {
    private final Map<String, Queue<MessageEnvelope>> queues = new HashMap<>();
    private final Map<String, List<MessageConsumer>> topicSubscribers = new HashMap<>();

    @Override
    public void send(String destination, MessageEnvelope message) {
        queues.computeIfAbsent(destination, ignored -> new ArrayDeque<>()).add(message);
    }

    public void subscribe(String topic, MessageConsumer consumer) {
        topicSubscribers.computeIfAbsent(topic, ignored -> new ArrayList<>()).add(consumer);
    }

    public void publish(String topic, MessageEnvelope message) {
        for (MessageConsumer subscriber : topicSubscribers.getOrDefault(topic, List.of())) {
            subscriber.handle(message);
        }
    }

    public int drainQueue(String queueName, MessageConsumer consumer, RetryPolicy retryPolicy, DeadLetterCollector deadLetters) {
        Queue<MessageEnvelope> queue = queues.computeIfAbsent(queueName, ignored -> new ArrayDeque<>());
        int handled = 0;
        while (!queue.isEmpty()) {
            MessageEnvelope message = queue.remove();
            if (deliverWithRetry(message, consumer, retryPolicy, deadLetters)) {
                handled++;
            }
        }
        return handled;
    }

    public void simulateDuplicateDelivery(String queueName, MessageEnvelope message) {
        send(queueName, message);
        send(queueName, message);
    }

    private boolean deliverWithRetry(
            MessageEnvelope message,
            MessageConsumer consumer,
            RetryPolicy retryPolicy,
            DeadLetterCollector deadLetters
    ) {
        RuntimeException lastFailure = null;
        for (int attempt = 1; attempt <= retryPolicy.maxAttempts(); attempt++) {
            try {
                consumer.handle(message);
                return true;
            } catch (RuntimeException failure) {
                lastFailure = failure;
            }
        }
        String reason = lastFailure == null ? "unknown failure" : lastFailure.getMessage();
        deadLetters.collect(message, reason, retryPolicy.maxAttempts());
        return false;
    }
}
