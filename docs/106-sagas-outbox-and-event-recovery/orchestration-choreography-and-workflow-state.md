# Orchestration Choreography And Workflow State

Sagas can be coordinated through orchestration, choreography, or a mix of both.

## Orchestration

Orchestration uses a central coordinator to decide the next step. The coordinator tracks workflow state and sends commands or publishes events.

Benefits:

- clear workflow visibility
- central place for timeouts and recovery
- easier operational reporting
- simpler reasoning about invalid transitions

Costs:

- coordinator becomes an important dependency
- participants may become passive
- workflow changes concentrate in one component

## Choreography

Choreography lets participants react to events and publish the next fact. Inventory reacts to `OrderSubmitted`, payment reacts to `InventoryReserved`, and notification reacts to `OrderConfirmed`.

Benefits:

- participant autonomy
- fewer central workflow dependencies
- natural event fan-out

Costs:

- hidden dependencies are easier to create
- cyclic event chains can appear
- workflow state is harder to inspect
- recovery ownership can be unclear

## Coupling Trade-Offs

Orchestration couples participants to a coordinator. Choreography couples participants through event contracts and implicit business order. Neither is automatically better.

Use orchestration when workflow visibility, timeouts, and explicit recovery are central. Use choreography when reactions are independent and no component needs to own the whole sequence.

## Workflow State

Workflow state should record:

- current status
- completed steps
- expected next event
- last applied aggregate version
- retries and deadlines
- compensation state
- failure reason
- correlation ID

Without state, a system may continue publishing events while no one can answer "where is this order stuck?"

## Hidden Dependencies And Cycles

A cyclic chain occurs when one event causes another that eventually causes the first again. Cycles can be valid, but accidental cycles cause duplicate work, retry amplification, and confusing traces.

Every event reaction should have an owner, an idempotency strategy, and a clear stopping condition.
