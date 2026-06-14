# Collections Framework Exercises

## Exercise 1: Unique Names

Difficulty: Beginner

Concepts practiced: `Set`, `HashSet`, duplicates

Problem statement: given a list of names with duplicates, print each unique name once.

Example input:

```text
Ada, Grace, Ada, James
```

Example output:

```text
Ada, Grace, James
```

Hints:

- Use a `Set`.
- Use `LinkedHashSet` if you want insertion order.

Stretch challenge: print how many duplicates were removed.

## Exercise 2: Word Frequency Counter

Difficulty: Intermediate

Concepts practiced: `Map`, `HashMap`, loops

Problem statement: count how many times each word appears in a sentence.

Example input:

```text
java is fun and java is powerful
```

Example output:

```text
java=2
is=2
fun=1
and=1
powerful=1
```

Hints:

- Split the sentence by spaces.
- Use `getOrDefault`.

Stretch challenge: ignore uppercase/lowercase differences.

## Exercise 3: Ticket Queue

Difficulty: Intermediate

Concepts practiced: `Queue`, `ArrayDeque`

Problem statement: simulate support tickets. Add three tickets, process them in order, and print each processed ticket.

Hints:

- Use `offer`.
- Use `poll`.
- Stop when the queue is empty.

Stretch challenge: include a ticket priority and switch to `PriorityQueue`.

## Exercise 4: Sort Students

Difficulty: Intermediate

Concepts practiced: `Comparator`, records/classes, sorting

Problem statement: create students with names and scores. Sort by score from highest to lowest.

Hints:

- Use `Comparator.comparingInt`.
- Use `.reversed()`.

Stretch challenge: if scores tie, sort by name.

## Exercise 5: Safe Removal

Difficulty: Intermediate

Concepts practiced: `Iterator`, collection mutation

Problem statement: remove blank strings from a list without causing a concurrent modification error.

Hints:

- Use `Iterator`.
- Call `iterator.remove()`.

Stretch challenge: remove strings that are blank after trimming.
