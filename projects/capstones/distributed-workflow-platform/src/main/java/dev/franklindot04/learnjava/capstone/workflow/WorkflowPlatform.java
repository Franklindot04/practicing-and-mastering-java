package dev.franklindot04.learnjava.capstone.workflow;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public final class WorkflowPlatform {
  private final Clock clock;
  private final Supplier<String> ids;
  private final Map<String, Workflow> workflows = new HashMap<>();
  private final Map<String, Task> tasks = new HashMap<>();
  private final Map<String, Worker> workers = new HashMap<>();
  private final Map<String, Lease> leases = new HashMap<>();
  private final Map<String, String> idempotency = new HashMap<>();
  private final Set<String> sideEffects = new HashSet<>();
  private final List<StateRecord> log = new ArrayList<>();
  private final List<Task> quarantine = new ArrayList<>();
  private final Metrics metrics = new Metrics();
  private int admissionCapacity = 100;

  public WorkflowPlatform(Clock clock, Supplier<String> ids) {
    this.clock = Objects.requireNonNull(clock);
    this.ids = Objects.requireNonNull(ids);
  }

  public static WorkflowPlatform local() {
    return new WorkflowPlatform(Clock.systemUTC(), () -> UUID.randomUUID().toString());
  }

  public Workflow submitWorkflow(String name, String idempotencyKey, List<TaskDefinition> definitions) {
    requireText(name, "name");
    requireText(idempotencyKey, "idempotencyKey");
    if (idempotency.containsKey(idempotencyKey)) {
      metrics.duplicates++;
      return workflows.get(idempotency.get(idempotencyKey));
    }
    if (admissionCapacity <= 0) {
      metrics.rejected++;
      Workflow rejected = new Workflow("rejected-" + ids.get(), name, WorkflowState.REJECTED);
      log.add(new StateRecord("admission-rejected", rejected.id(), now()));
      return rejected;
    }
    admissionCapacity--;
    String workflowId = "wf-" + ids.get();
    Workflow workflow = new Workflow(workflowId, name, WorkflowState.RUNNING);
    workflows.put(workflowId, workflow);
    idempotency.put(idempotencyKey, workflowId);
    for (TaskDefinition definition : definitions) {
      Task task = new Task("task-" + ids.get(), workflowId, definition.name(), definition.partitionKey(),
          definition.dependencies(), TaskState.READY, 0, definition.irreversible(), definition.poison());
      tasks.put(task.id(), task);
    }
    log.add(new StateRecord("workflow-submitted", workflowId, now()));
    return workflow;
  }

  public Task submitTask(String name, String idempotencyKey) {
    Workflow workflow = submitWorkflow(name + "-workflow", idempotencyKey,
        List.of(new TaskDefinition(name, "default", List.of(), false, false)));
    return tasks.values().stream().filter(task -> task.workflowId().equals(workflow.id())).findFirst().orElseThrow();
  }

  public Worker registerWorker(String id, int capacity, String partition) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be positive");
    }
    Worker worker = new Worker(id, capacity, partition, now(), true);
    workers.put(id, worker);
    log.add(new StateRecord("worker-registered", id, now()));
    return worker;
  }

  public Optional<Lease> leaseNext(String workerId, Duration ttl) {
    Worker worker = workers.get(workerId);
    if (worker == null || !worker.active()) {
      return Optional.empty();
    }
    Optional<Task> next = tasks.values().stream()
        .filter(task -> task.state() == TaskState.READY)
        .filter(task -> dependenciesComplete(task.workflowId(), task.dependencies()))
        .filter(task -> worker.partition().equals(task.partitionKey()))
        .min(Comparator.comparing(Task::id));
    if (next.isEmpty()) {
      return Optional.empty();
    }
    Task task = next.get().withState(TaskState.LEASED);
    tasks.put(task.id(), task);
    Lease lease = new Lease("lease-" + ids.get(), task.id(), workerId, now().plus(ttl));
    leases.put(task.id(), lease);
    log.add(new StateRecord("task-leased", task.id(), now()));
    return Optional.of(lease);
  }

  public CompletionResult complete(String taskId, String workerId, String completionKey) {
    Lease lease = leases.get(taskId);
    Task task = tasks.get(taskId);
    if (task == null) {
      return CompletionResult.rejected("missing task");
    }
    if (sideEffects.contains(completionKey)) {
      metrics.duplicates++;
      return CompletionResult.duplicate();
    }
    if (lease == null || !lease.workerId().equals(workerId)) {
      return CompletionResult.rejected("invalid lease");
    }
    if (task.poison()) {
      Task quarantined = task.withState(TaskState.QUARANTINED);
      tasks.put(taskId, quarantined);
      quarantine.add(quarantined);
      metrics.deadLetters++;
      return CompletionResult.rejected("poison task");
    }
    sideEffects.add(completionKey);
    tasks.put(taskId, task.withState(TaskState.COMPLETED));
    leases.remove(taskId);
    updateWorkflowState(task.workflowId());
    log.add(new StateRecord("task-completed", taskId, now()));
    return CompletionResult.completed();
  }

  public void failTask(String taskId) {
    Task task = tasks.get(taskId);
    if (task == null) {
      return;
    }
    if (task.irreversible()) {
      tasks.put(taskId, task.withState(TaskState.QUARANTINED));
      quarantine.add(task);
      metrics.deadLetters++;
      log.add(new StateRecord("irreversible-quarantined", taskId, now()));
      return;
    }
    tasks.put(taskId, task.withState(TaskState.FAILED));
    compensate(task.workflowId());
  }

  public void expireLeases(Instant at) {
    List<String> expired = leases.values().stream().filter(lease -> !lease.expiresAt().isAfter(at)).map(Lease::taskId).toList();
    for (String taskId : expired) {
      Task task = tasks.get(taskId);
      if (task != null && task.state() == TaskState.LEASED) {
        tasks.put(taskId, task.incrementAttempt().withState(TaskState.READY));
        metrics.retries++;
      }
      leases.remove(taskId);
      log.add(new StateRecord("lease-expired", taskId, at));
    }
  }

  public ReplayReport replay() {
    WorkflowPlatform rebuilt = new WorkflowPlatform(clock, ids);
    rebuilt.log.addAll(log);
    return new ReplayReport(log.size(), sideEffects.size(), sideEffects.size());
  }

  public PartitionReport partitionReport() {
    Map<String, Long> counts = new HashMap<>();
    for (Task task : tasks.values()) {
      counts.merge(task.partitionKey(), 1L, Long::sum);
    }
    String hot = counts.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("none");
    return new PartitionReport(hot, counts.getOrDefault(hot, 0L), counts.getOrDefault(hot, 0L) > 2);
  }

  public OperationalReport report() {
    return new OperationalReport(workflows.size(), tasks.size(), leases.size(), metrics.retries,
        metrics.deadLetters, metrics.duplicates, metrics.rejected, quarantine.size());
  }

  public Backoff retryBackoff(Task task) {
    long seconds = Math.min(60, (long) Math.pow(2, Math.max(0, task.attempts())));
    return new Backoff(Duration.ofSeconds(seconds), task.attempts() + 1);
  }

  public void setAdmissionCapacity(int admissionCapacity) {
    this.admissionCapacity = admissionCapacity;
  }

  public Optional<Task> task(String id) {
    return Optional.ofNullable(tasks.get(id));
  }

  private boolean dependenciesComplete(String workflowId, List<String> dependencies) {
    return dependencies.stream().allMatch(name -> tasks.values().stream()
        .anyMatch(task -> task.workflowId().equals(workflowId) && task.name().equals(name) && task.state() == TaskState.COMPLETED));
  }

  private void updateWorkflowState(String workflowId) {
    boolean complete = tasks.values().stream().filter(task -> task.workflowId().equals(workflowId))
        .allMatch(task -> task.state() == TaskState.COMPLETED);
    if (complete) {
      workflows.put(workflowId, workflows.get(workflowId).withState(WorkflowState.COMPLETED));
    }
  }

  private void compensate(String workflowId) {
    tasks.values().stream().filter(task -> task.workflowId().equals(workflowId) && task.state() == TaskState.COMPLETED)
        .forEach(task -> tasks.put(task.id(), task.withState(TaskState.COMPENSATED)));
    workflows.put(workflowId, workflows.get(workflowId).withState(WorkflowState.FAILED));
    log.add(new StateRecord("workflow-compensated", workflowId, now()));
  }

  private Instant now() {
    return clock.instant();
  }

  private static void requireText(String value, String field) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(field + " is required");
    }
  }

  private static final class Metrics {
    private int retries;
    private int deadLetters;
    private int duplicates;
    private int rejected;
  }

  public record TaskDefinition(String name, String partitionKey, List<String> dependencies, boolean irreversible, boolean poison) {}
  public record Workflow(String id, String name, WorkflowState state) {
    Workflow withState(WorkflowState state) {
      return new Workflow(id, name, state);
    }
  }
  public enum WorkflowState { RUNNING, COMPLETED, FAILED, REJECTED }
  public record Task(String id, String workflowId, String name, String partitionKey, List<String> dependencies,
                     TaskState state, int attempts, boolean irreversible, boolean poison) {
    Task withState(TaskState state) {
      return new Task(id, workflowId, name, partitionKey, dependencies, state, attempts, irreversible, poison);
    }
    Task incrementAttempt() {
      return new Task(id, workflowId, name, partitionKey, dependencies, state, attempts + 1, irreversible, poison);
    }
  }
  public enum TaskState { READY, LEASED, COMPLETED, FAILED, COMPENSATED, QUARANTINED }
  public record Worker(String id, int capacity, String partition, Instant lastHeartbeat, boolean active) {}
  public record Lease(String id, String taskId, String workerId, Instant expiresAt) {}
  public record CompletionResult(String status, String reason) {
    static CompletionResult completed() {
      return new CompletionResult("completed", "ok");
    }
    static CompletionResult duplicate() {
      return new CompletionResult("duplicate", "already completed");
    }
    static CompletionResult rejected(String reason) {
      return new CompletionResult("rejected", reason);
    }
  }
  public record Backoff(Duration delay, int nextAttempt) {}
  public record StateRecord(String type, String subjectId, Instant at) {}
  public record PartitionReport(String hotPartition, long taskCount, boolean rebalanceRecommended) {}
  public record ReplayReport(int recordsRead, int originalSideEffects, int replayedSideEffects) {}
  public record OperationalReport(int workflows, int tasks, int leases, int retries, int deadLetters,
                                  int duplicates, int rejected, int quarantined) {}
}
