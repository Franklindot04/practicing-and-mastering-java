# Boundaries Communication And Discovery

Service boundaries shape APIs, data ownership, security, operations, and failure behavior.

## Service Boundaries

A good boundary groups behavior that changes together and owns a coherent data model.

Ask:

- Which team owns this boundary?
- Which data does it own?
- Which operations must be transactional?
- Which clients depend on it?
- Which failures should be isolated?

## API Boundaries

API boundaries define how callers interact with the system.

Patterns:

- Public API for external clients.
- Internal API for service-to-service calls.
- Admin API for operations.
- Event contract for asynchronous consumers.

APIs should hide internal persistence details.

## Synchronous Communication

Synchronous communication waits for a response.

```text
Client -> Order API -> Inventory API -> Order API -> Client
```

Good fit when:

- The caller needs an immediate answer.
- The dependency is part of the user-facing decision.
- Failure can be shown clearly.

Risks:

- Latency adds up.
- Downstream failures affect the caller.
- Retries can amplify load.

## Asynchronous Communication

Asynchronous communication decouples the caller from later work.

```text
Order API -> Event Boundary -> Notification Worker
```

Good fit when:

- Work can happen after the response.
- Burst buffering is useful.
- Consumers can process independently.

Risks:

- Duplicate handling.
- Ordering questions.
- Backlog monitoring.
- Harder user-facing status.

## API Gateway And Backend-For-Frontend

An API gateway provides a front door for routing, authentication handoff, rate limits, or cross-cutting concerns.

A backend-for-frontend shapes responses for a specific client experience, such as mobile or web.

Do not use either pattern just to add a box. Use them when they simplify client boundaries or centralize a real concern.

## Service Discovery Concepts

Service discovery helps callers find healthy service instances.

Conceptual flow:

```text
Service Instance -> Registry or Platform
Caller           -> Resolves healthy target
```

Discovery does not remove the need for timeouts, health signals, and compatibility.
