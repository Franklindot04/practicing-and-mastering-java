# Idempotency Exercises

## Exercise 1: Design An Idempotency Key

Difficulty: Intermediate

Concepts practiced: duplicate requests, idempotency keys, safe create operations.

Problem statement:
Design an idempotency-key approach for `POST /tasks`. Explain what the API stores for the first request, what happens on an exact retry, and what happens if the same key is reused with different input.

Hints:

- Store enough information to return the same result.
- Treat same key with different payload as suspicious.
- Decide how long keys should be retained.

Stretch challenge:
Add a short log format for duplicate request detection.

## Exercise 2: Identify Idempotent Operations

Difficulty: Beginner

Concepts practiced: idempotency classification.

Problem statement:
Classify these as naturally idempotent or not: delete task, set task completed, append comment, create task, replace task title, increment task priority.

Hints:

- Ask whether repeating the operation changes the final intended state.
- Replacement is usually safer than incrementing.
- Creation often needs a key to become idempotent.

Stretch challenge:
Rewrite one non-idempotent operation to make it safer.

