# Architecture Review Solutions

## Exercise 1

Checklist:

- Is the backend stateless?
- What are the single points of failure?
- How many database connections can instances open?
- Where are secrets kept?
- What metrics show health and saturation?
- What grows with traffic and cost?
- How is a bad deployment rolled back?

## Exercise 2

A good review response keeps the learner's goals but asks for evidence. Start with a simple load-balanced backend and managed database. Add caching only after read load and staleness rules are known. Add a queue only when slow async work exists. Add a CDN for static or file-heavy traffic. Add an API gateway when routing, auth, or rate-limit needs are clear.

