# Reflection And Annotation Examples

Reflection lets Java code inspect classes, fields, methods, and annotations at runtime. Annotations attach metadata to code.

Frameworks such as test runners, JSON mappers, validation libraries, and dependency injection containers use these ideas conceptually. These examples stay framework-free so the mechanics are visible.

## Examples

- `CustomAnnotationDemo.java`: creates and reads a custom annotation.
- `ClassInspectionDemo.java`: inspects fields and methods.
- `AnnotationValidationDemo.java`: validates fields marked with an annotation.

## Compile

```bash
javac examples/professional/reflection-annotations/*.java
```

## Run

```bash
java -cp examples/professional/reflection-annotations CustomAnnotationDemo
java -cp examples/professional/reflection-annotations AnnotationValidationDemo
```

## Common Mistakes

- Using reflection when normal method calls are clearer.
- Forgetting annotation retention rules.
- Ignoring access and security concerns.
- Making runtime behavior hard to trace and test.

Use reflection carefully because it trades compile-time clarity for runtime flexibility.
