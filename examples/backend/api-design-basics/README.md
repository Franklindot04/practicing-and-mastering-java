# Backend API Design Basics Examples

These examples prepare you for Spring Boot without requiring a web server yet. They show the shapes that later become controllers, DTOs, services, repositories, validation results, and error responses.

## Examples

- `RequestResponseShapeDemo.java`: models simple request and response DTOs.
- `DtoMappingDemo.java`: maps between an internal model and public API DTOs.
- `ValidationResultDemo.java`: returns validation results without mixing rules into controller code.
- `ErrorResponseDemo.java`: creates consistent error response data.
- `ControllerServiceRepositoryDemo.java`: simulates a controller calling a service that uses a repository.

## Compile

From the repository root:

```bash
javac examples/backend/api-design-basics/*.java
```

## Run

```bash
java -cp examples/backend/api-design-basics RequestResponseShapeDemo
java -cp examples/backend/api-design-basics ControllerServiceRepositoryDemo
```

## How This Connects To Spring Boot

In Spring Boot, a controller method receives HTTP input and returns HTTP output. The same design ideas still apply:

- DTOs describe the public API shape.
- Services contain business rules.
- Repositories hide storage details.
- Validation protects the service from bad input.
- Error responses help clients understand failures.

Keep the controller thin. Put decisions where they can be tested without a running server.
