# JPA And Spring Data Exercises

## Exercise 1: Match Annotations

Difficulty: Beginner

Concepts practiced: JPA annotations

Problem statement: Match these annotations to their purpose: `@Entity`, `@Id`, `@GeneratedValue`, `@Column`, `@Table`, `@ManyToOne`, `@OneToMany`, `@JoinColumn`, `@Transactional`.

Hints:

- Some annotations map classes and fields.
- Some describe relationships.
- One often belongs on service methods.

Stretch challenge: Explain why `@Transactional` is not just a database annotation.

## Exercise 2: Spring Data Repository Reasoning

Difficulty: Beginner

Concepts practiced: `JpaRepository`, id type, generated methods

Problem statement: For `interface TaskRepository extends JpaRepository<TaskEntity, Long>`, explain what `TaskEntity` and `Long` mean and list three methods inherited from `JpaRepository`.

Hints:

- One type is the entity.
- One type is the primary key.

Stretch challenge: Add a derived query method for completed tasks.

## Exercise 3: Transaction Boundary

Difficulty: Beginner to Intermediate

Concepts practiced: Transactions, service boundaries

Problem statement: A service creates an order and then creates audit history for that order. Explain why these two writes may belong in one transaction.

Hints:

- Think about what happens if the second write fails.
- Think about rollback.

Stretch challenge: Name the method where you would put `@Transactional`.
