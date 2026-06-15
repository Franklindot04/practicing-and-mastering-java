# Backend Persistence Design Basics Examples

These framework-light examples prepare you for JPA and Spring Data without requiring a database or web server.

## Examples

- `EntityDtoMappingDemo.java`: maps an internal entity to a public response DTO.
- `RepositoryInterfaceDemo.java`: shows a repository interface and an in-memory implementation.
- `ServiceValidationBeforeSaveDemo.java`: validates request data before creating an entity.
- `NotFoundHandlingDemo.java`: turns a missing entity into a clear application error.
- `TransactionBoundaryConceptDemo.java`: simulates why multi-step persistence work needs a boundary.

## Compile

From the repository root:

```bash
javac examples/backend/persistence-design-basics/*.java
```

## Run

```bash
java -cp examples/backend/persistence-design-basics EntityDtoMappingDemo
java -cp examples/backend/persistence-design-basics TransactionBoundaryConceptDemo
```

## Why Persistence Code Is Layered

- Controllers handle HTTP.
- DTOs define API input and output.
- Services validate and coordinate work.
- Repositories hide storage details.
- Entities represent stored data.

DTOs matter because stored data and public API data often change for different reasons. These examples keep internals out of responses so the later JPA project feels less mysterious.
