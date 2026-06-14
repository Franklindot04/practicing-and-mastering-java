import java.util.List;
import java.util.Optional;

public class LambdasStreamsOptionalDemo {
    public static void main(String[] args) {
        List<String> topics = List.of("generics", "exceptions", "streams", "");

        List<String> cleaned = topics.stream()
            .filter(topic -> !topic.isBlank())
            .map(String::toUpperCase)
            .toList();

        System.out.println(cleaned);

        Optional<String> firstLongTopic = cleaned.stream()
            .filter(topic -> topic.length() > 7)
            .findFirst();

        firstLongTopic.ifPresent(topic -> System.out.println("Long topic: " + topic));
    }
}
