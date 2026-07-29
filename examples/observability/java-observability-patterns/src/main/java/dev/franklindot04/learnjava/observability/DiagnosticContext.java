package dev.franklindot04.learnjava.observability;

import java.util.Optional;

public final class DiagnosticContext {
    private static final ThreadLocal<RequestContext> CURRENT = new ThreadLocal<>();

    private DiagnosticContext() {
    }

    public static Optional<RequestContext> current() {
        return Optional.ofNullable(CURRENT.get());
    }

    public static Scope with(RequestContext context) {
        RequestContext previous = CURRENT.get();
        CURRENT.set(context);
        return new Scope(previous);
    }

    public static void clear() {
        CURRENT.remove();
    }

    public static final class Scope implements AutoCloseable {
        private final RequestContext previous;
        private boolean closed;

        private Scope(RequestContext previous) {
            this.previous = previous;
        }

        @Override
        public void close() {
            if (closed) {
                return;
            }
            if (previous == null) {
                CURRENT.remove();
            } else {
                CURRENT.set(previous);
            }
            closed = true;
        }
    }
}
