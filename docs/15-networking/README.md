# Networking

Java networking covers sockets, URLs, HTTP clients, and data exchange between programs.

Key topics:

- Client/server basics
- Sockets
- HTTP requests
- Timeouts
- Serialization formats

Common mistakes:

- Forgetting timeouts.
- Blocking forever on network reads.
- Trusting remote input.
- Mixing networking code directly into business logic.

Practice prompts:

- Write a simple socket echo server.
- Fetch a URL with Java's HTTP client.
- Add timeout handling.

Before moving on, you should understand that networks are unreliable and code must handle failure.

## Professional Practice

- [ ] Set timeouts on HTTP or socket calls.
- [ ] Keep networking code separate from business logic.
- [ ] Practice networking basics in [Professional Examples](../../examples/professional/).
- [ ] Complete networking prompts in [Professional Exercises](../../exercises/professional/).

Before backend APIs, you should be comfortable handling timeout, unavailable service, and invalid remote input cases.
