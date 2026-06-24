# Distributed Systems Design Reading Quiz

Read the sketch and answer the questions.

```text
Client creates task
      |
      v
Task API writes task
      |
      +--> sends notification request
      |
      +--> updates reporting summary later
```

## Questions

1. Which part of the workflow should likely be read-after-write consistent for the user?

2. Which part might be eventually consistent?

3. If the notification request succeeds but the response is lost, what duplicate risk exists?

4. What idempotency design could reduce that duplicate risk?

5. If the reporting summary is delayed, what should the UI or operations view show?

6. What logs or trace fields would help investigate a failed notification?

7. If three API instances all try to run the reporting summary update, what coordination or idempotency concern appears?

8. Which failure should probably block task creation: notification failure or task data-store failure? Explain.

