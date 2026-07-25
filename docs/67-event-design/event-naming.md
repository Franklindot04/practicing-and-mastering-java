# Event Naming

An event name should describe a fact that already happened.

## Use Past Tense

Good event names:

- `TaskCreated`
- `TaskCompleted`
- `UserRegistered`
- `OrderCancelled`

These names tell consumers that the source system already accepted the change.

## Avoid Command Names

Weak event names:

- `CreateTask`
- `SendNotification`
- `UpdateUser`

These sound like instructions. They do not clearly say what happened.

## Use Domain Language

Prefer names that match the business meaning.

```text
InvoicePaid
```

is clearer than:

```text
PaymentRowUpdated
```

The first describes a business fact. The second exposes storage details.

## Keep Names Stable

Changing an event name can break consumers. Choose names that can survive internal implementation changes.

## Naming Checklist

- Is the name in past tense?
- Does it describe a fact, not an instruction?
- Would a new reader understand the business meaning?
- Does it avoid database table, controller, or framework details?
- Could the name still make sense next year?
