# Database And SQL Foundations

Persistence means data survives after the program stops. Backend APIs usually need persistence so users can create, read, update, and delete data over time.

## Why APIs Need Databases

- Store user-created data.
- Query data efficiently.
- Keep relationships between records.
- Protect changes with transactions.
- Support reports, searches, and history.

## Relational Database Basics

A relational database stores data in tables.

- Table: a collection of related records, such as `tasks`.
- Row: one record in a table.
- Column: one field on each row.
- Primary key: a unique identifier for a row.
- Foreign key: a value that points to a row in another table.

Example `tasks` table:

| id | title | completed |
| --- | --- | --- |
| 1 | Read SQL notes | false |
| 2 | Practice queries | true |

## Study Order

1. [SQL Basics](sql-basics.md)
2. [Keys, Relationships, And Transactions](keys-relationships-transactions.md)
3. [Backend Persistence Foundations](../23-backend-persistence-foundations/README.md)

## Safety Rule

Never commit real database credentials, passwords, tokens, private keys, or production connection strings. Use local sample settings only, and load real secrets from safe environment-specific configuration later.

Before moving on, you should be able to explain what a table, row, column, primary key, and foreign key are.
