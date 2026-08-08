package dev.franklindot04.learnjava.capstone.diagnostics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;

class DiagnosticsWorkbenchTest {
  private final DiagnosticsWorkbench workbench = new DiagnosticsWorkbench();
  private final DiagnosticsWorkbench.WorkloadConfig config = new DiagnosticsWorkbench.WorkloadConfig(1_000, 32_000, 2);

  @Test
  void validatesStrictWorkloadBounds() {
    assertThrows(IllegalArgumentException.class, () -> new DiagnosticsWorkbench.WorkloadConfig(0, 1, 1).validate());
    assertThrows(IllegalArgumentException.class, () -> new DiagnosticsWorkbench.WorkloadConfig(1, DiagnosticsWorkbench.MAX_ALLOCATION_BYTES + 1, 1).validate());
    assertThrows(IllegalArgumentException.class, () -> new DiagnosticsWorkbench.WorkloadConfig(1, 1, DiagnosticsWorkbench.MAX_THREADS + 1).validate());
  }

  @Test
  void completesCpuAllocationRetainedCacheAndGcObservationStyleWorkloads() {
    assertTrue(workbench.cpuHeavy(config).completed());
    assertTrue(workbench.allocationHeavy(config).completed());
    assertTrue(workbench.retainedObjects(config).completed());
    assertTrue(workbench.cachePressure(config).completed());
  }

  @Test
  void completesConcurrencyWorkloadsWithoutPermanentDeadlock() {
    assertTrue(workbench.lockContention(config).completed());
    assertTrue(workbench.blockedThread(config).completed());
    assertTrue(workbench.threadPoolSaturation(config).completed());
  }

  @Test
  void calculatesLatencyDistributionDeterministically() {
    DiagnosticsWorkbench.LatencyReport report = workbench.latencyDistribution(List.of(1L, 2L, 3L, 4L, 100L));

    assertEquals(3, report.p50Millis());
    assertEquals(100, report.p95Millis());
    assertEquals(100, report.p99Millis());
    assertEquals(100, report.maxMillis());
  }

  @Test
  void detectsRegressionBudgetBehavior() {
    assertTrue(workbench.regressionBudget(100, 110, 1.20).withinBudget());
    assertFalse(workbench.regressionBudget(100, 140, 1.20).withinBudget());
  }

  @Test
  void createsDiagnosticReportForSafeDefaultTests() {
    DiagnosticsWorkbench.WorkloadReport cpu = workbench.cpuHeavy(config);
    DiagnosticsWorkbench.WorkloadReport allocation = workbench.allocationHeavy(config);
    DiagnosticsWorkbench.LatencyReport latency = workbench.latencyDistribution(List.of(10L, 20L, 30L, 40L));
    DiagnosticsWorkbench.RegressionReport regression = workbench.regressionBudget(40, 42, 1.10);

    DiagnosticsWorkbench.DiagnosticReport report = workbench.diagnosticReport(List.of(cpu, allocation), latency, regression);

    assertTrue(report.safeForDefaultTests());
    assertEquals(2, report.workloads());
  }
}
