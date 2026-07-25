# Java Code Reading Quiz

Read this code:

```java
public void handle(MessageEnvelope message) {
    emailClient.sendReceipt(message.payload());
    processedMessages.save(message.messageId());
    broker.ack(message);
}
```

Questions:

1. What duplicate side effect can occur if the process crashes after sending email but before saving the processed id?
2. What check should happen before sending the email?
3. Why should broker-specific `ack` calls usually stay outside domain logic?
4. What should be logged if sending fails?
5. How could a unique operation id improve the design?
