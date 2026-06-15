# Spring Profiles

Spring profiles let one application load different settings for different environments.

Common profile names:

- `local`: settings for running on a developer machine.
- `test`: settings for automated tests.
- `staging`: production-like review environment.
- `prod`: production environment.

In this repository, use `local` and `test` first. Do not add real production credentials or cloud deployment settings yet.

## Profile Files

Spring Boot commonly uses:

```text
application.properties
application-local.properties
application-test.properties
```

Base settings go in `application.properties`. Profile-specific settings go in the matching profile file.

Example:

```properties
# application.properties
spring.application.name=production-ready-task-api
```

```properties
# application-local.properties
spring.datasource.url=jdbc:h2:mem:tasks-local
```

```properties
# application-test.properties
spring.datasource.url=jdbc:h2:mem:tasks-test
```

## Activating A Profile

From the command line:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

For tests, prefer explicit test configuration:

```java
@ActiveProfiles("test")
```

## Profile Mistakes

- Committing real `prod` secrets.
- Making `local` and `test` behave so differently that tests stop being useful.
- Putting business logic decisions in profiles instead of code.
- Depending on the default profile without documenting what it does.
- Using a demo fallback in a production-like environment.
