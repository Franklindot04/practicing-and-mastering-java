# Inheritance, Polymorphism, And Abstraction

## Core Idea

Inheritance lets a class reuse and specialize behavior from another class. Polymorphism lets code work with a general type while the actual object decides which behavior runs.

```java
abstract class Notification {
    abstract void send(String message);
}

class EmailNotification extends Notification {
    @Override
    void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsNotification extends Notification {
    @Override
    void send(String message) {
        System.out.println("SMS: " + message);
    }
}
```

```java
Notification notification = new EmailNotification();
notification.send("Build finished");
```

## Why It Matters

These ideas make code easier to extend when many types share a common role.

## Common Mistakes

- Using inheritance just to reuse a few lines of code.
- Creating deep inheritance chains.
- Forgetting `@Override`.
- Making abstract classes when an interface would be clearer.

## Practice Prompts

- Model different payment methods.
- Model shapes with an `area` method.
- Model employees with different pay calculations.

## Before Moving On

You should understand superclass, subclass, overriding, abstract classes, and dynamic dispatch.
