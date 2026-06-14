import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        List<String> words = List.of("java", "thread", "java", "lock", "thread", "java");
        Map<String, Integer> counts = new ConcurrentHashMap<>();

        words.parallelStream().forEach(word -> counts.merge(word, 1, Integer::sum));

        System.out.println(counts);
    }
}
