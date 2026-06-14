# Beginner Java Quiz Answer Key

## Java Basics Quiz

1. B. `javac HelloWorld.java` compiles the source file.
2. C. `public static void main(String[] args)` is the standard entry point.
3. B. `int` stores whole numbers.
4. A. `%` returns the remainder after division.
5. C. `>=` means greater than or equal to.
6. A. A `for` loop is a common choice when the number of repeats is known.
7. B. `int[] scores = new int[5];` creates an integer array with five slots.
8. C. `equals()` compares string text values.
9. Declaring creates the variable name and type. Assigning stores a value in that variable.
10. Error messages often explain what Java expected, what it found, and which symbol or syntax caused the problem.
11. One possible answer:

```java
int doubleNumber(int number) {
    return number * 2;
}
```

12. Methods make code easier to read, reuse, test, and debug.
13. It prints `6`.
14. The final value is `"Java is fun"`.
15. It prints `Not adult yet`.
16. The loop uses `<= scores.length`, so it tries to access `scores[3]`. The last valid index is `2`, so the condition should be `index < scores.length`.

## OOP Introduction Quiz

1. A. A class is a blueprint for creating objects.
2. B. An object is a specific instance created from a class.
3. C. The `new` keyword creates a new object.
4. B. A constructor initializes a new object.
5. B. Private fields hide object data and help the class control how that data changes.
6. C. `getName()` follows common Java getter naming.
7. A class describes what an object can store and do. An object is one real value created from that class.
8. A `deposit` method can check rules, such as rejecting negative deposits, before changing the balance.
9. One possible answer: a `name` field and a `study()` method.
10. `this` refers to the current object whose method or constructor is running.
11. It prints `Milo`.
12. Two `Book` objects are created. `third` points to the same object as `second`.
13. `value` is private, so code outside the `Counter` class cannot assign `counter.value = 5`.
14. It prints `12`.
