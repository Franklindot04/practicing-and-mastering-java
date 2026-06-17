# Eventual Consistency Example

This example shows a task count that updates after the main write succeeds.

```text
User creates task
      |
      v
Task API writes task
      |
      v
Task list is immediately correct
      |
      v
Summary count updates later
```

## Why This Can Be Acceptable

The task itself must appear after creation. A dashboard count may be allowed to lag briefly if the interface makes that expectation reasonable.

## Risk

If the user sees "Task created" and then the task is missing from the task list, trust is damaged. That is different from a summary number catching up later.

## Review Questions

- Which view needs read-after-write consistency?
- Which view can be eventually consistent?
- How long can the lag last before it becomes a product problem?
- What monitoring would show delayed updates?

