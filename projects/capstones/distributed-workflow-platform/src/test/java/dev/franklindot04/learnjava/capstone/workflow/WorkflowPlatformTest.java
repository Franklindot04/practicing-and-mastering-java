package dev.franklindot04.learnjava.capstone.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class WorkflowPlatformTest {
  private final AtomicInteger ids = new AtomicInteger();
  private final WorkflowPlatform platform = new WorkflowPlatform(
      Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC),
      () -> "id-" + ids.incrementAndGet());

  @Test
  void completesWorkflowWithDependenciesAndIdempotentSubmission() {
    WorkflowPlatform.Workflow workflow = platform.submitWorkflow("ship-order", "idem-1", List.of(
        new WorkflowPlatform.TaskDefinition("reserve", "p1", List.of(), false, false),
        new WorkflowPlatform.TaskDefinition("charge", "p1", List.of("reserve"), false, false)));
    WorkflowPlatform.Workflow duplicate = platform.submitWorkflow("ship-order", "idem-1", List.of());
    platform.registerWorker("worker-a", 2, "p1");

    WorkflowPlatform.Lease first = platform.leaseNext("worker-a", Duration.ofSeconds(30)).orElseThrow();
    assertEquals("completed", platform.complete(first.taskId(), "worker-a", "effect-1").status());
    WorkflowPlatform.Lease second = platform.leaseNext("worker-a", Duration.ofSeconds(30)).orElseThrow();
    assertEquals("completed", platform.complete(second.taskId(), "worker-a", "effect-2").status());

    assertEquals(workflow.id(), duplicate.id());
    assertTrue(platform.report().duplicates() >= 1);
  }

  @Test
  void expiresLeaseAndSafelyReassignsTaskAfterWorkerFailure() {
    WorkflowPlatform.Task task = platform.submitTask("email", "idem-2");
    platform.registerWorker("worker-a", 1, "default");
    platform.registerWorker("worker-b", 1, "default");
    platform.leaseNext("worker-a", Duration.ofSeconds(1)).orElseThrow();

    platform.expireLeases(Instant.parse("2026-01-01T00:00:02Z"));
    WorkflowPlatform.Lease reassigned = platform.leaseNext("worker-b", Duration.ofSeconds(30)).orElseThrow();

    assertEquals(task.id(), reassigned.taskId());
    assertEquals(1, platform.report().retries());
  }

  @Test
  void rejectsDuplicateCompletionAndInvalidLease() {
    WorkflowPlatform.Task task = platform.submitTask("archive", "idem-3");
    platform.registerWorker("worker-a", 1, "default");
    WorkflowPlatform.Lease lease = platform.leaseNext("worker-a", Duration.ofSeconds(30)).orElseThrow();

    assertEquals("completed", platform.complete(lease.taskId(), "worker-a", "effect-archive").status());
    assertEquals("duplicate", platform.complete(lease.taskId(), "worker-a", "effect-archive").status());
    assertEquals("rejected", platform.complete(task.id(), "worker-b", "effect-other").status());
  }

  @Test
  void quarantinesPoisonTasksAndIrreversibleFailures() {
    platform.submitWorkflow("unsafe", "idem-4", List.of(
        new WorkflowPlatform.TaskDefinition("poison", "p1", List.of(), false, true),
        new WorkflowPlatform.TaskDefinition("irreversible", "p1", List.of(), true, false)));
    platform.registerWorker("worker-a", 1, "p1");
    WorkflowPlatform.Lease poison = platform.leaseNext("worker-a", Duration.ofSeconds(30)).orElseThrow();

    assertEquals("poison task", platform.complete(poison.taskId(), "worker-a", "effect-poison").reason());
    platform.task(poison.taskId()).ifPresent(task -> assertEquals(WorkflowPlatform.TaskState.QUARANTINED, task.state()));
    platform.report();
  }

  @Test
  void dependencyFailureCompensatesCompletedWork() {
    WorkflowPlatform.Workflow workflow = platform.submitWorkflow("dependent", "idem-5", List.of(
        new WorkflowPlatform.TaskDefinition("first", "p1", List.of(), false, false),
        new WorkflowPlatform.TaskDefinition("second", "p1", List.of("first"), false, false)));
    platform.registerWorker("worker-a", 1, "p1");
    WorkflowPlatform.Lease first = platform.leaseNext("worker-a", Duration.ofSeconds(30)).orElseThrow();
    platform.complete(first.taskId(), "worker-a", "effect-first");
    WorkflowPlatform.Lease second = platform.leaseNext("worker-a", Duration.ofSeconds(30)).orElseThrow();

    platform.failTask(second.taskId());

    assertEquals(1, platform.report().tasks() - platform.report().leases());
    assertEquals("wf-id-1", workflow.id());
  }

  @Test
  void detectsHotPartitionAndAdmissionPressure() {
    platform.submitWorkflow("hot", "idem-6", List.of(
        new WorkflowPlatform.TaskDefinition("a", "hot", List.of(), false, false),
        new WorkflowPlatform.TaskDefinition("b", "hot", List.of(), false, false),
        new WorkflowPlatform.TaskDefinition("c", "hot", List.of(), false, false)));
    platform.setAdmissionCapacity(0);
    WorkflowPlatform.Workflow rejected = platform.submitWorkflow("too-much", "idem-7", List.of());

    assertTrue(platform.partitionReport().rebalanceRecommended());
    assertEquals(WorkflowPlatform.WorkflowState.REJECTED, rejected.state());
    assertEquals(1, platform.report().rejected());
  }

  @Test
  void representsRetryBackoffAndReplayWithoutExtraSideEffects() {
    WorkflowPlatform.Task task = platform.submitTask("billing", "idem-8");
    platform.registerWorker("worker-a", 1, "default");
    WorkflowPlatform.Lease lease = platform.leaseNext("worker-a", Duration.ofSeconds(1)).orElseThrow();
    platform.expireLeases(Instant.parse("2026-01-01T00:00:02Z"));
    WorkflowPlatform.Task retried = platform.task(lease.taskId()).orElseThrow();

    assertEquals(Duration.ofSeconds(2), platform.retryBackoff(retried).delay());
    WorkflowPlatform.ReplayReport replay = platform.replay();
    assertEquals(replay.originalSideEffects(), replay.replayedSideEffects());
    assertFalse(platform.task(task.id()).isEmpty());
  }
}
