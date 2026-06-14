# Calculator CLI

A beginner-friendly command-line calculator that performs addition, subtraction, multiplication, and division.

## Concepts Practiced

- Classes and methods
- `switch` expressions
- Console input with `Scanner`
- Loops
- Basic validation
- Handling division by zero

## Files

```text
CalculatorApp.java
Calculator.java
```

## Compile

From the repository root:

```bash
javac projects/beginner/calculator-cli/Calculator.java projects/beginner/calculator-cli/CalculatorApp.java
```

## Run

```bash
java -cp projects/beginner/calculator-cli CalculatorApp
```

## Example Usage

```text
Choose an operation:
1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Enter choice: 1
Enter first number: 10
Enter second number: 5
Result: 15.0
```

## Possible Improvements

- Add more operations.
- Keep a history of calculations.
- Add automated tests after moving the code into a Maven source package.
- Improve input validation for non-numeric values.
