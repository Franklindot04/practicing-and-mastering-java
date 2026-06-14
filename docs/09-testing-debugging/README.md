# Testing And Debugging Strategies

Tests protect behavior. Debugging finds why behavior differs from expectations.

## JUnit 5 Basics

```java
@Test
void addsTwoNumbers() {
    assertEquals(4, Calculator.add(2, 2));
}
```

## Debugging Strategy

1. Reproduce the issue.
2. Read the error message.
3. Isolate the smallest failing case.
4. Inspect values.
5. Change one thing at a time.
6. Add a test for the fix.

Common mistakes:

- Testing implementation details instead of behavior.
- Only testing happy paths.
- Ignoring edge cases.
- Changing many things at once while debugging.

Before moving on, you should be able to write a small unit test and explain a failure message.
