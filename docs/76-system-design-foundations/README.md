# System Design Foundations

System design is the practice of turning a product goal into a working technical shape. It combines requirements, constraints, data, APIs, operations, security, cost, and tradeoffs.

This section is design-focused. It does not create cloud resources, provision infrastructure, or recommend one universal architecture.

## Topics

- [System Design Process](system-design-process.md)
- [Requirements And Constraints](requirements-and-constraints.md)
- [Workflows Data Flow And Boundaries](workflows-data-flow-and-boundaries.md)
- [Tradeoffs Diagrams And Reviews](tradeoffs-diagrams-and-reviews.md)
- [Common Beginner Mistakes](common-beginner-mistakes.md)

## Learning Goals

After this section, you should be able to:

- Explain system design in beginner-friendly language.
- Separate functional requirements from non-functional requirements.
- Identify assumptions, constraints, users, actors, and external dependencies.
- Sketch read and write workflows before choosing implementation details.
- Draw simple architecture diagrams that show boundaries and data flow.
- Discuss tradeoffs without claiming one design is always best.
- Use review questions to improve a design before implementation.

## Big Idea

A system design is a reasoned proposal, not a pile of technologies.

```text
Goal
  |
  v
Requirements -> Constraints -> Workflows -> Boundaries -> Tradeoffs -> Review
```

The quality of a design depends on how clearly it explains why a choice fits the problem.

## Reusable Checklist

- What are users trying to accomplish?
- Which requirements are functional?
- Which requirements are non-functional?
- What constraints are already known?
- Which assumptions need validation?
- What are the main read and write workflows?
- What data enters, changes, and leaves the system?
- Which parts are inside the system boundary?
- Which external systems are dependencies?
- What can fail?
- What tradeoffs are being accepted?
- What would make this design too expensive or too complex?
