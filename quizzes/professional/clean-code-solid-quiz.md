# Clean Code And SOLID Quiz

## Multiple Choice

1. What is a common sign that a method should be extracted?
   - A. It has a descriptive name
   - B. It mixes validation, calculation, and output
   - C. It returns a value
   - D. It has tests

2. What does SRP suggest?
   - A. A class should have one clear reason to change
   - B. A class should never have methods
   - C. Every class should implement three interfaces
   - D. All code should be static

3. Dependency inversion encourages services to depend on:
   - A. Concrete email clients only
   - B. Abstractions
   - C. Hidden global state
   - D. Database passwords

## Short Answer

4. Why do meaningful names matter in professional Java?
5. What is one risk of over-extracting tiny methods?
6. How can constructor injection make testing easier?

## Code Reading

7. Which SOLID principle is hinted at here?

```java
class ReportService {
    private final ReportWriter writer;

    ReportService(ReportWriter writer) {
        this.writer = writer;
    }
}
```
