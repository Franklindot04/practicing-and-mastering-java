package dev.franklindot04.learnjava.qualitylab;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import org.junit.jupiter.api.Test;

class QualityPerformanceLabTest {
    private QualityPerformanceLab lab(LabCache cache, MetricsCollector metrics){ return new QualityPerformanceLab(QualityPerformanceLab.repositoryWithStock(100), sku -> "fresh:"+sku, cache, metrics, Clock.fixed(Instant.parse("2026-08-03T00:00:00Z"), ZoneOffset.UTC)); }
    @Test void deterministicServiceBoundaryUsesFakeRepositoryAndClock(){ var metrics=new MetricsCollector(); var response=lab(new LabCache(),metrics).reserve(new Request("r1","SKU1",2)); assertAll(() -> assertTrue(response.accepted()), () -> assertTrue(response.message().contains("2026-08-03")), () -> assertEquals(1, metrics.snapshot().success())); }
    @Test void contractCompatibilityAllowsOptionalGrowthAndRejectsRemoval(){ var verifier=new ContractVerifier(); var oldC=new Contract("order", List.of("id","sku"),1); assertTrue(verifier.compatible(oldC,new Contract("order",List.of("id","sku","optionalNote"),2))); assertFalse(verifier.compatible(oldC,new Contract("order",List.of("id"),2))); }
    @Test void seededWorkloadReportsPercentilesAndRegressionBudget(){ var metrics=new MetricsCollector(); var result=lab(new LabCache(),metrics).run(new WorkloadScenario(new WorkloadProfile("baseline",20,1,29),false,false,false)); assertEquals(20,result.requests()); assertTrue(new PercentileCalculator().percentile(result.metrics().latencies(),95) > 0); assertEquals("PASS within budget", new RegressionGate(new PerformanceBudget(1_000_000,0)).evaluate(result.metrics())); assertTrue(new RegressionGate(new PerformanceBudget(0,0)).evaluate(result.metrics()).startsWith("FAIL")); }
    @Test void propertyInvariantKeepsStockNonNegative(){ var repo=QualityPerformanceLab.repositoryWithStock(10); var lab=new QualityPerformanceLab(repo, sku -> "fresh", new LabCache(), new MetricsCollector(), Clock.systemUTC()); Random random=new Random(2905); for(int i=0;i<20;i++) lab.reserve(new Request("p"+i,"SKU0",1+random.nextInt(3))); assertTrue(new InvariantChecker().stockNeverNegative(repo,"SKU0")); }
    @Test void mutationAndArchitectureQualityGateShowsWeakAndStrongSignals(){ var gate=new QualityGate(); assertTrue(gate.weakMutationSurvives(1,5)); assertFalse(gate.strongMutationCaught(1,5)); assertFalse(gate.architectureAllowed("api->repository")); assertTrue(gate.architectureAllowed("api->service")); }
    @Test void cacheMetricsAndStalenessAreObservable(){ var cache=new LabCache(); var metrics=new MetricsCollector(); var l=lab(cache,metrics); l.reserve(new Request("a","SKU1",1)); l.reserve(new Request("b","SKU1",1)); assertEquals(1,cache.misses()); assertEquals(1,cache.hits()); cache.put("SKU1","old"); assertTrue(cache.stale("SKU1","fresh:SKU1")); }
    @Test void diagnosticScenariosRemainBounded() throws Exception { assertTrue(DiagnosticScenario.run("allocation").bounded()); assertTrue(DiagnosticScenario.run("contention").bounded()); }
    @Test void throughputAndFailureMetricsAreCalculated(){ var snapshot=new MetricsSnapshot(List.of(100L,200L,300L),2,1); assertEquals(30.0, snapshot.throughputPerSecond(100),0.001); assertFalse(new PerformanceBudget(500,0).accepts(snapshot)); }
}
