# Saga Pattern

A saga is a long-running workflow made of multiple local transactions. If one step fails, the system runs compensation actions for completed steps.

## Example

```text
1. Create order
2. Reserve inventory
3. Request payment
4. Confirm order
```

If payment fails after inventory was reserved, the compensation may release the inventory and mark the order as payment failed.

```text
Create order -> Reserve inventory -> Payment fails
       |              |
       v              v
 Mark failed <- Release inventory
```

## Compensation Is Not Always Undo

Compensation is a business action that repairs or balances the workflow. It may not perfectly undo reality.

Examples:

- Refund a payment rather than pretend it never happened.
- Send a cancellation notice rather than erase a message.
- Release a reservation rather than delete the original record.

## Orchestration And Choreography

Orchestration uses a central workflow coordinator that tells each participant what to do next.

Choreography lets services react to events from each other.

Both styles require clear ownership, observability, and failure handling.

## Saga Checklist

- What are the steps?
- Which step owns each local transaction?
- What compensation exists for each completed step?
- Which failures are retryable?
- Which failures require human review?
- How can an operator see the current workflow state?

