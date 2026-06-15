# Kubernetes Core Concepts

Kubernetes is built around a cluster: a group of machines that run containerized workloads and the components needed to manage them.

## Control Plane

The control plane is the management side of the cluster. It stores cluster state, accepts requests, schedules work, and coordinates changes.

Important beginner concepts:

- API server: the front door for Kubernetes requests
- Scheduler: chooses where new Pods should run
- Controllers: watch actual state and try to move it toward desired state

## Worker Nodes

Worker nodes run application workloads. A node can be a local virtual machine, a machine in a lab, or a cloud instance.

## kubelet

The kubelet is an agent on each worker node. It talks to the control plane and helps ensure containers for assigned Pods are running.

## Kubernetes Objects

Kubernetes stores desired state as objects. Common objects include:

- Namespace: a logical grouping boundary
- Pod: the smallest deployable workload unit
- Deployment: manages replicated Pods and rolling updates
- Service: gives Pods a stable network access point
- ConfigMap: stores non-secret configuration
- Secret: stores sensitive-looking values, but still requires careful handling

## Beginner Mental Model

You usually do not tell Kubernetes, "start this container once." You tell Kubernetes, "this is the desired application shape." Kubernetes then works to maintain that shape.
