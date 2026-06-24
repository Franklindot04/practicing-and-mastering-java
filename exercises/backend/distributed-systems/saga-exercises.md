# Saga Exercises

## Exercise 1: Design A Task Import Saga

Difficulty: Intermediate

Concepts practiced: saga steps, compensation actions, workflow state.

Problem statement:
Design a saga for importing tasks from an uploaded file. Include at least four steps and a compensation action for each step that can leave partial state.

Hints:

- Compensation is a business action, not always an undo.
- Keep a durable import status.
- Decide which failures require manual review.

Stretch challenge:
Add statuses for `pending`, `processing`, `failed`, `compensating`, and `complete`.

## Exercise 2: Orchestration Or Choreography

Difficulty: Intermediate

Concepts practiced: saga coordination styles.

Problem statement:
Choose orchestration or choreography for the task import saga and explain your reasoning.

Hints:

- Orchestration centralizes workflow decisions.
- Choreography spreads decisions through events.
- Beginner systems are often easier to reason about with orchestration.

Stretch challenge:
Describe one observability risk in your chosen style.

