package dev.franklindot04.learnjava.qualityperformance;

import java.util.LinkedHashMap;
import java.util.Map;

public final class StringAlgorithms {
    public Map<Character,Integer> frequencies(String input) {
        Map<Character,Integer> counts = new LinkedHashMap<>();
        for (char c : input.toCharArray()) counts.merge(c,1,Integer::sum);
        return counts;
    }
    public int totalCount(Map<Character,Integer> counts) { return counts.values().stream().mapToInt(Integer::intValue).sum(); }
}
