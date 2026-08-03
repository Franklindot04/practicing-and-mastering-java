# Scheduling Model

Scheduling is deterministic: the platform selects the lowest task id that is ready, whose dependencies are complete, and whose partition matches the worker. This makes tests repeatable and keeps scheduling behavior explainable.
