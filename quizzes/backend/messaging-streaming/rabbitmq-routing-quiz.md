# RabbitMQ Routing Quiz

Design goal:

```text
task.created -> audit, email
task.closed  -> audit
task.failed  -> audit, email, incident
```

Questions:

1. Which exchange type fits pattern-based routing?
2. What binding could the audit queue use?
3. What bindings could the email queue use?
4. What binding should the incident queue use?
5. Why is a fanout exchange less precise for this design?
6. What should happen after repeated consumer failure?
