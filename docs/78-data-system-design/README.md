# Data System Design

Data design is the part of system design that decides what information the system owns, how it is shaped, how it changes, how it is read, and how it survives failure.

This section is provider-neutral. It does not include production database credentials, cloud provisioning, or deployment instructions.

## Topics

- [Data Requirements And Ownership](data-requirements-and-ownership.md)
- [Modeling Query Patterns And Transactions](modeling-query-patterns-and-transactions.md)
- [Consistency Replication And Partitioning](consistency-replication-and-partitioning.md)
- [Retention Recovery And Migration](retention-recovery-and-migration.md)
- [Data Design Mistakes And Review Questions](mistakes-and-review-questions.md)

## Learning Goals

After this section, you should be able to:

- Clarify data requirements before choosing storage technology.
- Compare relational and non-relational choices without absolutism.
- Explain normalization, denormalization, read models, and write models.
- Reason about transactions and consistency.
- Describe replication, partitioning, sharding, and hot partitions.
- Discuss retention, archival, backup, recovery, and migration strategy.
- Identify dual-write risks and privacy boundaries.

## Big Idea

Data design is not only table design. It is ownership, correctness, access patterns, lifecycle, and operational recovery.

```text
Requirement -> Data Owned -> Write Path -> Read Path -> Retention -> Recovery
```
