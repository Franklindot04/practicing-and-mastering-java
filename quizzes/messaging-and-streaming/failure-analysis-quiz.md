# Messaging And Streaming Failure Analysis Quiz

## FA1. Duplicate After Crash

A consumer updates a projection and crashes before acknowledging. The same message is delivered again. Identify the risk and the required protection.

## FA2. Requeue Loop

A RabbitMQ consumer rejects malformed JSON with requeue enabled. Queue depth stays high and CPU spikes. Diagnose the failure and propose a repair.

## FA3. Kafka Rebalance

A Kafka consumer group rebalances while one consumer is processing a long batch. Some offsets are not committed. Explain what can happen and how to reduce risk.

## FA4. Producer Timeout

A producer times out waiting for broker confirmation and retries the send. Later, two messages with the same business fact appear. Explain why and how to design for it.

## FA5. Replay Incident

An operator replays retained payment events into the normal consumer and customers are charged twice. Identify the design failure and safer replay approach.

## FA6. Late Mobile Events

Mobile purchase events arrive ten minutes late after a five-minute revenue window was published. Explain possible late-event handling choices.
