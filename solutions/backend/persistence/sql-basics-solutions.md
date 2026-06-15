# SQL Basics Solutions

## Exercise 1

```sql
SELECT id, title
FROM tasks
WHERE completed = false
ORDER BY created_at DESC
LIMIT 10;
```

This filters incomplete rows, sorts newest first, and limits the result.

## Exercise 2

```sql
INSERT INTO tasks (title, completed)
VALUES ('Practice persistence', false);
```

If there is a `description` column:

```sql
INSERT INTO tasks (title, description, completed)
VALUES ('Practice persistence', 'Use SQL carefully', false);
```

## Exercise 3

```sql
UPDATE tasks
SET completed = true
WHERE id = 5;
```

```sql
DELETE FROM tasks
WHERE id = 8;
```

Without `WHERE`, these statements can affect every row.
