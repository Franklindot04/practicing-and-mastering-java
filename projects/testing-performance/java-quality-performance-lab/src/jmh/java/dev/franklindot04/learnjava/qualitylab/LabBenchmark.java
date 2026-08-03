package dev.franklindot04.learnjava.qualitylab;

import java.time.Clock; import java.time.Instant; import java.time.ZoneOffset; import java.util.concurrent.TimeUnit; import org.openjdk.jmh.annotations.*;
@BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS) @Warmup(iterations=1) @Measurement(iterations=1) @Fork(1) @State(Scope.Thread)
public class LabBenchmark { QualityPerformanceLab lab; int id; @Setup public void setup(){ lab=new QualityPerformanceLab(QualityPerformanceLab.repositoryWithStock(10_000), sku -> "fresh:"+sku, new LabCache(), new MetricsCollector(), Clock.fixed(Instant.EPOCH, ZoneOffset.UTC)); } @Benchmark public Response reserveBaseline(){ return lab.reserve(new Request("b" + id++, "SKU1", 1)); } @Benchmark public long cpuFocused(){ return lab.cpuCost(200); } }
