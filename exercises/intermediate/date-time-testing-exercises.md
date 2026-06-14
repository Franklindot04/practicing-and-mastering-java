# Date, Time, Maven, And JUnit Exercises

## Exercise 1: Days Until Deadline

Difficulty: Intermediate

Concepts practiced: `LocalDate`, `ChronoUnit`

Problem statement: write a method that returns how many days remain between today and a deadline.

Hints:

- Use `LocalDate`.
- Use `ChronoUnit.DAYS.between`.

Stretch challenge: reject deadlines in the past.

## Exercise 2: Format A Meeting Time

Difficulty: Intermediate

Concepts practiced: `LocalDateTime`, formatting

Problem statement: format a meeting date and time into a readable string.

Example output:

```text
14 Jun 2026, 09:30
```

Hints:

- Use `DateTimeFormatter`.
- Keep parsing and formatting in separate methods.

Stretch challenge: add a time zone with `ZonedDateTime`.

## Exercise 3: Add JUnit Tests

Difficulty: Intermediate

Concepts practiced: Maven, JUnit 5, assertions

Problem statement: create tests for a small `GradeCalculator` method that returns `"Pass"` for scores 50 and above and `"Review"` otherwise.

Hints:

- Put tests under `src/test/java`.
- Use `@Test` and `assertEquals`.

Stretch challenge: add tests for invalid scores below 0 or above 100.
