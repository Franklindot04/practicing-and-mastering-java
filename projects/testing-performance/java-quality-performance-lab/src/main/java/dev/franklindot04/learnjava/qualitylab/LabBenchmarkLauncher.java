package dev.franklindot04.learnjava.qualitylab;

import org.openjdk.jmh.runner.Runner; import org.openjdk.jmh.runner.options.OptionsBuilder;
public final class LabBenchmarkLauncher { public static void main(String[] args) throws Exception { new Runner(new OptionsBuilder().include(".*LabBenchmark.*").forks(1).warmupIterations(1).measurementIterations(1).build()).run(); } }
