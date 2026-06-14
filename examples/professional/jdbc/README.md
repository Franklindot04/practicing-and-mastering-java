# Professional JDBC Examples

JDBC is Java's standard API for talking to relational databases. Real applications use external configuration for database URLs, usernames, and passwords.

These examples avoid real credentials and do not require a database server.

## Examples

- `JdbcConceptsDemo.java`: shows the basic JDBC types and safe configuration shape.
- `PreparedStatementShapeDemo.java`: demonstrates why parameterized SQL is preferred.

## Compile

```bash
javac examples/professional/jdbc/*.java
```

## Run

```bash
java -cp examples/professional/jdbc JdbcConceptsDemo
java -cp examples/professional/jdbc PreparedStatementShapeDemo
```

## Professional Warnings

- Never commit database credentials.
- Do not put passwords in source code.
- Prefer environment variables, secret managers, or deployment configuration.
- Use prepared statements instead of string-concatenated SQL.
- Close `Connection`, `PreparedStatement`, and `ResultSet` with try-with-resources.
