# Exception Handling

Exceptions represent errors or unusual conditions in a program.

```java
try {
    int value = Integer.parseInt("42");
    System.out.println(value);
} catch (NumberFormatException ex) {
    System.out.println("Invalid number");
}
```

Why it matters: exception handling lets programs fail clearly, recover when appropriate, and avoid hiding bugs.

Common mistakes:

- Catching `Exception` too broadly.
- Swallowing errors with an empty `catch`.
- Using exceptions for normal control flow.
- Forgetting useful error messages.

Practice prompts:

- Handle invalid number input.
- Create a custom exception.
- Use `try-with-resources` for file reading.

Before moving on, you should understand checked exceptions, unchecked exceptions, `try`, `catch`, `finally`, and `throw`.
