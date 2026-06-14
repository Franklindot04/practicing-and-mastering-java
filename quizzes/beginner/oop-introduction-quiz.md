# Beginner OOP Introduction Quiz

Use this quiz after studying classes, objects, fields, methods, constructors, and basic encapsulation.

## Multiple Choice

1. What is a class?
   - A. A blueprint for creating objects
   - B. A loop that repeats code
   - C. A command that compiles Java files
   - D. A file that can only store numbers

2. What is an object?
   - A. A saved compiler warning
   - B. A specific instance created from a class
   - C. A replacement for every method
   - D. A package name

3. Which keyword creates a new object?
   - A. `class`
   - B. `static`
   - C. `new`
   - D. `return`

4. What is a constructor used for?
   - A. Starting a `for` loop
   - B. Initializing a new object
   - C. Comparing two strings
   - D. Ending a program

5. Why are fields often marked `private`?
   - A. To prevent the class from compiling
   - B. To hide object data and control access through methods
   - C. To make the fields global
   - D. To allow only arrays to use them

6. Which method name follows common Java getter style for a field named `name`?
   - A. `read-name()`
   - B. `name.get()`
   - C. `getName()`
   - D. `Name()`

## Short Answer

7. In your own words, explain the difference between a class and an object.

8. Why might a `BankAccount` class have a `deposit` method instead of letting other code change its balance field directly?

9. Write one field and one method that could belong to a `Student` class.

10. What does `this` usually refer to inside an instance method?

## Code Reading

11. What does this program print?

```java
class Dog {
    private String name;

    Dog(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

Dog dog = new Dog("Milo");
System.out.println(dog.getName());
```

12. How many `Book` objects are created in this code?

```java
Book first = new Book("Clean Code");
Book second = new Book("Effective Java");
Book third = second;
```

13. What is the likely problem with this class?

```java
class Counter {
    private int value;

    int getValue() {
        return value;
    }
}

Counter counter = new Counter();
counter.value = 5;
```

14. What value is printed?

```java
class Rectangle {
    private int width;
    private int height;

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    int area() {
        return width * height;
    }
}

Rectangle rectangle = new Rectangle(4, 3);
System.out.println(rectangle.area());
```
