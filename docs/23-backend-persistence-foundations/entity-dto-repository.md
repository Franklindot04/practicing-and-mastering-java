# Entity, DTO, And Repository

Persistence-backed APIs need clear boundaries between stored data and public API data.

## Entity

An entity represents data stored by the application.

Example fields:

- `id`
- `title`
- `description`
- `completed`
- `createdAt`

Entities may include persistence annotations later, such as `@Entity` and `@Id`.

## DTO

A DTO describes data crossing the API boundary.

Examples:

- `CreateTaskRequest`
- `UpdateTaskRequest`
- `TaskResponse`
- `ErrorResponse`

DTOs help prevent accidental exposure of internal fields such as audit notes, database details, or future implementation fields.

## Repository Pattern

A repository hides how data is stored.

```java
interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(long id);
    List<Task> findAll();
    void delete(Task task);
}
```

The service can use this interface without caring whether the data is stored in memory, SQL, or another system.

## Service Boundary

Services should:

- Validate input before saving.
- Decide what happens when a row is missing.
- Map entities to response DTOs.
- Keep controller code small.

## Common Mistakes

- Returning entities directly from controllers.
- Putting SQL or repository details in controllers.
- Skipping validation before saving.
- Making request DTOs contain fields clients should not control.
