package dev.franklindot04.learnjava.reliability;

@FunctionalInterface
public interface Sleeper { void sleep(long millis) throws InterruptedException; }
