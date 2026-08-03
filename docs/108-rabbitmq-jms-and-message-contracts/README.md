# RabbitMQ JMS And Message Contracts

Kafka is only one messaging model. Java teams also meet brokered queues, exchanges, routing keys, and the Jakarta Messaging API. This section explains RabbitMQ and JMS concepts while keeping message contracts explicit and portable.

## Recommended Reading Order

1. [RabbitMQ Exchanges Queues Routing And Acknowledgements](rabbitmq-exchanges-queues-routing-and-acknowledgements.md)
2. [RabbitMQ Retries Dead Letters And Publisher Confirms](rabbitmq-retries-dead-letters-and-publisher-confirms.md)
3. [Jakarta Messaging JMS Foundations](jakarta-messaging-jms-foundations.md)
4. [Message Contracts Serialization And Schema Evolution](message-contracts-serialization-and-schema-evolution.md)

## Learning Goals

After this section, you should be able to:

- model RabbitMQ exchanges, queues, bindings, routing keys, publishers, and consumers
- compare direct, topic, fanout, and headers exchanges
- explain manual acknowledgements, negative acknowledgements, requeueing, and prefetch
- distinguish publisher confirms from consumer acknowledgements
- design dead-letter exchanges, retry queues, TTLs, and poison-message handling
- explain Jakarta Messaging abstractions and portability limits
- design message envelopes, headers, payload contracts, and schema evolution rules
- identify malformed-message, serialization, and untrusted-payload risks

## Review Questions

1. Why can a persistent RabbitMQ message still fail to produce a business outcome?
2. Why are requeue loops dangerous?
3. What does JMS standardize, and what remains provider-specific?
4. Why does Kafka not directly implement JMS semantics?
5. Which schema changes are easiest for tolerant Java consumers to survive?
