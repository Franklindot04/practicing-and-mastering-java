# Lambdas, Streams, And Optional Quiz

## Multiple Choice

1. What does `filter` do in a stream pipeline?
   - A. Converts each item to a new value
   - B. Keeps only items that match a condition
   - C. Sorts items in reverse order
   - D. Stops the JVM

2. What does `map` usually do?
   - A. Transforms each stream item
   - B. Removes every item
   - C. Catches exceptions
   - D. Opens a file

3. What does `Optional.empty()` represent?
   - A. A list with zero elements
   - B. A missing value without using `null`
   - C. A failed compilation
   - D. A stream that never ends

4. Which operation produces a final result from a stream?
   - A. Intermediate operation
   - B. Terminal operation
   - C. Package operation
   - D. Import operation

## Short Answer

5. Why can stream pipelines make collection processing easier to read?
6. Name one situation where a loop may be clearer than a stream.
7. Why should `Optional` usually be returned instead of accepted as a method parameter?

## Code Reading

8. What is printed?

```java
List<String> names = List.of("Ada", "Franklin", "Grace");
List<String> result = names.stream()
        .filter(name -> name.length() > 4)
        .toList();
System.out.println(result);
```

9. What is printed?

```java
Optional<String> email = Optional.empty();
System.out.println(email.orElse("missing@example.com"));
```
