# Learning Path

Follow this path in order unless you already know a topic well.

1. Getting started: install Java 21, set up an IDE, compile and run programs.
2. Java basics: syntax, variables, data types, operators, input, conditionals, loops, methods, arrays, strings.
3. Object-oriented programming: classes, objects, constructors, encapsulation, inheritance, polymorphism, interfaces, records, enums.
4. Collections: lists, sets, queues, maps, iterators, sorting, Big-O basics.
5. Intermediate Java: generics, exceptions, files, NIO, dates, Maven, unit testing, debugging.
6. Functional Java: lambdas, functional interfaces, streams, Optional.
7. Advanced Java: concurrency, executors, futures, locks, concurrent collections, JVM, garbage collection, performance.
8. Professional practices: clean code, SOLID, design patterns, architecture, interview preparation.

Before moving on from each level, you should be able to explain the concepts in your own words, write a small example without copying, complete exercises, and build a mini project.

## Beginner Learning Sequence

Use this sequence before moving into object-oriented programming or intermediate topics.

- [ ] Set up Java and confirm `java -version` and `javac -version` work: [Getting Started](docs/00-getting-started/README.md)
- [ ] Read the Java basics overview: [Java Basics](docs/01-java-basics/README.md)
- [ ] Study the basics notes in order:
  - [Hello World and Syntax](docs/01-java-basics/01-hello-world-and-syntax.md)
  - [Variables, Data Types, and Operators](docs/01-java-basics/02-variables-data-types-operators.md)
  - [Input, Output, Conditionals, and Loops](docs/01-java-basics/03-input-output-conditionals-loops.md)
  - [Methods, Arrays, and Strings](docs/01-java-basics/04-methods-arrays-strings.md)
  - [Debugging and Problem Solving](docs/01-java-basics/05-debugging-problem-solving.md)
- [ ] Run and modify the beginner examples: [Beginner Examples](examples/beginner/README.md)
- [ ] Complete the beginner exercises: [Beginner Exercises](exercises/beginner/README.md)
- [ ] Build one beginner project, then build another without copying: [Beginner Projects](projects/beginner/README.md)
- [ ] Check your understanding with quizzes: [Beginner Quizzes](quizzes/beginner/README.md)
- [ ] Review available explanations after attempting the work yourself: [Beginner Solutions](solutions/beginner/README.md)

## Ready For OOP Checklist

Move to object-oriented programming when you can:

- [ ] Compile and run a Java file from the terminal.
- [ ] Use variables, operators, conditionals, and loops without copying.
- [ ] Write small methods that accept parameters and return values.
- [ ] Use arrays and strings in simple programs.
- [ ] Read beginner compiler errors and make a reasonable fix.
- [ ] Build a small CLI project with input, decisions, and repeated actions.

## Intermediate Learning Sequence

Use this sequence after the beginner path and core OOP topics.

- [ ] Review collections: [Collections Framework](docs/03-collections-framework/README.md)
- [ ] Study generics: [Generics](docs/04-generics/README.md)
- [ ] Practice error handling: [Exception Handling](docs/05-exception-handling/README.md)
- [ ] Read and write files: [Files, IO, And NIO](docs/06-files-io-nio/README.md)
- [ ] Learn functional Java: [Lambdas, Streams, And Optional](docs/07-lambdas-streams/README.md)
- [ ] Work with modern dates and times: [Date And Time API](docs/08-date-time-api/README.md)
- [ ] Add test coverage: [Testing And Debugging](docs/09-testing-debugging/README.md)
- [ ] Run builds with Maven: [Maven First](docs/10-maven-gradle/README.md)
- [ ] Run and modify intermediate examples: [Intermediate Examples](examples/intermediate/java/README.md)
- [ ] Complete intermediate exercises: [Intermediate Exercises](exercises/intermediate/)
- [ ] Build intermediate projects: [Intermediate Projects](projects/intermediate/README.md)
- [ ] Check understanding with intermediate quizzes: [Intermediate Quizzes](quizzes/intermediate/README.md)
- [ ] Review intermediate solutions after attempting the work: [Intermediate Solutions](solutions/intermediate/)

## Ready For Advanced Java Checklist

Move to advanced Java when you can:

- [ ] Use generics in classes and methods without raw types.
- [ ] Explain checked and unchecked exceptions.
- [ ] Read from and write to files with `Path` and `Files`.
- [ ] Use `LocalDate`, `LocalDateTime`, and `DateTimeFormatter` for common tasks.
- [ ] Write JUnit tests for service logic and edge cases.
- [ ] Use streams for clear collection transformations.
- [ ] Use `Optional` to represent missing results without returning `null`.
- [ ] Build an intermediate CLI project with separated model and service logic.

## Advanced Learning Sequence

Use this sequence after the intermediate path and before backend frameworks.

- [ ] Study concurrency fundamentals: [Concurrency And Multithreading](docs/11-concurrency-multithreading/README.md)
- [ ] Study JVM memory and performance basics: [JVM, Memory, Garbage Collection, And Performance](docs/12-jvm-memory-performance/README.md)
- [ ] Learn design pattern tradeoffs: [Design Patterns](docs/13-design-patterns/README.md)
- [ ] Explore advanced language features: [Advanced Java Language Features](docs/16-advanced-java/README.md)
- [ ] Practice maintainable design: [Clean Code, SOLID, And Basic Architecture](docs/17-clean-code-architecture/README.md)
- [ ] Run and modify advanced examples: [Advanced Examples](examples/advanced/)
- [ ] Complete advanced exercises: [Advanced Exercises](exercises/advanced/)
- [ ] Build an advanced project: [Advanced Projects](projects/advanced/README.md)
- [ ] Check understanding with advanced quizzes: [Advanced Quizzes](quizzes/advanced/README.md)
- [ ] Review advanced solutions after attempting the work: [Advanced Solutions](solutions/advanced/)

## Ready For Professional Java Checklist

Move toward backend, Spring, databases, and production-style topics when you can:

- [ ] Explain race conditions and protect shared state.
- [ ] Use executors and futures without leaking threads.
- [ ] Describe stack, heap, reachability, and garbage collection basics.
- [ ] Measure before making performance changes.
- [ ] Recognize when a design pattern helps and when it adds clutter.
- [ ] Apply SOLID principles to keep project logic testable.
- [ ] Build a CLI project that separates parsing, service logic, and output.
