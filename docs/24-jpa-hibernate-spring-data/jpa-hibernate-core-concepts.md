# JPA And Hibernate Core Concepts

JPA lets Java code describe database-backed objects. Hibernate performs the runtime mapping.

## Entity Basics

An entity is a Java class mapped to a database table.

```java
@Entity
@Table(name = "tasks")
class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;
}
```

## Common Annotations

`@Entity`

Marks a class as persistent.

`@Table`

Customizes the table name.

`@Id`

Marks the primary key field.

`@GeneratedValue`

Lets the database or persistence provider generate ids.

`@Column`

Customizes column rules such as `nullable`, `length`, or `unique`.

`@Transactional`

Defines a transaction boundary. Service methods often use it when multiple database operations must succeed or fail together.

## Entity Rules For Beginners

- Use a no-argument constructor for JPA.
- Keep ids nullable before the entity is saved.
- Avoid exposing entities directly from controllers.
- Keep validation and workflow decisions in services.

## Validation Before Persistence

Validate request DTOs before creating or updating entities. A database constraint can protect stored data, but the API should still return clear validation errors.
