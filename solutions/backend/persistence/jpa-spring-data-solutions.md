# JPA And Spring Data Solutions

## Exercise 1

- `@Entity`: marks a persistent class.
- `@Id`: marks the primary key.
- `@GeneratedValue`: asks persistence/database tooling to generate ids.
- `@Column`: customizes a database column.
- `@Table`: customizes table mapping.
- `@ManyToOne`: many rows relate to one parent.
- `@OneToMany`: one parent relates to many child rows.
- `@JoinColumn`: names the foreign key column.
- `@Transactional`: marks work that should commit or roll back together.

## Exercise 2

`TaskEntity` is the entity type. `Long` is the id type.

Inherited methods include:

- `save`
- `findById`
- `findAll`
- `delete`

A derived query could be:

```java
List<TaskEntity> findByCompleted(boolean completed);
```

## Exercise 3

Creating an order and audit history should often be one transaction because the system should not save the order while losing the audit event. Put `@Transactional` on the service method that coordinates both writes, such as `OrderService.createOrder`.
