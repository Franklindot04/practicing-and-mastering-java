import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

public class CollectionsDemo {
    public static void main(String[] args) {
        listExamples();
        setExamples();
        queueAndDequeExamples();
        mapExamples();
        iteratorExample();
        sortingExamples();
        collectionsUtilityExample();
    }

    private static void listExamples() {
        List<String> topics = new ArrayList<>();
        topics.add("variables");
        topics.add("loops");
        topics.add("collections");
        System.out.println("ArrayList keeps insertion order: " + topics);

        LinkedList<String> reviewQueue = new LinkedList<>();
        reviewQueue.add("List");
        reviewQueue.add("Set");
        reviewQueue.add("Map");
        reviewQueue.removeFirst();
        System.out.println("LinkedList after removing first topic: " + reviewQueue);
    }

    private static void setExamples() {
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("Ada");
        uniqueNames.add("Grace");
        uniqueNames.add("Ada");
        System.out.println("HashSet removes duplicates: " + uniqueNames);

        Set<String> orderedNames = new LinkedHashSet<>();
        orderedNames.add("first");
        orderedNames.add("second");
        orderedNames.add("third");
        System.out.println("LinkedHashSet keeps insertion order: " + orderedNames);

        Set<Integer> sortedScores = new TreeSet<>();
        sortedScores.add(80);
        sortedScores.add(95);
        sortedScores.add(72);
        System.out.println("TreeSet sorts values: " + sortedScores);
    }

    private static void queueAndDequeExamples() {
        Queue<String> tasks = new ArrayDeque<>();
        tasks.add("compile");
        tasks.add("test");
        tasks.add("commit");
        System.out.println("Queue next task: " + tasks.poll());

        Deque<String> browserHistory = new ArrayDeque<>();
        browserHistory.push("docs");
        browserHistory.push("examples");
        browserHistory.push("exercises");
        System.out.println("Deque back button goes to: " + browserHistory.pop());

        Queue<Integer> priorities = new PriorityQueue<>();
        priorities.add(3);
        priorities.add(1);
        priorities.add(2);
        System.out.println("PriorityQueue first priority: " + priorities.poll());
    }

    private static void mapExamples() {
        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("java", 3);
        wordCounts.put("collections", 2);
        System.out.println("HashMap count for java: " + wordCounts.get("java"));

        Map<String, Integer> insertionOrder = new LinkedHashMap<>();
        insertionOrder.put("first", 1);
        insertionOrder.put("second", 2);
        insertionOrder.put("third", 3);
        System.out.println("LinkedHashMap keeps insertion order: " + insertionOrder);

        Map<String, Integer> sortedByKey = new TreeMap<>(wordCounts);
        System.out.println("TreeMap sorts keys: " + sortedByKey);
    }

    private static void iteratorExample() {
        List<String> names = new ArrayList<>(List.of("Ada", "", "Grace"));
        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().isBlank()) {
                iterator.remove();
            }
        }

        System.out.println("Iterator safely removed blanks: " + names);
    }

    private static void sortingExamples() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ada", 95));
        students.add(new Student("Grace", 99));
        students.add(new Student("James", 88));

        students.sort(Comparator.comparingInt(Student::score).reversed());
        System.out.println("Comparator sorted students by score: " + students);

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Streams", 7));
        courses.add(new Course("Basics", 1));
        courses.add(new Course("Collections", 3));
        Collections.sort(courses);
        System.out.println("Comparable sorted courses by order: " + courses);
    }

    private static void collectionsUtilityExample() {
        List<Integer> numbers = new ArrayList<>(List.of(4, 1, 9, 2));
        Collections.sort(numbers);
        System.out.println("Collections.sort: " + numbers);
        System.out.println("Collections.max: " + Collections.max(numbers));
        Collections.reverse(numbers);
        System.out.println("Collections.reverse: " + numbers);
    }
}

record Student(String name, int score) {
}

record Course(String title, int order) implements Comparable<Course> {
    @Override
    public int compareTo(Course other) {
        return Integer.compare(this.order, other.order);
    }
}
