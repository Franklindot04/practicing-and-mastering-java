# Design Patterns And SOLID Quiz

## Multiple Choice

1. What is the main purpose of a design pattern?
   - A. To replace all tests
   - B. To name a common solution to a recurring design problem
   - C. To force inheritance everywhere
   - D. To make code longer

2. Which pattern creates one object while hiding construction details?
   - A. Factory
   - B. Observer
   - C. Iterator
   - D. Adapter

3. What does the Single Responsibility Principle suggest?
   - A. A class should have one clear reason to change
   - B. A class should do every task
   - C. A method should never return a value
   - D. A package should contain one file

4. What does dependency inversion encourage?
   - A. High-level code depending on abstractions
   - B. High-level code depending directly on every low-level detail
   - C. Removing interfaces from all code
   - D. Avoiding tests

## Short Answer

5. When can a design pattern make code worse?
6. Name one benefit of programming to an interface.
7. What is the Open/Closed Principle?
8. How can clean architecture ideas help testing?

## Code Reading

9. Which SOLID idea is hinted at by this constructor?

```java
class ReportService {
    private final ReportWriter writer;

    ReportService(ReportWriter writer) {
        this.writer = writer;
    }
}
```

10. What pattern might fit when several notification subscribers should react to one event?
