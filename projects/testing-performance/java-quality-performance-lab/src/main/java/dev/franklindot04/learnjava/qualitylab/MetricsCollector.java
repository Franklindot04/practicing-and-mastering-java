package dev.franklindot04.learnjava.qualitylab;

import java.util.*;

public final class MetricsCollector { private final List<Long> latencies=new ArrayList<>(); private int success; public void record(long micros, boolean ok){ latencies.add(micros); if(ok) success++; } public MetricsSnapshot snapshot(){ return new MetricsSnapshot(List.copyOf(latencies), success, latencies.size()-success); } }
record MetricsSnapshot(List<Long> latencies, int success, int failures) { double throughputPerSecond(long elapsedMillis){ return elapsedMillis<=0?0:(latencies.size()*1000.0)/elapsedMillis; } }
final class PercentileCalculator { long percentile(List<Long> values, double p){ if(values.isEmpty()) return 0; List<Long> sorted=new ArrayList<>(values); Collections.sort(sorted); int index=(int)Math.ceil(p/100.0*sorted.size())-1; return sorted.get(Math.max(0, Math.min(index, sorted.size()-1))); } }
record PerformanceBudget(long p95Micros, int maxFailures) { boolean accepts(MetricsSnapshot snapshot){ return new PercentileCalculator().percentile(snapshot.latencies(),95) <= p95Micros && snapshot.failures() <= maxFailures; } }
record RegressionGate(PerformanceBudget budget) { String evaluate(MetricsSnapshot snapshot){ return budget.accepts(snapshot) ? "PASS within budget" : "FAIL budget exceeded: p95=" + new PercentileCalculator().percentile(snapshot.latencies(),95) + " failures=" + snapshot.failures(); } }
