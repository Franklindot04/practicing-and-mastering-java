# DTO, Service, And Validation Solutions

## Exercise 1

Possible DTOs:

```java
record CreateProductRequest(String name, BigDecimal price) {}
record UpdateProductRequest(String name, BigDecimal price, boolean available) {}
record ProductResponse(long id, String name, BigDecimal price, boolean available) {}
```

The create request has no id because the server should assign it.

## Exercise 2

Possible service flow:

```text
if name is blank, reject the request
if price is below zero, reject the request
create a Product model with a generated/default state
save through ProductRepository
map the saved Product to ProductResponse
return the response
```

The validation and mapping can be unit tested without starting HTTP.

## Exercise 3

Invalid input:

```json
{
  "status": 400,
  "message": "Request validation failed",
  "path": "/products",
  "details": ["name is required", "price must be zero or greater"]
}
```

Missing product:

```json
{
  "status": 404,
  "message": "Product 15 was not found",
  "path": "/products/15"
}
```
