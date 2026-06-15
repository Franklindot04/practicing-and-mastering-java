# Orchestration Basics Solutions

## Exercise 1

Running one container manually starts a process. Orchestration manages the desired number of containers, chooses where they run, replaces failures, connects networking, and supports updates. The image is the package; orchestration is the operating model.

## Exercise 2

Kubernetes should try to create or schedule another Pod so actual state moves toward three replicas. If it cannot, inspect Pods, the Deployment, and namespace events. Common reasons include insufficient resources, image pull failure, or scheduling constraints.

## Exercise 3

A safe checklist should include using a disposable local cluster, using a learning namespace, reviewing manifests before applying them, never using real credentials, never committing kubeconfig files, and deleting the namespace when finished.
