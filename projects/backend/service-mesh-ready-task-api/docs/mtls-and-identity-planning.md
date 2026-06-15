# mTLS And Identity Planning

mTLS and workload identity can protect service-to-service communication, but they do not replace application security.

## Workload Identity

Conceptual workload identities might be:

- `task-api`
- `notification-service`
- `audit-service`

These names are placeholders, not cluster identities or real certificates.

## Policy Questions

- Which workloads may call `task-api`?
- Which workloads may receive calls from `task-api`?
- Which calls need user authorization inside the application?
- How are policy changes reviewed?
- How are certificate rotation and trust changes monitored?

## Boundary

Never commit real certificates, private keys, trust bundles, kubeconfig files, service account tokens, or mesh identity exports.

