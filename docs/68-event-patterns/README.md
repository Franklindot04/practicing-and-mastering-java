# Event-Driven Patterns

Event-driven systems use recurring design patterns to describe how producers and consumers share facts and coordinate work.

This section is conceptual only. It does not use a specific broker, queue, stream, cloud service, or framework.

## Topics

- [Publish Subscribe](publish-subscribe.md)
- [Fan Out](fan-out.md)
- [Event Notification](event-notification.md)
- [Event-Carried State Transfer](event-carried-state-transfer.md)
- [Choreography](choreography.md)
- [Orchestration](orchestration.md)

## Learning Goals

After this section, you should be able to:

- Explain how publish/subscribe differs from a direct call.
- Describe fan-out without assuming every consumer does the same work.
- Compare lightweight notifications with events that carry useful state.
- Explain choreography and orchestration as workflow coordination styles.
- Choose a pattern based on coupling, visibility, and failure handling.

## Big Idea

Patterns give names to communication choices.

```text
One fact
  |
  +--> many independent reactions
```

The pattern is only helpful when it makes ownership and failure behavior clearer.
