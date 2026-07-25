# Events Vs Commands

Events and commands are easy to mix up because both can be represented as messages. The difference is intent.

## Events

An event says that something already happened.

```text
TaskCreated
OrderPaid
PasswordResetRequested
```

Events are facts. They are usually named in past tense.

Good event phrasing:

- `UserRegistered`
- `InvoiceSent`
- `TaskCompleted`

## Commands

A command asks something to do work.

```text
CreateTask
SendInvoice
ReserveInventory
```

Commands are instructions. They are usually named with an imperative verb.

Good command phrasing:

- `RegisterUser`
- `SendInvoiceEmail`
- `CompleteTask`

## Comparison

| Question | Event | Command |
| --- | --- | --- |
| Meaning | Something happened | Please do something |
| Time | Past | Present or future |
| Sender expectation | Other parts may react | A specific handler should act |
| Typical name | `TaskCreated` | `CreateTask` |
| Coupling | Often many possible consumers | Usually one intended handler |

## Learning Rule

If the message would still be true after every consumer ignores it, it is probably an event.

If the message only makes sense because someone must perform an action, it is probably a command.
