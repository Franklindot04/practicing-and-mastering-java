# Traffic Flow For The Task API

Simple flow:

```text
Client -> DNS -> Load Balancer -> Task API Instances -> Managed Database
```

## Request Path

The client sends a request to an entry point. DNS and load balancing route the request to a healthy task API instance. The API validates input, applies business rules, and reads or writes task data.

## Review Questions

- Are routes public or private?
- Are health checks separate from business endpoints?
- Can any healthy instance handle the request?
- What happens when one instance is removed during deployment?
- How are database connections limited?

