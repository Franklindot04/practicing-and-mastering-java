package dev.franklindot04.learnjava.reliability;

@FunctionalInterface
public interface FailureClassifier { boolean isRetryable(Throwable failure); }
