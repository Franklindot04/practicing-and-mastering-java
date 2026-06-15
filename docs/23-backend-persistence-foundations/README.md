# Backend Persistence Foundations

Persistence code connects API behavior to stored data. Good persistence design keeps HTTP, business rules, and storage details separated.

## Core Ideas

- Entity: a Java object that represents stored data.
- DTO: a Java object that represents public request or response data.
- Repository: a boundary for saving and loading data.
- Service: validates input and coordinates repository calls.
- Transaction: a unit of persistence work that should succeed or fail together.

## Why Layering Matters

Controllers should not know database details. Services should not expose database entities directly to clients. Repositories should hide storage mechanics.

This separation makes the application easier to test and safer to change.

## Study Order

1. [Entity, DTO, And Repository](entity-dto-repository.md)
2. [JDBC, ORM, And JPA Overview](jdbc-orm-jpa-overview.md)
3. [JPA, Hibernate, And Spring Data](../24-jpa-hibernate-spring-data/README.md)

Before moving on, you should be able to explain why an API response DTO can be different from the database entity.
