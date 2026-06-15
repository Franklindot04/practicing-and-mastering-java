# Local Learning Clusters

A local learning cluster lets you practice Kubernetes on your own machine. This repository does not create a cluster for you, but it explains common options.

## kind

`kind` runs Kubernetes nodes as local containers. It is useful for quick experiments and disposable clusters.

## minikube

`minikube` runs a local Kubernetes cluster using a virtual machine or container runtime, depending on your setup.

## Docker Desktop Kubernetes

Docker Desktop can enable a local Kubernetes cluster. It is convenient when Docker Desktop is already part of your development setup.

## Local-Only Safety Notes

- Do not connect these examples to a real cloud cluster while learning the basics
- Do not use real credentials in demo manifests
- Do not commit kubeconfig files
- Delete local resources when you are done experimenting
- Remember that local cluster behavior is not the same as production operations

## What To Learn First

Before applying YAML anywhere, practice reading manifests:

- What object is this?
- What namespace does it use?
- What labels does it define?
- What image would it run?
- What config and secret values does it expect?
- How would it be cleaned up?
