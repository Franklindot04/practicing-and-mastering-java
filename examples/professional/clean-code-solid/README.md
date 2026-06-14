# Professional Clean Code And SOLID Examples

These examples show how small design choices make Java code easier to read, test, and change.

## Examples

- `CleanMethodExtractionDemo.java`: extracts small methods from a larger workflow.
- `MeaningfulNamingDemo.java`: uses names that explain intent.
- `LongMethodRefactoringDemo.java`: compares a cluttered method with smaller steps.
- `SingleResponsibilityDemo.java`: separates reporting from output.
- `OpenClosedDemo.java`: adds behavior through polymorphism.
- `LiskovSubstitutionDemo.java`: keeps subtypes safe to use through a common type.
- `InterfaceSegregationDemo.java`: avoids forcing classes to implement methods they do not need.
- `DependencyInversionDemo.java`: depends on an interface instead of a concrete detail.

## Compile

From the repository root:

```bash
javac examples/professional/clean-code-solid/*.java
```

## Run

```bash
java -cp examples/professional/clean-code-solid CleanMethodExtractionDemo
java -cp examples/professional/clean-code-solid DependencyInversionDemo
```

## Common Mistakes

- Treating short code as automatically clean.
- Using vague names such as `data`, `manager`, or `process`.
- Creating abstractions before a real change point exists.
- Applying SOLID mechanically instead of using it to reduce change risk.

Clean code matters because professional systems are read, debugged, tested, and changed many more times than they are first written.
