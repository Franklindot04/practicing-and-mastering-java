import java.util.ArrayList;
import java.util.List;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        OrderEvents events = new OrderEvents();
        events.subscribe(message -> System.out.println("Email subscriber: " + message));
        events.subscribe(message -> System.out.println("Audit subscriber: " + message));

        events.publish("Order shipped");
    }
}

interface EventSubscriber {
    void receive(String message);
}

class OrderEvents {
    private final List<EventSubscriber> subscribers = new ArrayList<>();

    void subscribe(EventSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    void publish(String message) {
        for (EventSubscriber subscriber : subscribers) {
            subscriber.receive(message);
        }
    }
}
