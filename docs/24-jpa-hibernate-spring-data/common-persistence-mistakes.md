# Common Persistence Mistakes

Persistence bugs can be subtle because they involve code, data, and transactions.

## Returning Entities From Controllers

Entities may expose internal fields or trigger unexpected relationship loading. Return DTOs instead.

## Skipping Validation

Database constraints are useful, but API validation gives clients clearer errors before persistence fails.

## Committing Real Credentials

Never commit production database URLs, usernames, passwords, tokens, or private keys.

Good learning projects can use H2 in-memory settings. Real systems should load secrets from safe environment-specific configuration.

## Confusing JPA And Hibernate

JPA is the specification. Hibernate is an implementation. Spring Data JPA builds repository conveniences on top.

## Ignoring Transactions

When several writes belong to one workflow, decide where the transaction boundary belongs. Service methods are often the right place.

## Overusing Relationships

Do not add bidirectional relationships everywhere. Start with the relationship your use case actually needs.

## Forgetting DTO Mapping

Entity fields and API fields change for different reasons. Mapping between them keeps the public API stable.

## What To Practice First

- One entity.
- One repository.
- One service.
- Basic validation.
- CRUD endpoints.
- A small test using an in-memory database.
