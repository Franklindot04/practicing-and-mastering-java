# Backend Layering And Validation Quiz

## Multiple Choice

1. Which layer should usually contain business rules?
   - A. Controller
   - B. Service
   - C. README
   - D. HTTP header

2. What is a DTO mainly used for?
   - A. Describing public request or response data
   - B. Starting the JVM
   - C. Replacing all tests
   - D. Storing private keys

3. Which status code best fits invalid request data?
   - A. `201 Created`
   - B. `204 No Content`
   - C. `400 Bad Request`
   - D. `500 Internal Server Error`

4. Which test is usually fastest and simplest for service-layer rules?
   - A. Manual browser test only
   - B. Unit test with an in-memory dependency
   - C. Full production deployment
   - D. Screenshot comparison

## Short Answer

5. Why should controllers stay thin?

6. What fields might a useful error response contain?

7. Why should an API avoid returning internal model objects directly?

## Code Reading

8. Read this method sketch:

```java
TaskResponse create(CreateTaskRequest request) {
    if (request.title().isBlank()) {
        throw new IllegalArgumentException("title is required");
    }
    Task saved = repository.save(new Task(0, request.title(), false));
    return mapper.toResponse(saved);
}
```

Which layer should this method belong to, and what two responsibilities does it show?
