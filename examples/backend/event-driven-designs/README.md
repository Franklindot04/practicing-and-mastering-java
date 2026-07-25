# Event-Driven Design Examples

These examples show event-driven architecture concepts with simple backend scenarios.

They are Markdown-only design notes. They do not require a broker, queue, cloud service, database, or runnable messaging infrastructure.

## Examples

- [Order Created Flow](order-created-flow.md)
- [User Registration Flow](user-registration-flow.md)
- [Inventory Update Flow](inventory-update-flow.md)
- [Notification Flow](notification-flow.md)
- [Publish Subscribe Flow](publish-subscribe-flow.md)
- [Choreography Flow](choreography-flow.md)

## How To Use

For each example:

- Identify the event.
- Identify producers and consumers.
- Decide which failures should block the main action.
- Explain whether the flow is synchronous, asynchronous, or a mix.
