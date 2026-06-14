# Databases And JDBC

JDBC lets Java programs connect to relational databases.

Key topics:

- JDBC drivers
- Connections
- Prepared statements
- Result sets
- Transactions
- Connection cleanup

Common mistakes:

- Building SQL with string concatenation.
- Forgetting to close resources.
- Committing real credentials.
- Ignoring transaction boundaries.

Practice prompts:

- Connect to a local database.
- Insert a record with `PreparedStatement`.
- Query records into simple objects.
- Wrap multiple changes in a transaction.

Before moving on, you should understand why placeholders in prepared statements are safer than string-built SQL.
