package dev.franklindot04.learnjava.reliableservice;

import java.time.*;import java.util.*;

record CheckoutRequest(String idempotencyKey, String fingerprint, String sku, int quantity, int cents) {}
enum CheckoutStatus { SUCCESS, DEGRADED, REJECTED, FAILED }
record CheckoutResponse(CheckoutStatus status, String message, List<String> diagnostics) {}
record RequestContext(Clock clock, Instant deadline) { boolean expired(){return !clock.instant().isBefore(deadline);} }
enum FailureType { NONE, TRANSIENT, FATAL, TIMEOUT }
final class PlannedFailure extends Exception { final FailureType type; PlannedFailure(FailureType type){super(type.name()); this.type=type;} boolean retryable(){return type==FailureType.TRANSIENT||type==FailureType.TIMEOUT;} }
final class FailurePlan { private final Deque<FailureType> failures=new ArrayDeque<>(); FailurePlan(FailureType... types){failures.addAll(Arrays.asList(types));} void maybeFail() throws PlannedFailure { FailureType next=failures.isEmpty()?FailureType.NONE:failures.removeFirst(); if(next!=FailureType.NONE) throw new PlannedFailure(next); } }
final class ReliabilityMetrics { int accepted; int rejected; int degraded; int paymentCharges; int inventoryReservations; }
final class RecoveryJournal { private final List<String> events=new ArrayList<>(); void record(String event){events.add(event);} List<String> events(){return List.copyOf(events);} }
