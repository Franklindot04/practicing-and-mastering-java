# Calculator CLI Solution Walkthrough

## What The Project Does

The Calculator CLI is a small command-line program that lets a learner choose an operation, enter two numbers, and see the result. It supports addition, subtraction, multiplication, and division.

## Main Java Concepts Used

- Classes and objects
- Methods with parameters and return values
- `Scanner` for console input
- `while` loops for repeated menu choices
- `switch` expressions for operation selection
- Exceptions for invalid division

## Important Classes And Methods

- `Calculator` contains the arithmetic logic.
- `CalculatorApp` contains the console menu and user input.
- `add`, `subtract`, `multiply`, and `divide` each handle one operation.
- `readMenuChoice` keeps asking until the user enters a valid menu number.
- `readNumber` keeps asking until the user enters a valid decimal number.

## Step-By-Step Logic

1. Create a `Scanner` to read from the keyboard.
2. Create a `Calculator` object.
3. Show the menu inside a loop.
4. Read the menu choice.
5. If the choice is exit, stop the loop.
6. Otherwise, read two numbers.
7. Call the matching calculator method.
8. Print the result.
9. If division by zero happens, print a friendly error message instead of crashing.

## Common Beginner Mistakes

- Forgetting to handle division by zero.
- Using `=` instead of `==` when comparing values.
- Putting all arithmetic directly in the menu instead of using small methods.
- Not checking whether the next scanner value is actually a number.
- Forgetting to close the `Scanner` when the program is done.

## Possible Improvements

- Add more operations, such as powers or square roots.
- Add a history of recent calculations.
- Let the user continue with the previous result.
- Add tests for the non-interactive calculator methods.
- Improve the formatting of whole-number results.

## Reflection Questions

1. Why is the arithmetic logic easier to test when it is in `Calculator` instead of inside `main`?
2. What should a calculator do when the user enters an invalid menu choice?
3. How would you add a new operation without rewriting the whole program?
4. Why does `divide` throw an exception instead of returning `0` for division by zero?
