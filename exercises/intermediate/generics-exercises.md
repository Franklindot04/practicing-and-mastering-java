# Generics Exercises

## Exercise 1: Generic Box

Difficulty: Intermediate

Concepts practiced: generic classes, type parameters

Problem statement: create a `Box<T>` class that stores one value and returns it with `getValue`.

Example output:

```text
Java
42
```

Hints:

- Put `<T>` after the class name.
- Store the value in a private field.

Stretch challenge: add a `map` method that converts the value to another type.

## Exercise 2: First Item Helper

Difficulty: Intermediate

Concepts practiced: generic methods, lists

Problem statement: write a generic method that returns the first item in a `List<T>`.

Hints:

- Put `<T>` before the return type.
- Decide what should happen when the list is empty.

Stretch challenge: return `Optional<T>` instead of `null`.

## Exercise 3: Number Summary

Difficulty: Intermediate

Concepts practiced: bounded wildcards

Problem statement: write a method that accepts `List<? extends Number>` and returns the total as a `double`.

Hints:

- Use `number.doubleValue()`.
- The method should work with `Integer`, `Double`, and `BigDecimal` lists.

Stretch challenge: explain why adding values to `List<? extends Number>` is restricted.
