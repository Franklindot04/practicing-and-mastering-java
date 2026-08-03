# Events Commands Queries And Notifications

Clear message semantics are the foundation of event-driven architecture. Ambiguous names lead to confused ownership, unsafe retries, and consumers that guess what the producer intended.

## Facts And Intent

An event is a fact. It says something happened.

```java
sealed interface OrderMessage permits OrderSubmitted, ReserveInventory, OrderStatusQuery {}

record OrderSubmitted(String orderId, long totalCents) implements OrderMessage {}
record ReserveInventory(String orderId) implements OrderMessage {}
record OrderStatusQuery(String orderId) implements OrderMessage {}
```

`OrderSubmitted` is a fact. `ReserveInventory` is an intent. `OrderStatusQuery` asks for information.

## Events

Events should usually be named in the past tense:

- `OrderSubmitted`
- `InventoryReserved`
- `PaymentRejected`
- `NotificationRequested`

Past-tense names remind consumers that the event is immutable. The consumer may react, ignore it, or raise a compensating action, but it cannot make the original fact un-happen.

Business events describe business meaning. Technical events describe implementation activity. `PasswordResetRequested` is a business event. `EmailJobInserted` is a technical event. Both can be useful, but they should not be confused.

## Commands

A command asks a specific receiver to do something. It can be rejected.

```java
record AuthorizePayment(String orderId, long amountCents) {}
```

The receiver can answer `accepted`, `rejected`, `already processed`, or `invalid`. Commands are useful when one component owns a decision. They become dangerous when named like events, because consumers may treat an uncompleted intent as a completed fact.

## Queries

A query asks for data and expects a response:

```java
record FindOrderStatus(String orderId) {}
record OrderStatusView(String orderId, String status) {}
```

Queries should not change state. If a message both asks for data and causes side effects, retries and caching become much harder to reason about.

## Requests

A request is a broad interaction pattern. HTTP requests are common, but a request can also be a message. A request may contain a command, query, or other instruction. Always name the message by its business meaning, not only by transport shape.

## Notifications

A notification tells another component that something happened or needs attention. Some notifications are events with sparse payloads. Others are delivery tasks, such as `SendOrderConfirmationEmail`.

The naming should reveal whether the message is a fact or an instruction:

- `OrderConfirmed` means the order is already confirmed.
- `SendOrderConfirmationEmail` asks a notification component to do work.
- `OrderConfirmationEmailSent` records that the notification happened.

## Command-Style Events

Names like `CreateInvoiceEvent` or `ProcessPaymentEvent` are semantic warning signs. They sound like commands wrapped in event clothing. A consumer that receives `ProcessPaymentEvent` cannot tell whether payment was requested, authorized, captured, failed, or merely scheduled.

Prefer precise names:

- `InvoiceRequested`
- `InvoiceCreated`
- `PaymentAuthorizationRequested`
- `PaymentAuthorized`
- `PaymentRejected`

## Event Granularity

Coarse events reduce event volume but may hide important meaning. Fine-grained events improve precision but can overwhelm consumers and operators.

Use business boundaries to choose granularity. `OrderSubmitted` is usually better than `OrderUpdated` because it carries a clear fact. `LineItemQuantityChanged` may be useful inside an ordering domain but too detailed for an external integration event.

## Message Naming Checklist

- Is this a fact, intent, question, or delivery task?
- Does the tense match the meaning?
- Can a command be rejected?
- Does a query clearly return a response?
- Would a new consumer understand the event without asking the producer team?
- Does the name avoid generic words like `updated`, `changed`, or `processed` when a richer business word exists?
