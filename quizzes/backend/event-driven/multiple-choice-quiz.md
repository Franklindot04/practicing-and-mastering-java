# Multiple-Choice Quiz

## 1. Which message is most clearly an event?

A. `CreateTask`
B. `TaskCreated`
C. `SendEmail`
D. `UpdateTask`

## 2. What does asynchronous communication usually mean?

A. The sender waits for every consumer to finish.
B. The sender continues without waiting for every reaction to complete.
C. The system has no failures.
D. The event has no payload.

## 3. Who owns the meaning of an event?

A. The producer boundary.
B. Every consumer equally.
C. The database table.
D. The logging system.

## 4. What is a common risk of event-carried state transfer?

A. Payloads may expose too much contract data.
B. Consumers can never work independently.
C. Events cannot have names.
D. Producers must be consumers.

## 5. What problem does the outbox pattern help reduce?

A. Java syntax errors.
B. Dual-write inconsistency between state change and event publication.
C. Missing controller annotations.
D. Slow CSS rendering.

## 6. In CQRS, what is the write model mainly responsible for?

A. Display formatting.
B. Protecting business rules and accepting changes.
C. Styling API docs.
D. Replaying logs.

## 7. What is a poison event?

A. An event that repeatedly fails processing.
B. An event that has no timestamp.
C. A command with a good name.
D. A successful replay.

## 8. Why are correlation identifiers useful?

A. They replace event names.
B. They connect related work across boundaries.
C. They make events mutable.
D. They remove the need for monitoring.
