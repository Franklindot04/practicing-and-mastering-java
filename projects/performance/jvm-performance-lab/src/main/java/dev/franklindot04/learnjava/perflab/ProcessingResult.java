package dev.franklindot04.learnjava.perflab;

import java.math.BigDecimal;
import java.util.Map;

public record ProcessingResult(Map<String, BigDecimal> totals, MetricsSnapshot metrics) {
}
