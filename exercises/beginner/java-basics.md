# Java Basics Exercises

## Exercise 1: Personal Profile

Difficulty: Beginner

Concepts practiced: variables, strings, output

Problem statement: create variables for your name, age, favorite language, and weekly study goal. Print them in a readable profile.

Example output:

```text
Name: Franklin
Age: 25
Favorite language: Java
Weekly goal: 5 sessions
```

Hints:

- Use `String` for text.
- Use `int` for whole numbers.
- Use `System.out.println`.

Stretch challenge: print the total number of study sessions after four weeks.

## Exercise 2: Grade Message

Difficulty: Beginner

Concepts practiced: conditionals, comparison operators

Problem statement: given a score from 0 to 100, print a message:

- 90 and above: Excellent
- 70 to 89: Good progress
- 50 to 69: Needs review
- Below 50: Keep practicing

Example input:

```text
82
```

Example output:

```text
Good progress
```

Hints:

- Start with the highest score range.
- Use `else if`.

Stretch challenge: reject scores below 0 or above 100.

## Exercise 3: Multiplication Table

Difficulty: Beginner

Concepts practiced: loops, arithmetic, output

Problem statement: print the multiplication table for a number from 1 to 12.

Example input:

```text
5
```

Example output:

```text
5 x 1 = 5
5 x 2 = 10
...
5 x 12 = 60
```

Hints:

- Use a `for` loop.
- The loop counter should start at 1.

Stretch challenge: let the user choose the ending number.

## Exercise 4: Average Score Method

Difficulty: Beginner

Concepts practiced: methods, arrays, loops

Problem statement: write a method that accepts an `int[]` of scores and returns the average as a `double`.

Example input:

```text
90, 80, 70
```

Example output:

```text
80.0
```

Hints:

- Add all values first.
- Cast before dividing.
- Avoid dividing by zero.

Stretch challenge: return `0.0` for an empty array.

## Exercise 5: Word Analyzer

Difficulty: Beginner

Concepts practiced: strings, conditionals, methods

Problem statement: write a program that prints the length of a word, whether it contains the letter `a`, and the uppercase version.

Example input:

```text
java
```

Example output:

```text
Length: 4
Contains a: true
Uppercase: JAVA
```

Hints:

- Use `length`.
- Use `contains`.
- Use `toUpperCase`.

Stretch challenge: treat uppercase and lowercase `A` the same.
