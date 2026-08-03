package dev.franklindot04.learnjava.capstone.diagnostics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public final class DiagnosticsWorkbench {
  public static final int MAX_ITERATIONS = 50_000;
  public static final int MAX_ALLOCATION_BYTES = 2_000_000;
  public static final int MAX_THREADS = 8;

  public WorkloadReport cpuHeavy(WorkloadConfig config) {
    config.validate();
    long value = 0;
    for (int i = 0; i < config.iterations(); i++) {
      value += (long) i * i % 97;
    }
    return new WorkloadReport("cpu-heavy", config.iterations(), value, true);
  }

  public WorkloadReport allocationHeavy(WorkloadConfig config) {
    config.validate();
    List<byte[]> allocations = new ArrayList<>();
    int remaining = config.allocationBytes();
    while (remaining > 0) {
      int size = Math.min(1024, remaining);
      allocations.add(new byte[size]);
      remaining -= size;
    }
    return new WorkloadReport("allocation-heavy", allocations.stream().mapToInt(bytes -> bytes.length).sum(), allocations.size(), true);
  }

  public WorkloadReport retainedObjects(WorkloadConfig config) {
    config.validate();
    Map<Integer, byte[]> retained = new HashMap<>();
    for (int i = 0; i < config.iterations() / 100; i++) {
      retained.put(i, new byte[Math.min(512, Math.max(1, config.allocationBytes() / 100))]);
    }
    return new WorkloadReport("retained-objects", retained.size(), retained.values().stream().mapToInt(bytes -> bytes.length).sum(), true);
  }

  public WorkloadReport lockContention(WorkloadConfig config) {
    config.validate();
    ReentrantLock lock = new ReentrantLock();
    ExecutorService pool = Executors.newFixedThreadPool(Math.min(config.threads(), MAX_THREADS));
    try {
      var futures = IntStream.range(0, Math.min(config.threads(), MAX_THREADS))
          .mapToObj(i -> pool.submit(() -> {
            for (int j = 0; j < config.iterations() / 100; j++) {
              lock.lock();
              try {
                Math.sqrt(j);
              } finally {
                lock.unlock();
              }
            }
          })).toList();
      for (Future<?> future : futures) {
        future.get(2, TimeUnit.SECONDS);
      }
      return new WorkloadReport("lock-contention", futures.size(), config.iterations(), true);
    } catch (Exception exception) {
      return new WorkloadReport("lock-contention", 0, 0, false);
    } finally {
      pool.shutdownNow();
    }
  }

  public WorkloadReport blockedThread(WorkloadConfig config) {
    config.validate();
    Object monitor = new Object();
    Thread thread = new Thread(() -> {
      synchronized (monitor) {
        try {
          monitor.wait(25);
        } catch (InterruptedException exception) {
          Thread.currentThread().interrupt();
        }
      }
    }, "bounded-blocked-thread");
    thread.start();
    try {
      thread.join(250);
      return new WorkloadReport("blocked-thread", 1, thread.isAlive() ? 0 : 1, !thread.isAlive());
    } catch (InterruptedException exception) {
      Thread.currentThread().interrupt();
      return new WorkloadReport("blocked-thread", 1, 0, false);
    }
  }

  public WorkloadReport threadPoolSaturation(WorkloadConfig config) {
    config.validate();
    ExecutorService pool = Executors.newFixedThreadPool(Math.min(config.threads(), MAX_THREADS));
    try {
      List<Future<Integer>> futures = IntStream.range(0, Math.min(config.iterations() / 100, 50))
          .mapToObj(i -> pool.submit(() -> i)).toList();
      int completed = 0;
      for (Future<Integer> future : futures) {
        future.get(1, TimeUnit.SECONDS);
        completed++;
      }
      return new WorkloadReport("thread-pool-saturation", futures.size(), completed, true);
    } catch (Exception exception) {
      return new WorkloadReport("thread-pool-saturation", 0, 0, false);
    } finally {
      pool.shutdownNow();
    }
  }

  public WorkloadReport cachePressure(WorkloadConfig config) {
    config.validate();
    Map<Integer, String> cache = new HashMap<>();
    for (int i = 0; i < config.iterations(); i++) {
      cache.put(i % 128, "value-" + i);
    }
    return new WorkloadReport("cache-pressure", cache.size(), config.iterations(), true);
  }

  public LatencyReport latencyDistribution(List<Long> latenciesMillis) {
    if (latenciesMillis.isEmpty()) {
      throw new IllegalArgumentException("latencies are required");
    }
    List<Long> sorted = latenciesMillis.stream().sorted().toList();
    return new LatencyReport(percentile(sorted, 50), percentile(sorted, 95), percentile(sorted, 99), sorted.get(sorted.size() - 1));
  }

  public RegressionReport regressionBudget(long baselineP95Millis, long candidateP95Millis, double allowedRatio) {
    if (baselineP95Millis <= 0 || allowedRatio < 1.0) {
      throw new IllegalArgumentException("baseline must be positive and ratio must be at least 1.0");
    }
    double ratio = (double) candidateP95Millis / baselineP95Millis;
    return new RegressionReport(ratio, ratio <= allowedRatio);
  }

  public DiagnosticReport diagnosticReport(List<WorkloadReport> reports, LatencyReport latency, RegressionReport regression) {
    long unsafe = reports.stream().filter(report -> !report.completed()).count();
    return new DiagnosticReport(reports.size(), unsafe, latency.p95Millis(), regression.withinBudget());
  }

  private long percentile(List<Long> sorted, int percentile) {
    int index = (int) Math.ceil(percentile / 100.0 * sorted.size()) - 1;
    return sorted.get(Math.max(0, Math.min(index, sorted.size() - 1)));
  }

  public record WorkloadConfig(int iterations, int allocationBytes, int threads) {
    public void validate() {
      if (iterations <= 0 || iterations > MAX_ITERATIONS) {
        throw new IllegalArgumentException("iterations must be between 1 and " + MAX_ITERATIONS);
      }
      if (allocationBytes < 0 || allocationBytes > MAX_ALLOCATION_BYTES) {
        throw new IllegalArgumentException("allocation bytes must be bounded");
      }
      if (threads <= 0 || threads > MAX_THREADS) {
        throw new IllegalArgumentException("threads must be between 1 and " + MAX_THREADS);
      }
    }
  }
  public record WorkloadReport(String workload, long primaryCount, long secondaryCount, boolean completed) {}
  public record LatencyReport(long p50Millis, long p95Millis, long p99Millis, long maxMillis) {}
  public record RegressionReport(double ratio, boolean withinBudget) {}
  public record DiagnosticReport(int workloads, long unsafeWorkloads, long p95Millis, boolean regressionWithinBudget) {
    public boolean safeForDefaultTests() {
      return unsafeWorkloads == 0 && regressionWithinBudget;
    }
  }
}
