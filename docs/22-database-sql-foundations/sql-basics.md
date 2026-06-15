# SQL Basics

SQL is the language used to read and change data in many relational databases.

## SELECT

Read rows from a table.

```sql
SELECT id, title, completed
FROM tasks;
```

## WHERE

Filter rows.

```sql
SELECT id, title
FROM tasks
WHERE completed = false;
```

## ORDER BY

Sort results.

```sql
SELECT id, title
FROM tasks
ORDER BY title ASC;
```

## LIMIT

Return only part of the result.

```sql
SELECT id, title
FROM tasks
ORDER BY id DESC
LIMIT 10;
```

## INSERT

Create a row.

```sql
INSERT INTO tasks (title, completed)
VALUES ('Practice SQL', false);
```

## UPDATE

Change existing rows.

```sql
UPDATE tasks
SET completed = true
WHERE id = 1;
```

Always include a careful `WHERE` unless you really mean to update every row.

## DELETE

Remove rows.

```sql
DELETE FROM tasks
WHERE id = 1;
```

Always include a careful `WHERE` unless you really mean to delete every row.

## Basic JOIN Idea

A join combines rows from related tables.

```sql
SELECT tasks.title, users.email
FROM tasks
JOIN users ON tasks.owner_id = users.id;
```

You do not need to master joins immediately, but you should know they connect related data.
