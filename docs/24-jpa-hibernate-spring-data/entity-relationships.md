# Entity Relationships

Relational databases connect tables with keys. JPA maps those relationships into Java objects.

## Many-To-One

Many comments can belong to one task.

```java
@ManyToOne
@JoinColumn(name = "task_id", nullable = false)
private Task task;
```

`@JoinColumn` names the foreign key column.

## One-To-Many

One task can have many comments.

```java
@OneToMany(mappedBy = "task")
private List<Comment> comments = new ArrayList<>();
```

`mappedBy` says the other side owns the foreign key.

## Lazy Vs Eager Loading

Lazy loading waits to load related data until it is needed. Eager loading loads related data immediately.

Beginner rule: prefer lazy relationships unless you have a clear reason to load related data every time.

## N+1 Query Problem

The N+1 problem happens when code loads one list and then accidentally runs one extra query for each item in that list.

Example idea:

```text
1 query loads 20 tasks
20 more queries load comments for each task
```

You do not need to fix every performance issue immediately, but you should recognize this pattern.

## DTOs And Relationships

Avoid returning relationship-heavy entities directly from controllers. Create response DTOs that include only the data the client needs.
