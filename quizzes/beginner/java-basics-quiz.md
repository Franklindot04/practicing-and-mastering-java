# Beginner Java Basics Quiz

Use this quiz after studying setup, syntax, variables, operators, conditionals, loops, methods, arrays, strings, and debugging basics.

## Multiple Choice

1. Which command compiles a Java source file named `HelloWorld.java`?
   - A. `java HelloWorld.java`
   - B. `javac HelloWorld.java`
   - C. `compile HelloWorld`
   - D. `mvn HelloWorld.java`

2. Which method is the usual starting point for a simple Java program?
   - A. `public static void start(String[] args)`
   - B. `public void main()`
   - C. `public static void main(String[] args)`
   - D. `main public static void(String[] args)`

3. Which type is a good choice for storing a whole number like `42`?
   - A. `boolean`
   - B. `int`
   - C. `String`
   - D. `char`

4. What does the `%` operator return?
   - A. The remainder after division
   - B. The largest number in a calculation
   - C. The percentage symbol as text
   - D. The square root of a number

5. Which condition checks whether `score` is at least `70`?
   - A. `score = 70`
   - B. `score ==< 70`
   - C. `score >= 70`
   - D. `score => 70`

6. Which loop is often used when you know how many times code should repeat?
   - A. `for`
   - B. `try`
   - C. `switch`
   - D. `class`

7. Which array declaration creates space for five integers?
   - A. `int scores = new int[5];`
   - B. `int[] scores = new int[5];`
   - C. `scores int[] = 5;`
   - D. `int scores[] = int(5);`

8. Which method compares two strings by their text value?
   - A. `==`
   - B. `compareText()`
   - C. `equals()`
   - D. `sameAs()`

## Short Answer

9. What is the difference between declaring a variable and assigning a value to it?

10. Why should beginners read compiler error messages carefully instead of only looking at the line number?

11. Write a method named `doubleNumber` that accepts an `int` and returns twice its value.

12. Name one reason to use a method instead of putting all code inside `main`.

## Code Reading

13. What does this program print?

```java
int total = 0;

for (int number = 1; number <= 3; number++) {
    total = total + number;
}

System.out.println(total);
```

14. What is the final value of `message`?

```java
String message = "Java";
message = message + " is";
message = message + " fun";
```

15. What does this condition print when `age` is `16`?

```java
int age = 16;

if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Not adult yet");
}
```

16. Find the bug in this loop and explain the problem.

```java
int[] scores = {80, 90, 100};

for (int index = 0; index <= scores.length; index++) {
    System.out.println(scores[index]);
}
```
