package dev.franklindot04.learnjava.systemdesign;

import java.util.function.Supplier;

public final class FallbackService<T> {
    public T call(Supplier<T> primary, Supplier<T> fallback) {
        try {
            return primary.get();
        } catch (RuntimeException ex) {
            return fallback.get();
        }
    }
}
