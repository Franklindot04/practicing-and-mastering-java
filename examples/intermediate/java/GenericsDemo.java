import java.util.List;

public class GenericsDemo {
    public static void main(String[] args) {
        Box<String> topic = new Box<>("Generics");
        Box<Integer> score = new Box<>(95);

        System.out.println(topic.value());
        System.out.println(score.value());
        printAll(List.of("List", "Set", "Map"));
    }

    private static <T> void printAll(List<T> values) {
        for (T value : values) {
            System.out.println(value);
        }
    }
}

record Box<T>(T value) {
}
