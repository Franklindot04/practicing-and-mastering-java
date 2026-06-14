# Practicing and Mastering Java

A progressive Java learning, practicing, and mastery repository for learners who want a structured path from first programs to professional Java development.

This repository is built progressively. Each branch should add a focused, reviewable part of the learning system instead of one large drop of content.

## Who This Is For

- Beginners learning Java for the first time.
- Developers revisiting Java fundamentals.
- Students preparing for interviews.
- Builders who want portfolio-quality Java projects.
- Anyone who wants notes, examples, exercises, quizzes, and projects in one place.

## Java Version

The primary target is Java 21 LTS. Java 17 is also acceptable for many examples because it is still widely used in companies.

## How To Use This Repository

1. Read `LEARNING_PATH.md` to understand the recommended order.
2. Use `ROADMAP.md` to see the big picture.
3. Study a topic note in `docs/`.
4. Run matching examples from `examples/`.
5. Solve exercises from `exercises/`.
6. Compare your thinking with `solutions/` when available.
7. Build projects from `projects/`.
8. Track progress in `PROGRESS.md`.

New learners should start with the [beginner learning sequence](LEARNING_PATH.md#beginner-learning-sequence), which connects setup, notes, examples, exercises, projects, quizzes, and solutions in order.

After the beginner path, continue with the [intermediate learning sequence](LEARNING_PATH.md#intermediate-learning-sequence) for generics, exceptions, files, dates, Maven, testing, lambdas, streams, and Optional.

## Folder Structure

```text
docs/           Concept notes from beginner to advanced Java
examples/       Runnable Java examples
exercises/      Practice prompts and drills
solutions/      Suggested solutions and explanations
projects/       Portfolio-oriented Java projects
quizzes/        Topic quizzes and review questions
interview-prep/ Interview notes and coding challenges
templates/      Reusable content templates
```

## Running Examples

Compile and run a simple Java file:

```bash
javac examples/beginner/HelloWorld.java
java -cp examples/beginner HelloWorld
```

For Maven-based code:

```bash
mvn test
mvn package
```

## Suggested Study Routine

- Study one concept note.
- Type the example code yourself.
- Change the example and predict the output.
- Complete two or three exercises.
- Write a short note explaining what was confusing.
- Revisit the topic after a few days.

## Project Roadmap

The repository grows in focused branches:

- Foundation and contribution files.
- Beginner notes, examples, and exercises.
- Object-oriented programming.
- Collections and generics.
- Exceptions, files, dates, Maven, and testing.
- Lambdas, streams, and Optional.
- Concurrency, JVM, performance, and advanced Java.
- Interview preparation and portfolio projects.

## Disclaimer

This is a learning and mastery repository. Some examples may intentionally simplify real-world concerns. Security-sensitive or intentionally vulnerable examples must be clearly marked as educational.
