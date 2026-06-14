public class FactoryMethodPatternDemo {
    public static void main(String[] args) {
        Notification notification = NotificationFactory.create("email");
        notification.send("Welcome!");
    }
}

interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsNotification implements Notification {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

class NotificationFactory {
    static Notification create(String type) {
        if ("sms".equalsIgnoreCase(type)) {
            return new SmsNotification();
        }
        return new EmailNotification();
    }
}
