# Modeling Query Patterns And Transactions

Schema design should follow the way the system writes and reads data. A beautiful model that cannot answer the required queries is incomplete.

## Schema Design

Relational-style order example:

```text
orders
  id
  customer_id
  status
  created_at

order_items
  order_id
  sku
  quantity
  price_snapshot
```

This separates order headers from line items and keeps item-level data queryable.

## Normalization

Normalization reduces duplication and keeps facts in one place.

Benefits:

- Fewer update anomalies.
- Clear relationships.
- Better integrity for shared facts.

Costs:

- More joins.
- More complex read queries.
- Potentially slower read paths at scale.

## Denormalization

Denormalization duplicates or reshapes data for reads.

Example:

```text
order_summary_view
  order_id
  customer_name_snapshot
  total
  status
  last_updated_at
```

Benefits:

- Faster reads.
- Simpler query shape.
- Good fit for read models.

Costs:

- Stale data risk.
- Synchronization complexity.
- More storage.

## Read Models And Write Models

Write models protect business rules. Read models answer queries efficiently.

```text
Command -> Write Model -> Durable State -> Read Model -> Query
```

This split can be useful even inside a modular monolith. It does not require separate services.

## Indexing Concepts

Indexes help reads find data faster, but they are not free.

Ask:

- Which query needs the index?
- Does the index match filter and sort patterns?
- How often does indexed data change?
- Does the index increase write cost?
- Is the query selective enough?

## Query Patterns

Document known query patterns:

- Lookup by ID.
- List by user.
- Search by status.
- Recent activity feed.
- Aggregate totals.
- Admin audit query.

Designing without query patterns often leads to accidental full scans.

## Transactions

Transactions group changes that must succeed or fail together.

Example:

```text
Create order
  |
  +-- insert order
  +-- insert order items
  +-- insert initial status history
```

If all three are part of one consistency boundary, a transaction may be appropriate.

## Isolation Concepts

Isolation describes how concurrent transactions see each other.

Beginner questions:

- Can two users update the same record at once?
- Can a reader see uncommitted data?
- Can a query result change during a transaction?
- Is optimistic locking needed?

Exact isolation levels depend on the chosen database, but the design should still name the concurrency problem.
