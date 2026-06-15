# Spring Data Repositories

Spring Data JPA reduces repository boilerplate by generating common persistence operations from interfaces.

## Repository Interface

```java
interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
}
```

`JpaRepository<Task, Long>` means:

- The entity type is `Task`.
- The id type is `Long`.

Common inherited methods:

- `save(entity)`
- `findById(id)`
- `findAll()`
- `delete(entity)`
- `existsById(id)`

## Query Methods

Spring Data can create simple queries from method names.

```java
List<Task> findByTitleContainingIgnoreCase(String title);
```

Use these carefully. Very long method names are a sign that the query may need another approach later.

## Service Still Matters

Repositories should not replace services. A service should still:

- Validate input.
- Decide not-found behavior.
- Map entities to DTOs.
- Coordinate transaction boundaries.

## Common Beginner Mistakes

- Putting controller logic in repositories.
- Returning `Optional` all the way to API responses without deciding the error shape.
- Writing repository methods before understanding the use case.
- Treating generated methods as magic instead of learning the database operation they imply.
