# Networking, Domains, And HTTPS

Deployed applications need to be reachable safely.

## Ports

A backend listens on a port, such as `8080`. A platform may provide the port through an environment variable.

Your app should be able to read the configured port instead of assuming every environment uses the same value.

## Domains And DNS

A domain name points users to an application. DNS controls how names resolve to network locations.

Example:

```text
api.example.com -> deployed API endpoint
```

This repository does not configure real domains.

## TLS And HTTPS

HTTPS protects traffic between clients and servers. Real deployments should use HTTPS for public APIs.

Learning projects can understand the concept before configuring real certificates.

## Firewalls And Security Groups

Cloud platforms often control which network traffic can reach a service.

Basic idea:

- Allow only needed inbound ports.
- Restrict database access.
- Avoid exposing admin tools publicly.

Exact names differ by provider.
