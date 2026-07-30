package dev.franklindot04.learnjava.reliability;

import java.util.concurrent.Callable;

public interface IdempotencyStore<T> { T executeOnce(String key, String fingerprint, Callable<T> work) throws Exception; }
