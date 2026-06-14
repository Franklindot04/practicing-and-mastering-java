# Exceptions And Files Exercises

## Exercise 1: Safe Integer Parser

Difficulty: Intermediate

Concepts practiced: exceptions, validation

Problem statement: write a method that accepts text and returns an integer. If parsing fails, return a default value.

Example input/output:

```text
"25" -> 25
"oops" -> 0
```

Hints:

- Use `Integer.parseInt`.
- Catch `NumberFormatException`.

Stretch challenge: let the caller choose the default value.

## Exercise 2: Read Lines From A File

Difficulty: Intermediate

Concepts practiced: `Path`, `Files`, checked exceptions

Problem statement: write a method that reads all lines from a text file and prints them with line numbers.

Hints:

- Use `Files.readAllLines`.
- Let `IOException` be handled by the caller or catch it with a friendly message.

Stretch challenge: skip blank lines.

## Exercise 3: Word Count File Report

Difficulty: Intermediate

Concepts practiced: file handling, maps, strings

Problem statement: read a file and count how many times each word appears.

Example output:

```text
java=3
files=2
```

Hints:

- Split text on whitespace.
- Normalize words to lowercase.
- Use `Map<String, Integer>`.

Stretch challenge: remove punctuation before counting.
