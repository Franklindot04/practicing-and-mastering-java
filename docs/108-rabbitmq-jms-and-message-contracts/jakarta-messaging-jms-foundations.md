# Jakarta Messaging JMS Foundations

## Purpose

Jakarta Messaging, commonly called JMS from its Java EE history, is a Java API for messaging providers. It gives applications common abstractions for queues, topics, messages, producers, consumers, transactions, selectors, and acknowledgement modes.

JMS is not a broker. A provider implements it. Provider behavior, tuning, clustering, durability, selectors, redelivery policy, and operational tooling still vary.

## Core Types

Important abstractions:

- `ConnectionFactory`: creates connections or simplified contexts
- `JMSContext`: simplified API for producing and consuming
- `Destination`: common parent concept for queues and topics
- `Queue`: point-to-point destination
- `Topic`: publish-subscribe destination
- `JMSProducer`: sends messages
- `JMSConsumer`: receives messages
- `Message`: base message type with headers and properties
- `TextMessage`: string payload
- `BytesMessage`: binary payload

Selectors filter messages using properties. Durable subscriptions allow a topic subscriber to receive messages while disconnected, according to provider rules and configuration.

## Acknowledgements And Transactions

Acknowledgement modes affect when consumed messages are considered handled. Common concepts include automatic acknowledgement, client acknowledgement, and transacted sessions or contexts. Local JMS transactions can group sends and receives inside the provider boundary, but they do not automatically include an external database or HTTP side effect.

## Portability Limits

JMS standardizes the Java programming model, not identical operational behavior across providers. Redelivery timing, dead-letter policy, clustering, message persistence, monitoring, and advanced delivery features are commonly provider-specific.

Kafka does not directly implement JMS semantics. Some bridges or adapters may present JMS-like APIs, but Kafka's log, partitions, offsets, consumer groups, and retention model remain different.

## Java Boundary Example

```java
record JmsSendPlan(String destinationName, boolean topic, int acknowledgementMode) {
}
```

Keep provider-specific connection factories and destination lookup in adapters. Keep business contracts independent of whether the provider is ActiveMQ, Artemis, IBM MQ, or another implementation.
