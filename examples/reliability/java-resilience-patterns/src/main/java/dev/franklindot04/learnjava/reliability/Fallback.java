package dev.franklindot04.learnjava.reliability;

@FunctionalInterface
public interface Fallback<T> { T value(Throwable failure); }
