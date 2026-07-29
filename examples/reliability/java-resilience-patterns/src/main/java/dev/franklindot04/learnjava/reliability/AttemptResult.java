package dev.franklindot04.learnjava.reliability;

public record AttemptResult<T>(int attempts, T value, Throwable failure) {}
