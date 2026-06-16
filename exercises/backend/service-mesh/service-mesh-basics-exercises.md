# Service Mesh Basics Exercises

## Exercise 1: Explain The Mesh Boundary

Difficulty: Beginner

Concepts practiced: service mesh purpose, API gateway comparison, ownership boundaries.

Problem statement: Describe what a service mesh could manage for internal traffic in a backend system, then list three responsibilities that still belong to application code.

Hints:

- Think about east-west traffic.
- Separate workload identity from user identity.
- Remember validation and business rules.

Stretch: Add one case where an API gateway is the better fit.

## Exercise 2: Decide Whether A Mesh Is Needed

Difficulty: Beginner

Concepts practiced: readiness, operational tradeoffs, avoiding premature complexity.

Problem statement: A team has one Spring Boot API, an H2 demo database, and no internal service calls. Explain whether a service mesh is justified.

Hints:

- Count service-to-service calls.
- Look for repeated communication policy needs.
- Consider operational cost.

Stretch: Name two improvements the team should make before revisiting mesh adoption.

