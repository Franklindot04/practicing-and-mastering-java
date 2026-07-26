# Common Beginner Mistakes

System design is learned through practice and review. These mistakes are common because they feel productive at first.

## Starting With Tools

Tool-first design:

```text
We need Kafka, Redis, Kubernetes, and microservices.
```

Requirement-first design:

```text
We need users to submit orders safely, avoid duplicate charges, and view order status.
```

Tools should appear after the problem requires them.

## Ignoring Non-Functional Requirements

A design that only handles the happy path is incomplete. Latency, availability, durability, security, observability, and cost can change the design.

## Treating Estimates As Facts

Capacity estimates are starting assumptions. Real traffic should be measured once the system exists.

## Making Everything A Microservice

Microservices can help when teams, domains, and scaling needs justify them. They also add distributed failure, deployment coordination, observability work, and data consistency challenges.

## Adding Caches Without Invalidation Plans

Caching can reduce load, but stale data can become a correctness problem. Always ask what invalidates the cache and what stale result is acceptable.

## Retrying Without Idempotency

Retries can turn a temporary error into duplicate writes. Write paths that clients or workers retry should define idempotency behavior.

## Hiding External Dependency Failure

An external service can be slow, unavailable, or inconsistent. A design should state timeout, retry, fallback, and user-facing behavior.

## Drawing Diagrams Without Boundaries

Diagrams should show ownership. If everything is just boxes and arrows, reviewers cannot tell which team owns which part or where data crosses trust boundaries.

## Review Questions

- Did the design start with requirements or tools?
- Are assumptions labeled?
- Are read and write paths separate enough to reason about?
- Are external dependencies named?
- Are failure cases discussed?
- Are tradeoffs explicit?
- Is the design simpler than it first appeared?
- Is the design more complex than the requirements justify?
