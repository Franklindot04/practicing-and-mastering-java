# Spring Boot Core Concepts

Spring Boot removes a lot of manual setup, but the application is still plain Java at the center.

## Entry Point

A Spring Boot app starts from a class with `main`.

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

`@SpringBootApplication` marks the app configuration and tells Spring Boot where to start looking for components.

## Dependency Injection

Dependency injection means a class receives the objects it needs instead of creating them directly.

Prefer constructor injection:

```java
@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
}
```

This makes dependencies visible and easier to test.

## Auto-Configuration

Spring Boot looks at your dependencies and creates common infrastructure automatically.

If `spring-boot-starter-web` is present, Spring Boot can configure:

- Embedded web server
- JSON conversion
- Request routing
- Controller support

You can override defaults later, but beginners should first learn the default behavior.

## Configuration

Application settings usually live in files such as `application.properties` or `application.yml`.

Examples of settings:

- Server port
- Application name
- Logging level
- Database URL later

Do not commit secrets such as passwords, tokens, or private keys.

## Common Mistakes

- Putting all logic in controllers.
- Using field injection instead of constructor injection.
- Treating auto-configuration as magic instead of learning the default flow.
- Adding many dependencies before the app needs them.
- Starting with security, Docker, and databases before the first resource works.
