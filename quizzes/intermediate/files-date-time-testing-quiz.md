# Files, Date/Time, Maven, And Testing Quiz

## Multiple Choice

1. Which class represents a file path in modern Java?
   - A. `Path`
   - B. `Route`
   - C. `Location`
   - D. `FileNameOnly`

2. Which type represents a date without a time of day?
   - A. `LocalTime`
   - B. `LocalDate`
   - C. `InstantMessage`
   - D. `CalendarDateOnly`

3. What does Maven use `pom.xml` for?
   - A. Project configuration and dependencies
   - B. Storing only source code
   - C. Recording terminal history
   - D. Formatting stack traces

4. Which JUnit 5 annotation marks a test method?
   - A. `@Check`
   - B. `@Run`
   - C. `@Test`
   - D. `@Assert`

## Short Answer

5. Why should file-reading code handle `IOException`?
6. Why is `LocalDate` better than a plain `String` for due dates?
7. What does `assertEquals(expected, actual)` check?

## Code Reading

8. What is printed?

```java
LocalDate start = LocalDate.of(2026, 6, 14);
LocalDate end = start.plusDays(3);
System.out.println(end);
```

9. What does this test verify?

```java
@Test
void addsTwoNumbers() {
    assertEquals(7, Calculator.add(3, 4));
}
```
