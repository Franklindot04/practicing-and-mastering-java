# DTO, Service, And Validation Exercises

## Exercise 1: Create Request And Response DTOs

Difficulty: Beginner

Concepts practiced: DTO design, public API shape

Problem statement: Design `CreateProductRequest` and `ProductResponse` records for a product API. Include name, price, and availability in the response. Do not let clients choose the id when creating a product.

Hints:

- Request DTOs describe what clients send.
- Response DTOs describe what clients receive.

Stretch challenge: Add a separate `UpdateProductRequest`.

## Exercise 2: Write Service Rules

Difficulty: Beginner to Intermediate

Concepts practiced: Service-layer logic, validation boundaries

Problem statement: Write pseudocode for a `ProductService.create` method. It should reject blank names, reject prices below zero, save the product, and return a response DTO.

Hints:

- The service should make decisions.
- The repository should only save and find data.

Stretch challenge: Explain which parts could be unit tested without HTTP.

## Exercise 3: Plan Error Responses

Difficulty: Beginner

Concepts practiced: Validation errors, not-found errors, error response shape

Problem statement: Design one JSON error response for invalid input and one for a missing product id.

Hints:

- Include `status` and `message`.
- Add `details` when multiple fields are invalid.

Stretch challenge: Include a `path` field and explain why it helps.
