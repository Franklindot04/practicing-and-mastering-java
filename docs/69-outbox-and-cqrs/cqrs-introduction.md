# CQRS Introduction

CQRS means Command Query Responsibility Segregation.

The core idea is to separate the model used to change state from the model used to read state.

## Simple Mental Model

```text
Commands
  |
  v
Write model
  |
  v
State changes

Queries
  |
  v
Read model
  |
  v
View-friendly data
```

## Commands

Commands ask the system to change something.

Examples:

- Create a task.
- Complete a task.
- Register a user.

## Queries

Queries ask the system for information without changing state.

Examples:

- List open tasks.
- Show task details.
- Count completed tasks by day.

## Important Note

CQRS does not require multiple services, separate databases, event sourcing, or asynchronous messaging. It can be a simple design habit inside one application.
