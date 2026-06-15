# Keys, Relationships, And Transactions

Keys and transactions keep database-backed applications reliable.

## Primary Keys

A primary key uniquely identifies one row.

```sql
CREATE TABLE tasks (
  id BIGINT PRIMARY KEY,
  title VARCHAR(100) NOT NULL
);
```

Most beginner Spring projects let the database generate ids.

## Foreign Keys

A foreign key connects one table to another.

```sql
CREATE TABLE comments (
  id BIGINT PRIMARY KEY,
  task_id BIGINT NOT NULL,
  body VARCHAR(500) NOT NULL,
  FOREIGN KEY (task_id) REFERENCES tasks(id)
);
```

Here, each comment belongs to a task.

## Relationship Shapes

- One-to-many: one task has many comments.
- Many-to-one: many comments belong to one task.
- One-to-one: one row matches one row elsewhere.
- Many-to-many: many rows connect to many rows, usually through a join table.

## Transactions

A transaction groups database work so it succeeds or fails together.

Example: transferring money should not subtract from one account unless it also adds to the other.

Transaction basics:

- Commit: save all changes.
- Rollback: undo changes when something fails.
- Boundary: the part of code that should succeed or fail as one unit.

In Spring, service methods often become transaction boundaries when persistence logic gets more complex.

## Common Mistakes

- Forgetting primary keys.
- Updating or deleting without a `WHERE`.
- Storing related data as a comma-separated string instead of using relationships.
- Ignoring rollback when multiple writes must succeed together.
