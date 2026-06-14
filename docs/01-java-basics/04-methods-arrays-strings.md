# Methods, Arrays, And Strings

## Methods

Methods group reusable behavior.

```java
public static int add(int a, int b) {
    return a + b;
}
```

Use methods when a task has a clear name and can be reused or tested.

## Arrays

Arrays store multiple values of the same type.

```java
int[] scores = {90, 85, 72};

for (int score : scores) {
    System.out.println(score);
}
```

## Strings

Strings represent text and have useful methods.

```java
String name = "Java";
System.out.println(name.length());
System.out.println(name.toUpperCase());
System.out.println(name.contains("av"));
```

## Why It Matters

Methods reduce repetition. Arrays let you work with groups of values. Strings are everywhere in user input, files, logs, and web applications.

## Common Mistakes

- Forgetting that array indexes start at 0.
- Accessing an index outside the array.
- Making one giant `main` method.
- Comparing strings with `==`.
- Forgetting strings are immutable.

## Practice Prompts

- Write a method that returns the larger of two numbers.
- Find the average of an array.
- Count how many names in an array start with `A`.
- Reverse a string manually.

## Before Moving On

You should understand method parameters, return values, array indexing, enhanced `for` loops, and common `String` methods.
