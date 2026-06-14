# Reflection, Networking, And JDBC Quiz

## Multiple Choice

1. Which annotation retention allows reflection to read an annotation at runtime?
   - A. `SOURCE`
   - B. `CLASS`
   - C. `RUNTIME`
   - D. `STATIC`

2. Why should HTTP clients use timeouts?
   - A. Networks can hang or respond slowly
   - B. Timeouts make passwords safer
   - C. Timeouts replace tests
   - D. Java requires them to compile

3. Which JDBC tool helps avoid SQL injection?
   - A. String concatenation
   - B. `PreparedStatement`
   - C. `System.out.println`
   - D. `Thread.sleep`

## Short Answer

4. Why should reflection be isolated in a small part of the codebase?
5. Where should database credentials come from in a professional app?
6. Name one network failure a program should handle gracefully.

## Code Reading

7. What is unsafe about this SQL shape?

```java
String sql = "select * from users where email = '" + email + "'";
```
