# Reflection And Annotations Exercises

## Exercise 1: Runtime Annotation

Difficulty: Professional foundation

Concepts practiced: custom annotations, retention

Problem statement: create an annotation named `@Command` with a `name` value and read it from a class at runtime.

Hints:

- Use `@Retention(RetentionPolicy.RUNTIME)`.
- Call `getAnnotation`.

Stretch challenge: add a description field.

## Exercise 2: Inspect Public Methods

Difficulty: Professional foundation

Concepts practiced: reflection, method metadata

Problem statement: print all declared method names for a class.

Hints:

- Use `Class<?>`.
- Use `getDeclaredMethods`.

Stretch challenge: print parameter counts too.

## Exercise 3: Required Field Validator

Difficulty: Professional foundation

Concepts practiced: annotations, reflection, validation

Problem statement: mark fields with `@Required` and write a validator that reports blank or null values.

Hints:

- Use `getDeclaredFields`.
- Use `setAccessible(true)` only for this learning example.

Stretch challenge: support a custom error message in the annotation.
