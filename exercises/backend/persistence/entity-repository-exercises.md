# Entity And Repository Exercises

## Exercise 1: Design An Entity And DTO

Difficulty: Beginner

Concepts practiced: Entity vs DTO, API boundaries

Problem statement: Design fields for a `ProductEntity`, `CreateProductRequest`, and `ProductResponse`. Include a field that belongs on the entity but should not appear in the response.

Hints:

- Entities represent stored data.
- Request DTOs describe client input.
- Response DTOs describe public output.

Stretch challenge: Explain who should assign the id.

## Exercise 2: Repository Interface

Difficulty: Beginner

Concepts practiced: Repository pattern, persistence boundaries

Problem statement: Sketch a `ProductRepository` interface with methods to save, find by id, list all, and delete a product.

Hints:

- Use `Optional` for find-by-id.
- Keep business validation out of the repository.

Stretch challenge: Add a method for finding products by availability.

## Exercise 3: Service Validation Before Save

Difficulty: Beginner to Intermediate

Concepts practiced: Validation, service-layer workflow

Problem statement: Write pseudocode for `ProductService.create`. It should reject blank names, reject negative prices, save the entity, and return a response DTO.

Hints:

- Validate before calling save.
- Map entity to DTO after save so generated ids are included.

Stretch challenge: Decide what error shape the API should return for invalid input.
