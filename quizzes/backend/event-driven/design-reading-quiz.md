# Architecture Design-Reading Quiz

Review this conceptual flow:

```text
Task API
  |
  +--> Save task
  |
  v
TaskCreated
  |
  +--> Notification consumer
  |
  +--> Reporting consumer
  |
  +--> Audit consumer
```

## Questions

1. Which component is the producer?
2. Name three consumers.
3. Which part of the flow should fail if task validation fails?
4. Should notification failure usually undo task creation? Explain.
5. What metadata would help trace one request through all consumers?
6. What duplicate-handling risk exists in the notification consumer?
7. Is this closer to publish/subscribe or orchestration? Explain.
8. What metric would show that reporting is falling behind?
9. What event contract change could break consumers?
10. How could an outbox pattern change the save-and-publish portion of the flow?
