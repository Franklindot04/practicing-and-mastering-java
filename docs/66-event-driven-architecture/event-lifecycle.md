# Event Lifecycle

An event has a lifecycle from the moment a business fact happens to the moment consumers finish reacting to it.

## Conceptual Steps

```text
Business action
  |
  v
State change accepted
  |
  v
Event created
  |
  v
Event made available
  |
  v
Consumer receives event
  |
  v
Consumer processes event
  |
  v
Outcome recorded
```

## Step 1: Business Action

Something starts the flow: a user request, a scheduled process, an import, or another internal operation.

## Step 2: State Change Accepted

The system validates the action and decides the fact is real. For example, a task is created only after the title is valid and the task is saved.

## Step 3: Event Created

The event captures the fact with a name, timestamp, identifier, and payload.

## Step 4: Event Made Available

The event becomes available for interested consumers. This may happen immediately or through a durable handoff pattern in more advanced designs.

## Step 5: Consumer Receives Event

Each consumer reads the event independently. Some may react quickly. Others may be delayed or unavailable.

## Step 6: Consumer Processes Event

The consumer performs its local work, such as updating a read model, writing an audit entry, or preparing a notification.

## Step 7: Outcome Recorded

The consumer should make success or failure observable. Without this step, event-driven systems become hard to operate.
