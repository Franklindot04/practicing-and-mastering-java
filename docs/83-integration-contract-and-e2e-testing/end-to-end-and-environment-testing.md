# End-To-End And Environment Testing

End-to-end tests exercise user-visible behavior through a broad system boundary. They are expensive, so they should focus on the journeys that matter most.

## High-Value Journey Selection

Prefer end-to-end coverage for:

- A critical revenue or learning workflow.
- A path many users depend on.
- A path with several independently deployed parts.
- A smoke test that decides whether deeper validation should continue.
- A failure path that has caused escaped defects and cannot be checked lower down.

Avoid using end-to-end tests as the only place where every validation rule is checked.

## Environment Concerns

| Concern | Why it matters |
| --- | --- |
| Environment parity | Differences in configuration can hide or create failures. |
| Environment drift | Shared environments change over time without test authors noticing. |
| Ownership | Someone must know who fixes broken data, services, and credentials. |
| Data cleanup | Old test data can make later runs fail or pass incorrectly. |
| Sandbox services | Simulations should be documented so their limits are clear. |

## External Service Simulation

External service simulation and service virtualization can make tests safer and more repeatable. They are useful when live dependencies are slow, costly, unavailable, or unsafe for test data. The tradeoff is fidelity: a simulator can drift from the real service.

## End-To-End Scope

A practical end-to-end suite might contain:

- Main successful reservation journey.
- Login or authorization smoke path if relevant.
- One representative validation failure.
- One representative downstream failure.
- A post-release smoke check against a safe environment.

That is enough to provide broad workflow evidence without turning the suite into a duplicate of unit and component tests.

## Limitations

End-to-end tests can still miss defects because they sample a few paths, use prepared data, and run in limited environments. Passing end-to-end tests should increase confidence, not erase the need for design review, lower-level tests, observability, and release monitoring.

