package dev.franklindot04.learnjava.qualityperformance;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class PropertyAndQualityTest {
    @Test void seededGeneratedStringsPreserveFrequencyInvariant() {
        var algorithms = new StringAlgorithms(); var random = new Random(2900L);
        for (int sample = 0; sample < 100; sample++) {
            int length = random.nextInt(40); StringBuilder s = new StringBuilder();
            for (int i=0;i<length;i++) s.append((char)('a' + random.nextInt(5)));
            var counts = algorithms.frequencies(s.toString());
            assertEquals(s.length(), algorithms.totalCount(counts), "seed=2900 sample=" + sample + " input=" + s);
        }
    }
    @Test void mutationSimulationShowsWeakAssertionAndStrongerAssertion() {
        var rules = new QualityRules();
        assertTrue(rules.weaklyAcceptsPositive(1, 5), "weak assertion lets an impossible reservation survive");
        assertFalse(rules.stronglyAcceptsReservation(1, 5));
    }
    @Test void architectureGateDetectsForbiddenDependencyAndBoundaryRule() {
        var rules = new QualityRules();
        assertFalse(rules.architecture(List.of("controller->repository", "domain->port")).passed());
        assertTrue(rules.architecture(List.of("controller->service", "domain->port", "adapter->port")).passed());
    }
}
