public class DependencyInversionDemo {
    public static void main(String[] args) {
        NotificationService service = new NotificationService(new ConsoleMessageSender());
        service.notifyUser("Build completed");
    }
}

interface MessageSender {
    void send(String message);
}

class ConsoleMessageSender implements MessageSender {
    public void send(String message) {
        System.out.println(message);
    }
}

class NotificationService {
    private final MessageSender sender;

    NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    void notifyUser(String message) {
        sender.send(message);
    }
}
