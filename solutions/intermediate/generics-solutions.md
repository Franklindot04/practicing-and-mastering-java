# Generics Solutions

## Generic Box

Create `class Box<T>` with a private `T value`, a constructor, and `getValue`. The type is chosen when the object is created, such as `Box<String>` or `Box<Integer>`.

## First Item Helper

A generic method can be written as `<T> T first(List<T> values)`. Check for an empty list before calling `values.get(0)`.

## Number Summary

Use `List<? extends Number>` when the method only reads numbers. Loop over the values and add `number.doubleValue()` to a running total.
