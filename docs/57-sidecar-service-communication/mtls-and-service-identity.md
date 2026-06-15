# mTLS And Service Identity

Mutual TLS, often shortened to mTLS, means both sides of a connection prove identity during encrypted communication.

In service mesh discussions, mTLS is usually tied to workload identity. The mesh can help ensure that one service is talking to the expected service, not merely to an IP address.

## Why Identity Matters

Internal networks are not automatically trustworthy. Service identity helps policies answer questions like:

- Which workload is calling?
- Which workload is being called?
- Is this caller allowed to reach this destination?
- Is the connection encrypted?

## What The Mesh May Handle

A mesh may automate certificate issuance, rotation, and policy distribution. Those details are production-sensitive and should not be copied into a learning repository.

## What The Application Still Handles

mTLS can prove workload identity, but it does not replace user authentication, domain authorization, input validation, or audit-friendly business decisions.

## Safe Learning Boundary

Use conceptual placeholders such as `task-api` and `notification-service`. Do not commit real certificates, private keys, trust bundles, signing material, or cluster identity files.

