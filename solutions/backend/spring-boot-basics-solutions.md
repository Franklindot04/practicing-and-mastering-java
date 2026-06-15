# Spring Boot Basics Solutions

## Exercise 1

- `@SpringBootApplication`: main application class.
- `@RestController`: controller class that returns response bodies.
- `@GetMapping`: controller method for `GET`.
- `@PostMapping`: controller method for `POST`.
- `@Service`: service class with business logic.
- `@Repository`: storage boundary.
- `@RequestBody`: JSON request body parameter.
- `@PathVariable`: value from the URL path.

`@Service` does not belong on a controller because the controller handles HTTP while the service owns business behavior.

## Exercise 2

Possible sketch:

```java
@RestController
@RequestMapping("/api/books")
class BookController {
    private final BookService service;

    BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    List<BookResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    BookResponse findById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    ResponseEntity<BookResponse> create(@RequestBody CreateBookRequest request) {
        BookResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/books/" + created.id())).body(created);
    }
}
```

## Exercise 3

Constructor injection makes dependencies explicit and lets tests create the class directly.

```java
@Service
class BookService {
    private final BookRepository repository;

    BookService(BookRepository repository) {
        this.repository = repository;
    }
}
```
