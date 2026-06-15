# Mesh Security, mTLS, And Identity

Mesh security often starts with workload identity and mutual TLS. Workload identity gives a service instance an identity that can be used in policy. mTLS encrypts the connection and lets both sides authenticate each other.

## Useful Security Questions

- Which workload is calling?
- Which workload is being called?
- Is the connection encrypted?
- Is this source allowed to talk to this destination?
- Can policy changes be reviewed and rolled back?

## What A Mesh May Provide

A mesh may provide certificate automation, service identity, encrypted transport, and authorization policy for service-to-service communication.

## Keep The Boundary Clear

Workload identity is not the same as end-user identity. A mesh can identify `task-api` as a workload, but application code still needs to authenticate users, authorize actions, validate input, and protect sensitive data.

## Repository Safety

Do not commit real certificates, private keys, service account tokens, cluster trust bundles, kubeconfig files, provider credentials, or production policy exports.

