# Service-To-Service Communication

In a sidecar mesh, a request from one service to another usually passes through two proxies.

## Simple Flow

1. Service A sends a request to Service B.
2. Service A's local proxy receives or intercepts the outbound request.
3. The proxy applies outbound policy, such as timeout or route selection.
4. The request travels across the network to Service B's proxy.
5. Service B's proxy applies inbound policy.
6. Service B receives the request.
7. The response returns through the same proxy path.

## What Changes For Developers

Developers may still write ordinary HTTP or gRPC client code, but the runtime path now includes mesh infrastructure. When debugging, they must consider application logs and proxy behavior.

## Important Questions

- What timeout does the application set?
- What timeout does the mesh set?
- Which retries happen, and where?
- How is service identity verified?
- Which metrics identify the source, destination, route, and result?

## Common Mistake

Do not configure hidden retries without understanding idempotency. Retrying a request that creates or charges something can duplicate work unless the API is designed for it.

