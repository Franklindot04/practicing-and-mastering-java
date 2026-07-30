package dev.franklindot04.learnjava.reliability;

import java.time.*;

public final class RequestBudget {
    private final Clock clock; private final Instant deadline;
    public RequestBudget(Clock clock, Duration budget) { if (budget.isNegative() || budget.isZero()) throw new IllegalArgumentException("budget must be positive"); this.clock = clock; this.deadline = clock.instant().plus(budget); }
    public boolean isExpired() { return !clock.instant().isBefore(deadline); }
    public Duration remaining() { return Duration.between(clock.instant(), deadline); }
}
