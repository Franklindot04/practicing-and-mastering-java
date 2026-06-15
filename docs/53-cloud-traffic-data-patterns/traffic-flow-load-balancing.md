# Traffic Flow And Load Balancing

Simple request flow often looks like:

```text
Client -> DNS -> Load Balancer -> Backend Service -> Managed Database
```

## DNS

DNS maps a name to a destination. In real systems, DNS can point traffic toward load balancers, gateways, or provider-managed endpoints.

## Load Balancing

A load balancer distributes requests across healthy backend instances. It can improve availability and capacity when the application can run multiple instances safely.

Load balancing does not fix application bugs, database bottlenecks, or bad retry behavior.

## Reverse Proxy

A reverse proxy receives client traffic and forwards it to backend services. It can centralize TLS termination, routing, compression, or header handling depending on the platform.

## API Gateway

An API gateway is a more feature-rich entry point for APIs. It may provide routing, authentication integration, rate limiting, request shaping, or analytics.

Do not add a gateway only because it sounds advanced. Add it when its responsibilities are clear.

