# Requirements And Constraints

Good system design starts with what the system must do and what conditions it must satisfy. A design that skips this step often solves an imagined problem.

## Functional Requirements

Functional requirements describe visible behavior.

Examples:

- A user can submit an order.
- A user can search order history.
- An administrator can issue a refund.
- The system sends a notification after payment succeeds.

Ask:

- Who performs the action?
- What input do they provide?
- What output do they expect?
- What state changes?
- What happens when the action fails?

## Non-Functional Requirements

Non-functional requirements describe qualities of the system.

Examples:

- Availability target.
- Latency target.
- Throughput expectation.
- Durability expectation.
- Security boundary.
- Auditability.
- Cost limit.
- Operational support model.

Avoid vague goals such as "fast" or "highly available" without context. A student project, an internal admin tool, and a payment system can all need different levels of reliability.

## Constraints

Constraints are limits the design must respect.

Examples:

- Existing Java and Spring Boot codebase.
- Team has strong SQL experience.
- Data must remain in a specific region.
- Budget is limited.
- Migration must happen without downtime.
- Existing clients cannot change immediately.

Constraints are not always bad. They help narrow the design space.

## Assumptions

Assumptions are guesses used to move the design forward.

Examples:

- Most traffic is read-heavy.
- Users retry failed submissions.
- Search does not need to be real time.
- Order history rarely changes after completion.

Label assumptions clearly. A design review should ask which assumptions require measurement or product confirmation.

## Users And Actors

Users are people or systems that interact with the backend.

```text
Customer ---> Order API
Admin    ---> Admin API
Payment  ---> Payment Callback Boundary
Worker   ---> Async Processing Boundary
```

Actors matter because different actors often need different permissions, latency expectations, and failure behavior.

## Requirements Checklist

- Who are the users and actors?
- What are the top three user workflows?
- What behavior is explicitly out of scope?
- What data is created or changed?
- What data must be protected?
- What latency or throughput target is known?
- What availability or recovery target is known?
- What constraints come from existing systems?
- Which assumptions are unverified?
- Which requirements conflict with each other?
