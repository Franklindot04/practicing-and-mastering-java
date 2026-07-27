# System Design Exercises

## Exercise 1: Requirements Clarification For Order Search

Difficulty: Intermediate

Concepts: requirements clarification, functional requirements, non-functional requirements, assumptions, API design

Scenario: A product team asks for "fast order search" in a Java backend.

Requirements:

- Customers can search their own orders.
- Support staff can search by customer reference and status.
- Search results show order ID, status, total, and creation date.

Constraints:

- Do not assume a new search service is required.
- Some data may be sensitive.
- Exact traffic is not known yet.

Questions:

- Which functional requirements need clarification?
- Which non-functional requirements would you ask for?
- What API boundaries would you sketch first?
- Which assumptions would you label as unverified?

Hints:

- Separate customer search from support search.
- Ask about latency, freshness, authorization, and result size.

Stretch challenge: propose a first design and a later design if traffic grows 20x.

## Exercise 2: Capacity Estimate For A Task API

Difficulty: Intermediate

Concepts: capacity estimation, traffic assumptions, read/write ratio, cost-aware design

Scenario: A task API expects 50,000 daily active users. Each user creates 4 tasks per day and reads their task list 20 times per day.

Requirements:

- Estimate average create-task requests per second.
- Estimate average list-task requests per second.
- Identify peak-traffic assumptions.
- Decide whether caching is obviously required.

Constraints:

- Do not claim exact estimates are possible without measurements.
- Keep the design simple unless the numbers justify complexity.

Questions:

- What numbers can you calculate?
- What peak multiplier would you choose and why?
- Which metric would you monitor after launch?
- Which cost could grow unexpectedly?

Hints:

- Daily average traffic can hide peaks.
- Reads and writes often need different scaling strategies.

Stretch challenge: describe how the answer changes if one large tenant creates half the traffic.

## Exercise 3: Modular Monolith Or Microservices

Difficulty: Advanced

Concepts: service boundaries, modular monoliths, microservices, domain ownership, shared databases

Scenario: A small team owns ordering, inventory, and notifications for a new commerce product.

Requirements:

- Orders need inventory checks.
- Notifications are useful but not critical to order creation.
- The team wants to deploy weekly.

Constraints:

- One team owns all domains.
- Traffic is not proven.
- The team has limited operations capacity.

Questions:

- Would you start with a modular monolith or microservices?
- What module boundaries would you define?
- What data should not be shared casually?
- What future signal would justify extracting a service?

Hints:

- Microservices add network, deployment, and observability costs.
- A modular monolith still needs real boundaries.

Stretch challenge: design an anti-corruption layer for a legacy inventory system.

## Exercise 4: Consistency And Idempotency

Difficulty: Advanced

Concepts: consistency choices, idempotency, retries, circuit breakers, backpressure

Scenario: Customers sometimes double-click Submit Order. The payment boundary occasionally times out.

Requirements:

- Avoid duplicate orders.
- Avoid duplicate payment authorization.
- Return a clear status to the customer.

Constraints:

- The payment gateway is external.
- You cannot rely on the client to retry correctly.
- Some failures are ambiguous.

Questions:

- Where is idempotency required?
- Which retries are safe?
- When should a circuit breaker open?
- Where might backpressure be better than accepting more requests?

Hints:

- A timeout does not always mean the external action failed.
- Idempotency usually needs stored request keys and results.

Stretch challenge: propose a pending-payment state and explain its user experience.

## Exercise 5: Data Partitioning And Hot Keys

Difficulty: Advanced

Concepts: partitioning, replication, hot-key mitigation, read models, caching

Scenario: A learning platform stores quiz attempts. Most users are quiet, but exam week creates huge traffic for one course.

Requirements:

- Store attempts durably.
- Show recent attempts to learners.
- Show aggregate course progress to instructors.

Constraints:

- One course can dominate write traffic.
- Instructors can tolerate slightly stale aggregates.
- Learners expect their own recent attempt to appear quickly.

Questions:

- What partition key risks hot spots?
- Which reads can use a read model?
- Which data needs stronger consistency?
- How could caching help and where could it mislead users?

Hints:

- Time-based keys and course-only keys can create hot partitions.
- Read-your-writes may matter more than global freshness.

Stretch challenge: propose a strategy for rebalancing if one course remains hot.

## Exercise 6: Multi-Region Migration Strategy

Difficulty: Advanced

Concepts: multi-region reasoning, migration strategy, observability, cost-aware design, ADRs

Scenario: A backend currently runs in one region. Leadership asks whether it should become multi-region.

Requirements:

- Identify business reasons that could justify multi-region.
- Identify risks and costs.
- Propose a migration path if the decision is justified.

Constraints:

- Do not assume multi-region is always necessary.
- Data consistency requirements are not fully known.
- Operations capacity is limited.

Questions:

- What questions must be answered before choosing multi-region?
- Which data can be eventually consistent?
- Which operational signals must exist first?
- What ADR would you write?

Hints:

- Multi-region affects data, routing, incident response, and cost.
- Active-active is harder than disaster recovery readiness.

Stretch challenge: compare active-passive with active-active for this scenario.
