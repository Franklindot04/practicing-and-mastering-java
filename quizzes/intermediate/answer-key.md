# Intermediate Java Quiz Answer Key

## Generics And Exceptions Quiz

1. B. `List<String>` prevents non-String values at compile time.
2. A. A generic method declares its type parameter before the return type.
3. C. `IOException` is checked.
4. B. A `catch` block should handle the problem or add useful context.
5. Generics let the compiler check types before the program runs.
6. Use `List<? extends Number>` when reading numbers from lists of different numeric types.
7. Throwing reports a problem. Catching handles a reported problem.
8. It prints `4`.
9. It prints `Invalid number`.

## Lambdas, Streams, And Optional Quiz

1. B. `filter` keeps items that match a condition.
2. A. `map` transforms each item.
3. B. `Optional.empty()` represents a missing value without `null`.
4. B. A terminal operation produces a final result.
5. Pipelines can show the sequence of filter, transform, and collect steps directly.
6. A loop may be clearer when logic has several branches or side effects.
7. Returning `Optional` communicates that a value may be absent. Parameters are usually clearer as normal values.
8. It prints `[Franklin, Grace]`.
9. It prints `missing@example.com`.

## Files, Date/Time, Maven, And Testing Quiz

1. A. `Path` represents a file path.
2. B. `LocalDate` represents a date without time.
3. A. Maven uses `pom.xml` for project configuration and dependencies.
4. C. `@Test` marks a JUnit 5 test method.
5. File operations can fail because files may be missing, locked, unreadable, or unavailable.
6. `LocalDate` supports validation, comparison, and date calculations.
7. It checks whether the expected and actual values are equal.
8. It prints `2026-06-17`.
9. It verifies that adding 3 and 4 returns 7.
