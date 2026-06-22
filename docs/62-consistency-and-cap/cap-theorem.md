# CAP Theorem

CAP theorem describes a tradeoff in distributed data systems when a network partition happens.

The three words are:

- Consistency: every read sees the most recent successful write, or the system returns an error.
- Availability: every request receives a non-error response, even if it may not contain the newest data.
- Partition tolerance: the system continues making a defined choice when network communication between nodes is broken or delayed.

## The Important Part

In a real distributed system, partitions can happen. When they do, the system must choose how to behave.

```text
Node A cannot talk to Node B

Option 1: reject some work to preserve consistency.
Option 2: accept work and risk stale or conflicting data.
```

CAP is most useful when it helps you ask better questions. It is less useful when used as a label like "this database is CP" without understanding the specific operation, failure mode, and configuration.

## Example

Imagine two copies of account data. If the network between them breaks:

- A strongly consistent design may reject a transfer until it can confirm the current balance.
- A highly available design may accept requests on both sides and reconcile later.

For money movement, rejecting work may be safer. For a product view counter, accepting approximate data may be fine.

## Beginner Warning

CAP does not mean you can pick any two forever and ignore the third. It means that during a partition, the system has to prefer consistency or availability for a specific behavior.

