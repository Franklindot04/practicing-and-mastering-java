package dev.franklindot04.learnjava.qualityperformance;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(1)
@State(Scope.Thread)
public class QualityBenchmark {
    private StringAlgorithms algorithms;
    private String input;
    @Setup public void setup() { algorithms = new StringAlgorithms(); input = "stage29-quality-performance".repeat(20); }
    @Benchmark public int correctBenchmark(Blackhole blackhole) { var counts = algorithms.frequencies(input); blackhole.consume(counts); return algorithms.totalCount(counts); }
    @Benchmark public int naiveConstantFoldRisk() { return "constant".length(); }
    @Benchmark public byte[] allocationFocused() { return new byte[4096]; }
}
