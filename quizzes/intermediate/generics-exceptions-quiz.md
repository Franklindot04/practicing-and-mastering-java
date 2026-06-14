# Generics And Exceptions Quiz

## Multiple Choice

1. What does `List<String>` prevent?
   - A. Adding duplicate strings
   - B. Adding non-String values at compile time
   - C. Reading values from the list
   - D. Sorting the list

2. Where does the type parameter go in a generic method?
   - A. Before the return type
   - B. After the method body
   - C. Only in the class name
   - D. Inside the `catch` block

3. Which exception is checked?
   - A. `NullPointerException`
   - B. `IllegalArgumentException`
   - C. `IOException`
   - D. `NumberFormatException`

4. What should a `catch` block usually do?
   - A. Hide all errors silently
   - B. Handle the problem or add useful context
   - C. Always restart the program
   - D. Delete the stack trace

## Short Answer

5. Why are generics safer than using raw `List`?
6. When would `List<? extends Number>` be useful?
7. What is the difference between throwing and catching an exception?

## Code Reading

8. What is printed?

```java
List<Integer> values = List.of(2, 4, 6);
System.out.println(values.get(1));
```

9. What happens?

```java
try {
    int value = Integer.parseInt("abc");
    System.out.println(value);
} catch (NumberFormatException error) {
    System.out.println("Invalid number");
}
```
