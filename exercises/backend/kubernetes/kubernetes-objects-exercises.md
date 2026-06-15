# Kubernetes Objects Exercises

## Exercise 1: Identify The Object

Difficulty: Beginner

Concepts practiced: Pod, Deployment, Service, ConfigMap, Secret

Problem statement: For each need, name the Kubernetes object that fits best:

- Run replicated copies of a Java API
- Provide a stable in-cluster network name
- Store non-secret runtime settings
- Store placeholder sensitive-looking values for a demo
- Represent one running workload unit

Hints:
- A Deployment manages Pods.
- A Service does not run code.

Stretch challenge: Explain why learners usually edit a Deployment instead of a ReplicaSet.

## Exercise 2: Service Selector Match

Difficulty: Beginner

Concepts practiced: labels, selectors, Services

Problem statement: A Deployment creates Pods with label `app: task-api`, but the Service selector is `app: tasks-api`. Explain the problem and the fix.

Hints:
- The Service finds Pods by matching labels.
- One extra letter can break routing.

Stretch challenge: Add a second useful label that does not affect Service selection.

## Exercise 3: Namespace Cleanup

Difficulty: Beginner

Concepts practiced: namespaces, cleanup

Problem statement: Explain why putting all local learning resources in `learnjava-kubernetes` makes cleanup easier.

Hints:
- Think about object grouping.
- Think about avoiding accidental deletion of unrelated resources.

Stretch challenge: Write the command shape for deleting that namespace.
