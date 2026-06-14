# Collections Framework

## Why This Matters

The collections framework gives Java developers ready-made data structures for storing, searching, ordering, and transforming groups of objects.

## Main Interfaces

- `List`: ordered collection that allows duplicates.
- `Set`: collection that avoids duplicates.
- `Queue`: collection for processing items in order.
- `Deque`: double-ended queue.
- `Map`: key-value lookup structure.

## Lists

- `ArrayList`: fast random access, common default list.
- `LinkedList`: efficient additions/removals at ends, less common as a general list.

Use a list when order matters and duplicates are allowed.

## Sets

- `HashSet`: fast membership checks, no guaranteed order.
- `LinkedHashSet`: keeps insertion order.
- `TreeSet`: keeps sorted order.

Use a set when uniqueness matters.

## Queues

- `Queue`: process items first-in-first-out.
- `Deque`: add or remove from both ends.
- `PriorityQueue`: process items by priority.

Use queues when the order of processing matters.

## Maps

- `HashMap`: fast key-value lookup, no guaranteed order.
- `LinkedHashMap`: keeps insertion order.
- `TreeMap`: keeps keys sorted.

Use a map when you need to look up a value by key.

## Iterators

Iterators safely move through collections and can remove items during traversal.

```java
Iterator<String> iterator = names.iterator();
while (iterator.hasNext()) {
    String name = iterator.next();
    if (name.isBlank()) {
        iterator.remove();
    }
}
```

## Comparable And Comparator

- `Comparable` defines a natural order for a type.
- `Comparator` defines an external ordering rule.

Use `Comparator` when you need multiple sorting strategies.

## Collections Utility Class

`Collections` includes helpers such as `sort`, `reverse`, `shuffle`, `min`, `max`, and immutable wrappers.

## Big-O Basics

Approximate common operations:

| Structure | Lookup | Add | Contains |
| --- | --- | --- | --- |
| `ArrayList` | O(1) by index | O(1) amortized at end | O(n) |
| `LinkedList` | O(n) | O(1) at ends | O(n) |
| `HashSet` | not index-based | O(1) average | O(1) average |
| `TreeSet` | not index-based | O(log n) | O(log n) |
| `HashMap` | O(1) average by key | O(1) average | O(1) average by key |
| `TreeMap` | O(log n) by key | O(log n) | O(log n) |

## Common Mistakes

- Using a list when uniqueness matters.
- Assuming `HashMap` or `HashSet` iteration order is stable.
- Modifying a collection inside an enhanced `for` loop.
- Sorting objects without defining a comparison rule.
- Ignoring null-handling rules.

## When To Use Which Collection

- Need ordered values with duplicates: `ArrayList`.
- Need unique values: `HashSet`.
- Need unique values in insertion order: `LinkedHashSet`.
- Need sorted unique values: `TreeSet`.
- Need key-value lookup: `HashMap`.
- Need sorted keys: `TreeMap`.
- Need first-in-first-out processing: `ArrayDeque`.
- Need priority processing: `PriorityQueue`.

## Practice Prompts

- Count word frequency with `HashMap`.
- Remove duplicate names with `HashSet`.
- Sort students by score with `Comparator`.
- Process support tickets with `Queue`.

## Before Moving On

You should understand the differences between `List`, `Set`, `Queue`, `Deque`, and `Map`, and you should be able to choose one for a small problem.
