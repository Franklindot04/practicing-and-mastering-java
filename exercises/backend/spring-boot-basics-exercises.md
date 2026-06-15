# Spring Boot Basics Exercises

## Exercise 1: Match Annotations To Responsibilities

Difficulty: Beginner

Concepts practiced: Spring Boot annotations, layers

Problem statement: Match each annotation to the layer or responsibility where it belongs: `@SpringBootApplication`, `@RestController`, `@GetMapping`, `@PostMapping`, `@Service`, `@Repository`, `@RequestBody`, `@PathVariable`.

Hints:

- Some annotations belong on classes.
- Some annotations belong on controller methods or parameters.

Stretch challenge: Explain why `@Service` should not be placed on a controller.

## Exercise 2: Sketch A Controller

Difficulty: Beginner to Intermediate

Concepts practiced: Controller/service separation, endpoint planning

Problem statement: Sketch a `BookController` with methods for `GET /api/books`, `GET /api/books/{id}`, and `POST /api/books`. Do not write repository code in the controller.

Hints:

- Inject a service through the constructor.
- Return DTOs from controller methods.

Stretch challenge: Add a `DELETE /api/books/{id}` method that returns no body.

## Exercise 3: Explain Constructor Injection

Difficulty: Beginner

Concepts practiced: Dependency injection, testability

Problem statement: In your own words, explain why constructor injection is preferred over field injection for beginner Spring Boot services.

Hints:

- Think about required dependencies.
- Think about creating a service in a unit test.

Stretch challenge: Show a small constructor-injected service class.
