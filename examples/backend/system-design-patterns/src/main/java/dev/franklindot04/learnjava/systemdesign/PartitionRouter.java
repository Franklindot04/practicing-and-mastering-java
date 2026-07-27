package dev.franklindot04.learnjava.systemdesign;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public final class PartitionRouter {
    private final SortedMap<Integer, String> ring = new TreeMap<>();

    public PartitionRouter(List<String> partitions, int virtualNodes) {
        if (partitions.isEmpty() || virtualNodes <= 0) {
            throw new IllegalArgumentException("partitions and virtualNodes are required");
        }
        for (String partition : partitions) {
            for (int node = 0; node < virtualNodes; node++) {
                ring.put(hash(partition + "#" + node), partition);
            }
        }
    }

    public String route(String key) {
        int hash = hash(key);
        SortedMap<Integer, String> tail = ring.tailMap(hash);
        return (tail.isEmpty() ? ring.get(ring.firstKey()) : tail.get(tail.firstKey()));
    }

    public List<String> partitions() {
        return new ArrayList<>(ring.values().stream().distinct().sorted(Comparator.naturalOrder()).toList());
    }

    private int hash(String value) {
        return Math.floorMod(value.hashCode(), Integer.MAX_VALUE);
    }
}
