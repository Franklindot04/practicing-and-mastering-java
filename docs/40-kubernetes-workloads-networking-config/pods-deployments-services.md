# Pods, Deployments, And Services

## Pods

A Pod is the smallest deployable unit in Kubernetes. It usually wraps one application container, although it can contain more than one tightly coupled container.

For most Java backend learning examples, think of one Pod as one running copy of the backend application.

## ReplicaSets

A ReplicaSet keeps a chosen number of matching Pods running. Beginners usually interact with ReplicaSets indirectly through Deployments.

## Deployments

A Deployment manages replicated Pods and updates them over time. You describe:

- Which container image to run
- How many replicas should exist
- Which ports the container exposes
- Which environment variables are needed
- Which probes and resource settings apply

The Deployment controller works to keep the desired number of Pods available.

## Services

Pods can be replaced, so their IP addresses are not stable. A Service provides a stable name and virtual address for a set of Pods selected by labels.

Common Service types:

- `ClusterIP`: exposes the Service inside the cluster
- `NodePort`: exposes the Service on a port of each node, useful for some local learning scenarios
- `LoadBalancer`: asks the platform for an external load balancer; this is a concept only in this stage

## Ingress Concept

Ingress can route HTTP traffic from outside the cluster to Services, usually through an Ingress controller. This stage mentions Ingress conceptually only.

## Common Mistakes

- Expecting Pods to have permanent addresses
- Creating a Service whose selector does not match any Pod labels
- Using `LoadBalancer` in a local-only example and expecting a cloud load balancer
- Editing generated ReplicaSets directly instead of the Deployment
