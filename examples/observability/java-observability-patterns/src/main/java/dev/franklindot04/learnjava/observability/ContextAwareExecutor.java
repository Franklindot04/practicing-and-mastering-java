package dev.franklindot04.learnjava.observability;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public final class ContextAwareExecutor implements AutoCloseable {
    private final ExecutorService delegate;

    public ContextAwareExecutor(ExecutorService delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    public <T> Future<T> submit(RequestContext context, Callable<T> task) {
        return delegate.submit(() -> {
            try (DiagnosticContext.Scope ignored = DiagnosticContext.with(context)) {
                return task.call();
            }
        });
    }

    @Override
    public void close() {
        delegate.shutdownNow();
    }
}
