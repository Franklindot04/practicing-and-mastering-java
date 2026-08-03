package dev.franklindot04.learnjava.qualityperformance;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public final class BenchmarkLauncher {
    public static void main(String[] args) throws Exception {
        new Runner(new OptionsBuilder().include(".*QualityBenchmark.*").forks(1).warmupIterations(1).measurementIterations(1).build()).run();
    }
}
