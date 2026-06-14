# Collections Framework Solutions

## Solution 1: Unique Names

```java
Set<String> names = new LinkedHashSet<>(List.of("Ada", "Grace", "Ada", "James"));
System.out.println(names);
```

## Solution 2: Word Frequency Counter

```java
String sentence = "java is fun and java is powerful";
Map<String, Integer> counts = new LinkedHashMap<>();

for (String word : sentence.split(" ")) {
    counts.put(word, counts.getOrDefault(word, 0) + 1);
}

System.out.println(counts);
```

## Solution 3: Ticket Queue

```java
Queue<String> tickets = new ArrayDeque<>();
tickets.offer("Login issue");
tickets.offer("Payment issue");
tickets.offer("Profile update");

while (!tickets.isEmpty()) {
    System.out.println("Processing: " + tickets.poll());
}
```

## Solution 4: Sort Students

```java
record Student(String name, int score) {}

List<Student> students = new ArrayList<>();
students.add(new Student("Ada", 95));
students.add(new Student("Grace", 99));
students.add(new Student("James", 88));

students.sort(Comparator.comparingInt(Student::score).reversed());
System.out.println(students);
```

## Solution 5: Safe Removal

```java
List<String> values = new ArrayList<>(List.of("Java", "", "Collections", " "));
Iterator<String> iterator = values.iterator();

while (iterator.hasNext()) {
    if (iterator.next().trim().isEmpty()) {
        iterator.remove();
    }
}

System.out.println(values);
```
