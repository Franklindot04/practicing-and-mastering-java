# Classes, Objects, And Encapsulation

## Core Idea

A class is a blueprint. An object is a specific instance created from that blueprint.

```java
public class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double openingBalance) {
        this.owner = owner;
        this.balance = openingBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

## Why Encapsulation Matters

Encapsulation protects an object's internal state. Instead of letting other code change `balance` directly, the class controls valid changes through methods.

## Common Mistakes

- Making every field public.
- Creating getters and setters without thinking about rules.
- Putting all logic in `main`.
- Creating classes that store data but have no useful behavior.

## Practice Prompts

- Create a `Student` class with name and scores.
- Create a `Book` class with title, author, and availability.
- Add validation to prevent invalid values.

## Before Moving On

You should understand fields, methods, constructors, `this`, private state, and public behavior.
