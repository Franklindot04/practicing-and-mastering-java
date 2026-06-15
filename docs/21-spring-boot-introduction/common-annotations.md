# Common Spring Boot Annotations

Annotations give Spring metadata about classes and methods. Start with the common ones before exploring advanced options.

## Application Annotation

`@SpringBootApplication`

Marks the main application class. It combines configuration, auto-configuration, and component scanning.

## Web Annotations

`@RestController`

Marks a class whose methods return response data, often JSON.

`@RequestMapping`

Defines a shared route prefix or broader request mapping.

`@GetMapping`

Handles HTTP `GET` requests.

`@PostMapping`

Handles HTTP `POST` requests.

You will also commonly see `@PutMapping`, `@PatchMapping`, and `@DeleteMapping`.

## Layer Annotations

`@Service`

Marks a class that contains business logic.

`@Repository`

Marks a class that talks to storage. Later, Spring can translate database-related exceptions for repositories.

## Injection Annotation

`@Autowired`

Tells Spring to inject a dependency. In modern Spring, constructor injection often does not need the annotation when there is one constructor.

Prefer this:

```java
@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
}
```

Avoid this in new code:

```java
@Autowired
private TaskRepository repository;
```

Constructor injection makes required dependencies clear and supports focused tests.

## Request Data Annotations

`@PathVariable`

Reads a value from the URL path, such as `/tasks/{id}`.

`@RequestParam`

Reads a query parameter, such as `/tasks?completed=false`.

`@RequestBody`

Reads JSON from the request body into a Java object.

## Common Mistakes

- Annotating every class instead of thinking about its responsibility.
- Returning internal entities directly from controllers.
- Using `@Autowired` fields because it seems shorter.
- Forgetting that annotations do not replace clear design.
