# Event-Driven Operations

Event-driven systems need operational habits because important work may happen after the original request finishes.

This section is conceptual and vendor neutral. It does not install monitoring tools, brokers, clusters, cloud services, or runnable infrastructure.

## Topics

- [Monitoring Event-Driven Systems](monitoring-event-driven-systems.md)
- [Event Tracing Concepts](event-tracing-concepts.md)
- [Debugging Asynchronous Flows](debugging-asynchronous-flows.md)
- [Correlation Identifiers](correlation-identifiers.md)
- [Event Replay Concepts](event-replay-concepts.md)
- [Poison Events](poison-events.md)
- [Common Operational Mistakes](common-operational-mistakes.md)
- [Failure Investigation Workflow](failure-investigation-workflow.md)

## Learning Goals

After this section, you should be able to:

- Explain why asynchronous work needs different monitoring than direct requests.
- Use correlation identifiers to follow one business action across boundaries.
- Describe event replay and poison events at a high level.
- Investigate a failed event flow without guessing blindly.

## Big Idea

In event-driven architecture, "the request returned successfully" does not always mean every related business reaction has finished.
