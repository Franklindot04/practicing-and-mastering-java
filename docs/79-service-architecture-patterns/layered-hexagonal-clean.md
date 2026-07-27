# Layered Hexagonal And Clean Architecture

These patterns organize code inside a deployable unit. They can be used inside a monolith, modular monolith, or service.

## Layered Architecture

Layered architecture separates responsibilities by technical layer.

```text
Controller -> Service -> Repository -> Database
```

Benefits:

- Easy for beginners to understand.
- Common in Java and Spring Boot projects.
- Clear request path for simple CRUD applications.

Risks:

- Business rules can leak into controllers or repositories.
- Layers can become broad and generic.
- Domain boundaries may be hidden by technical structure.

## Hexagonal Architecture

Hexagonal architecture puts the application core behind ports and adapters.

```text
HTTP Adapter -> Input Port -> Application Core -> Output Port -> Persistence Adapter
```

Benefits:

- External systems stay behind interfaces.
- Core behavior can be tested without infrastructure.
- Replacing adapters is less invasive.

Risks:

- More interfaces than a small app may need.
- Can become ceremony if boundaries are not meaningful.

## Clean Architecture

Clean architecture emphasizes dependency direction. Business rules should not depend on frameworks or delivery mechanisms.

```text
Frameworks -> Adapters -> Use Cases -> Domain
```

Benefits:

- Protects core rules from framework churn.
- Encourages explicit use cases.
- Supports testable design.

Risks:

- Too many packages for simple learning projects.
- Abstract names can hide concrete behavior.

## Choosing Among Them

| Situation | Pattern That May Fit | Reason |
| --- | --- | --- |
| Simple CRUD learning app | Layered | Direct and familiar |
| External dependencies are likely to change | Hexagonal | Ports keep adapters replaceable |
| Complex domain rules | Clean or hexagonal | Core behavior deserves protection |
| Tiny script or demo | Simpler structure | Architecture should not bury the idea |
