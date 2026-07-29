package dev.franklindot04.learnjava.reliability;

import java.time.*;import java.util.concurrent.Callable;

public final class CircuitBreaker {
 private final int threshold; private final Duration openDuration; private final Clock clock; private CircuitBreakerState state=CircuitBreakerState.CLOSED; private int failures; private Instant openedAt=Instant.MIN; private boolean probe;
 public CircuitBreaker(int threshold, Duration openDuration, Clock clock){if(threshold<1)throw new IllegalArgumentException("threshold must be positive");this.threshold=threshold;this.openDuration=openDuration;this.clock=clock;}
 public synchronized CircuitBreakerSnapshot snapshot(){transitionIfReady();return new CircuitBreakerSnapshot(state,failures,probe?1:0);} private void transitionIfReady(){if(state==CircuitBreakerState.OPEN&&!clock.instant().isBefore(openedAt.plus(openDuration)))state=CircuitBreakerState.HALF_OPEN;}
 public <T> T call(Callable<T> work) throws Exception { synchronized(this){transitionIfReady(); if(state==CircuitBreakerState.OPEN) throw new IllegalStateException("circuit breaker is open"); if(state==CircuitBreakerState.HALF_OPEN){ if(probe) throw new IllegalStateException("half-open probe already running"); probe=true; }} try { T value=work.call(); synchronized(this){failures=0; probe=false; state=CircuitBreakerState.CLOSED;} return value; } catch(Exception ex){ synchronized(this){probe=false; failures++; if(state==CircuitBreakerState.HALF_OPEN||failures>=threshold){state=CircuitBreakerState.OPEN; openedAt=clock.instant();}} throw ex; }}
}
