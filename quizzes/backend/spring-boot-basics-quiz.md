# Spring Boot Basics Quiz

## Multiple Choice

1. What does `@SpringBootApplication` mark?
   - A. A request body DTO
   - B. The main application class
   - C. A database table
   - D. A test assertion

2. Which annotation is commonly used for a class that exposes REST endpoints?
   - A. `@Repository`
   - B. `@Service`
   - C. `@RestController`
   - D. `@Autowired`

3. Which dependency is most likely needed for a basic REST API?
   - A. `spring-boot-starter-web`
   - B. `spring-boot-starter-security`
   - C. `spring-boot-starter-batch`
   - D. `spring-boot-starter-mail`

4. Which injection style is preferred for most beginner services?
   - A. Field injection
   - B. Constructor injection
   - C. Static global lookup
   - D. Creating dependencies with `new` inside every method

## Short Answer

5. What problem does Spring Boot auto-configuration try to solve?

6. Why should beginners avoid adding security and database complexity before the first REST resource works?

## Code Reading

7. In `public TaskController(TaskService service)`, what dependency is being injected and why is that useful for testing?
