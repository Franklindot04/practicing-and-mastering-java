# Getting Started With Java

## Why This Matters

Before learning syntax, you need a working setup. A learner who can compile, run, and debug a small program has a much easier time practicing consistently.

## Recommended Setup

- Java 21 LTS for modern learning.
- Java 17 is acceptable because many companies still use it.
- IntelliJ IDEA Community, VS Code, or Eclipse.
- A terminal where `java -version` and `javac -version` work.
- Maven for dependency management and tests.

## Install Java

Check your current Java version:

```bash
java -version
javac -version
```

You should see a Java runtime and compiler. If `javac` is missing, you installed only a runtime instead of a JDK.

## Hello World Workflow

Create `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

Compile and run:

```bash
javac HelloWorld.java
java HelloWorld
```

## Common Mistakes

- Naming the file differently from the public class.
- Running `java HelloWorld.class` instead of `java HelloWorld`.
- Installing a JRE instead of a JDK.
- Forgetting that Java is case-sensitive.
- Copying commands without understanding the current folder.

## Practice Prompts

- Print your name and learning goal.
- Print three lines using three `System.out.println` calls.
- Intentionally misspell `main`, observe the error, then fix it.
- Run `java -version` and write down the installed version.

## Before Moving On

You should understand:

- [ ] The difference between JDK, JRE, `java`, and `javac`.
- [ ] How to compile a single Java file.
- [ ] How to run a compiled class.
- [ ] Why file names and public class names must match.

## Next Step

Continue to [Java Basics](../01-java-basics/README.md), then practice with [beginner examples](../../examples/beginner/README.md) and [beginner exercises](../../exercises/beginner/README.md).
