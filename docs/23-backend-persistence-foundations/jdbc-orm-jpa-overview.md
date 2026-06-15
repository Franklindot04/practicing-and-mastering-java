# JDBC, ORM, And JPA Overview

Java has several ways to work with databases. Learn the tradeoffs before using a framework.

## JDBC Recap

JDBC is Java's low-level database API.

With JDBC, code usually:

- Opens a connection.
- Prepares SQL.
- Sets parameters safely.
- Executes the statement.
- Maps result rows to Java objects.
- Closes resources.

JDBC is explicit and useful to understand, but repetitive in larger applications.

## ORM Concept

ORM means object-relational mapping. An ORM maps Java objects to database rows and tables.

Instead of writing every SQL statement manually, you describe entities and relationships. The ORM handles many common persistence operations.

## JPA

JPA is a Java specification for ORM. It defines standard annotations and APIs, but it is not the implementation itself.

## Hibernate

Hibernate is a popular JPA implementation. It does the actual work at runtime.

## Spring Data JPA

Spring Data JPA builds on JPA and reduces repository boilerplate. You write repository interfaces, and Spring provides common methods such as `save`, `findById`, and `findAll`.

## How They Connect

```text
Controller -> Service -> Spring Data Repository -> JPA -> Hibernate -> Database
```

## What To Remember

- JDBC helps you understand what is really happening.
- ORM reduces repetitive mapping code.
- JPA gives standard annotations and concepts.
- Hibernate implements JPA.
- Spring Data JPA simplifies repository code.
