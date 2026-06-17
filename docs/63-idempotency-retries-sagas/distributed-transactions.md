# Distributed Transactions

A local transaction protects changes inside one database. A distributed transaction tries to coordinate changes across multiple services or data stores.

## Why They Are Hard

Distributed transactions are hard because each participant can fail independently.

```text
Order Service creates order
Payment Service charges card
Inventory Service reserves stock

What if payment succeeds but inventory fails?
```

The system needs a decision for every incomplete state.

## Two-Phase Commit At A High Level

Two-phase commit is a coordination approach:

1. Ask each participant to prepare.
2. If all can prepare, tell all to commit.
3. If any cannot prepare, tell all to roll back.

This sounds clean, but it can block when the coordinator or participants fail. Many service-oriented systems avoid relying on it for user-facing workflows.

## Common Alternative

Instead of one global transaction, systems often use smaller local transactions plus recovery steps.

This is where sagas become useful.

## Beginner Rule

Do not assume a single database transaction can protect work spread across multiple services. Write down the failure cases first.

