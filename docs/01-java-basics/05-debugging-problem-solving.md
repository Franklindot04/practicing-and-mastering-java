# Basic Debugging And Problem Solving

## Debugging Mindset

Debugging is not guessing. It is the process of narrowing down what the program actually does compared with what you expected.

## Practical Steps

1. Read the error message.
2. Find the file and line number.
3. Reproduce the issue.
4. Print or inspect important values.
5. Change one thing at a time.
6. Run again.

## Problem-Solving Pattern

1. Restate the problem.
2. Identify inputs and outputs.
3. Write examples.
4. Break the work into steps.
5. Turn steps into code.
6. Test normal cases and edge cases.

## Example

Problem: determine whether a number is even.

Inputs: one integer.

Output: true or false.

Rule: a number is even when `number % 2 == 0`.

```java
public static boolean isEven(int number) {
    return number % 2 == 0;
}
```

## Common Mistakes

- Ignoring compiler messages.
- Changing many lines before testing again.
- Only testing the happy path.
- Solving before understanding the examples.

## Practice Prompts

- Debug a loop that prints one extra number.
- Write test cases for a grade calculator.
- Explain a small program line by line out loud.

## Before Moving On

You should be able to turn a small problem into inputs, outputs, rules, examples, and code.
