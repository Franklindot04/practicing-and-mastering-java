# Modular Monoliths And Microservices

The monolith-versus-microservices question is about boundaries, ownership, deployment, and operations. It is not about prestige.

## Modular Monolith

A modular monolith is one deployable application with strong internal module boundaries.

```text
+-----------------------------+
| Backend Application          |
|                             |
| Orders | Inventory | Billing |
+-----------------------------+
          |
          v
      Shared runtime
```

Benefits:

- One deployment unit.
- Easier local transactions.
- Simpler debugging.
- Lower operational overhead.
- Good fit for one team or early product discovery.

Risks:

- Boundaries require discipline.
- Modules can accidentally share internals.
- Independent scaling is limited.
- Build and deploy time can grow.

## Microservices

Microservices split capabilities into independently deployable services.

```text
Orders API ---> Payment Service
    |
    +-------> Inventory Service
```

Benefits:

- Independent deployment when teams and domains are stable.
- Independent scaling for specific workloads.
- Clear ownership can improve team autonomy.
- Different data stores can fit different domains.

Risks:

- Network failure becomes normal.
- Distributed tracing and monitoring are required.
- Data consistency is harder.
- Deployments need compatibility discipline.
- Local development and testing can become expensive.

## Bounded Contexts And Domain Ownership

A bounded context is a boundary where a model has a specific meaning.

Example:

- "Order status" in checkout may mean customer-facing progress.
- "Payment status" in billing may mean authorization, capture, refund, or dispute state.

Do not force one model to mean everything everywhere.

## Shared Libraries

Shared libraries can reduce duplication, but they can also couple services.

Good candidates:

- Stable utility code.
- Shared test helpers.
- Common observability conventions.

Risky candidates:

- Domain models that change often.
- Persistence entities.
- Internal service clients that hide network behavior.

## Shared Database Versus Database Per Service

| Choice | Helps With | Risks |
| --- | --- | --- |
| Shared database | Simpler reporting and local joins | Blurred ownership, unsafe cross-service writes |
| Database per service | Clear ownership and independent schema | Integration and consistency complexity |

Database per service is useful only when the team accepts the operational and consistency tradeoffs.

## When Microservices Are Unnecessary

Microservices may be unnecessary when:

- One small team owns the whole product.
- Traffic does not require independent scaling.
- Domain boundaries are not yet stable.
- Deployment frequency is low.
- Strong local transactions are central.
- Observability and operations maturity are still developing.
