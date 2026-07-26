# Comparisons Failure Modes And Reviews

Architecture patterns are tools for reasoning. Use comparison to expose tradeoffs, not to crown one winner.

## Architecture Comparison

| Pattern | Strengths | Risks | Good Fit |
| --- | --- | --- | --- |
| Layered | Familiar, simple flow | Business rules can leak | Small APIs, CRUD learning |
| Hexagonal | Testable core, replaceable adapters | Interface ceremony | External dependencies matter |
| Clean architecture | Protected domain and use cases | Can over-abstract | Complex business rules |
| Modular monolith | Simple deployment, strong local consistency | Boundary discipline required | One team, evolving domain |
| Microservices | Independent ownership and scaling | Distributed failure and data complexity | Stable domains, multiple teams |

## Common Failure Modes

- Splitting services before the domain is understood.
- Sharing databases while pretending services are independent.
- Hiding network calls behind local-looking methods.
- Using async events without duplicate handling.
- Creating shared libraries that couple every deployment.
- Adding API gateways without clear responsibility.
- Using sagas without compensation or observability.
- Migrating by big-bang rewrite instead of incremental replacement.

## Tradeoff Analysis Prompt

For each proposed boundary, ask:

- What gets simpler?
- What gets harder?
- Which team owns it?
- Which data belongs to it?
- Which failure is isolated?
- Which new failure is introduced?
- What must be monitored?
- What rollback or migration path exists?

## Review Questions

- Is this architecture solving a stated requirement?
- Could a modular monolith satisfy the same need?
- Which service owns each business decision?
- Which database writes cross boundaries?
- Which calls are synchronous and why?
- Which workflows are asynchronous and how are duplicates handled?
- Where is an anti-corruption layer useful?
- What would trigger extracting a module later?
- What would trigger merging services or simplifying the design?
