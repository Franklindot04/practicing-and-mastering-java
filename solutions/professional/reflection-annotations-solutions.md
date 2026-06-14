# Reflection And Annotations Solutions

## Runtime Annotation

Use runtime retention or reflection will not see the annotation. Read it with `SomeClass.class.getAnnotation(Command.class)`.

## Inspect Public Methods

Use `getDeclaredMethods` for methods declared directly on the class. Use `getMethods` when inherited public methods matter too.

## Required Field Validator

Loop over fields, check `isAnnotationPresent`, read the value, and report null or blank values. Keep reflection isolated in one utility class.
