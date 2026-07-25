# Health Signals And Metrics

Messaging health is not one number. It is a picture of producers, broker paths, consumers, and side effects.

## Producer Signals

Track:

- Publish attempts
- Publish successes
- Publish failures
- Publish latency
- Serialization failures
- Message size problems

## Consumer Signals

Track:

- Messages received
- Messages processed successfully
- Processing failures
- Processing latency
- End-to-end latency from message timestamp to completion
- Retry counts
- Duplicate processing indicators

## Broker-Oriented Signals

Track:

- Queue depth or backlog
- Consumer lag concepts
- Dead-letter volume
- Redelivery count
- Oldest unprocessed message age

## Practical Dashboard Questions

1. Are producers publishing successfully?
2. Are consumers keeping up?
3. Are failures increasing?
4. Are dead-letter messages accumulating?
5. Is end-to-end latency acceptable?
