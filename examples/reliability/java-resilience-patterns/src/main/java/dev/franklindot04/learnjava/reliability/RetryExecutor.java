package dev.franklindot04.learnjava.reliability;

import java.time.*;import java.util.concurrent.Callable;

public final class RetryExecutor {
    private final RetryPolicy policy; private final FailureClassifier classifier; private final ExponentialBackoff backoff; private final Sleeper sleeper; private final Clock clock;
    public RetryExecutor(RetryPolicy policy, FailureClassifier classifier, ExponentialBackoff backoff, Sleeper sleeper, Clock clock) { this.policy=policy; this.classifier=classifier; this.backoff=backoff; this.sleeper=sleeper; this.clock=clock; }
    public <T> T execute(Callable<T> work) throws Exception {
        Instant start = clock.instant(); Throwable original = null;
        for (int attempt = 1; attempt <= policy.maxAttempts(); attempt++) {
            if (Duration.between(start, clock.instant()).compareTo(policy.maxElapsed()) >= 0) break;
            try { return work.call(); } catch (Throwable failure) {
                if (original == null) original = failure;
                if (!classifier.isRetryable(failure) || attempt == policy.maxAttempts()) { if (failure instanceof Exception e) throw e; throw new RuntimeException(failure); }
                Duration delay = backoff.delayForAttempt(attempt + 1);
                if (Duration.between(start, clock.instant()).plus(delay).compareTo(policy.maxElapsed()) > 0) break;
                try { sleeper.sleep(delay.toMillis()); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); throw ex; }
            }
        }
        if (original instanceof Exception e) throw e; throw new RuntimeException(original);
    }
}
