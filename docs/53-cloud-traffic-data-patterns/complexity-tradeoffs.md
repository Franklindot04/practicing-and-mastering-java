# Complexity Tradeoffs

Architecture components solve problems but also create responsibilities.

## When Not To Add Complexity

Avoid adding a component when:

- The current bottleneck is unknown.
- The team cannot operate it.
- The failure mode is not understood.
- The data consistency rule is unclear.
- A simpler application or database change would solve the problem.

## Common Traffic And Data Mistakes

- Adding a cache without invalidation rules.
- Adding a queue without retry and dead-letter thinking.
- Scaling backend instances while ignoring database connections.
- Using object storage as a database.
- Using a database for large binary files without a reason.
- Adding an API gateway before routing and auth needs are clear.
- Treating CDN behavior as invisible.

## Better Habits

Start with a readable flow diagram. Name each component's job. Add one component at a time, and write down what new operational responsibility it creates.

