# Exceptions And Files Solutions

## Safe Integer Parser

Call `Integer.parseInt(text)` inside a `try` block. Catch `NumberFormatException` and return the default value.

## Read Lines From A File

Use `Files.readAllLines(path)` or `Files.lines(path)`. Keep the `Path` as a parameter so the method is easy to test.

## Word Count File Report

Read the file, split each line into words, normalize with `toLowerCase`, and update a `Map<String, Integer>` with `getOrDefault`.
