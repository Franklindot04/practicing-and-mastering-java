# Entity And Repository Solutions

## Exercise 1

Possible fields:

```text
ProductEntity: id, name, price, available, internalAuditNote
CreateProductRequest: name, price
ProductResponse: id, name, price, available
```

The server/database should assign the id. `internalAuditNote` should not appear in the public response.

## Exercise 2

```java
interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(long id);
    List<Product> findAll();
    void delete(Product product);
    List<Product> findByAvailable(boolean available);
}
```

The repository hides storage details. It should not decide whether a price is valid.

## Exercise 3

Possible flow:

```text
if name is blank, reject the request
if price is negative, reject the request
create ProductEntity from clean request data
save through repository
map saved entity to ProductResponse
return response
```

Invalid input can become a `400 Bad Request` response with field-level details.
