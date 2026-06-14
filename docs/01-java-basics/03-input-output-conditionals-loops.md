# Input, Output, Conditionals, And Loops

## Input And Output

Use `System.out.println` to show output. Use `Scanner` for simple console input.

```java
import java.util.Scanner;

public class InputDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your score: ");
        int score = scanner.nextInt();
        System.out.println("Score: " + score);
    }
}
```

## Conditionals

Conditionals let your program choose what to do.

```java
if (score >= 70) {
    System.out.println("Pass");
} else {
    System.out.println("Keep practicing");
}
```

## Loops

Loops repeat work.

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

```java
int attempts = 0;
while (attempts < 3) {
    attempts++;
}
```

## Why It Matters

Real programs respond to input, make decisions, and repeat tasks. These ideas power menus, validation, games, and data processing.

## Common Mistakes

- Forgetting to import `Scanner`.
- Comparing strings with `==` instead of `.equals`.
- Writing loops that never end.
- Off-by-one errors like stopping at 9 when you meant 10.

## Practice Prompts

- Ask for an age and print whether the person can vote.
- Print all numbers from 1 to 100.
- Print only even numbers from 1 to 50.
- Keep asking for a password until it matches.

## Before Moving On

You should understand `if`, `else if`, `else`, `for`, `while`, and simple console input.
