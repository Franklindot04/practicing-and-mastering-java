package dev.franklindot04.learnjava.observability;

import java.util.Random;

public final class DeterministicSampler {
    private final double probability;
    private final Random random;

    public DeterministicSampler(double probability, long seed) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("probability must be between 0 and 1");
        }
        this.probability = probability;
        this.random = new Random(seed);
    }

    public boolean shouldKeep() {
        return random.nextDouble() < probability;
    }
}
