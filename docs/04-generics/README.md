# Generics

Generics let you write type-safe code that works with more than one type.

```java
List<String> names = new ArrayList<>();
names.add("Ada");
```

Why it matters: generics prevent many runtime casting errors and make APIs clearer.

Common mistakes:

- Using raw types like `List` instead of `List<String>`.
- Trying to use primitives directly, such as `List<int>`.
- Making generic methods harder than they need to be.

Practice prompts:

- Write a generic `Box<T>`.
- Write a method that prints any `List<T>`.
- Compare `List<?>` and `List<Object>`.

Before moving on, you should understand type parameters, generic classes, generic methods, and wildcards at a basic level.

## Next Practice

- [ ] Run a generics example from [Intermediate Java Examples](../../examples/intermediate/java/README.md).
- [ ] Complete intermediate generics exercises in [Intermediate Exercises](../../exercises/intermediate/).
- [ ] Check understanding with [Intermediate Quizzes](../../quizzes/intermediate/README.md).
