public class PolymorphismDemo {
    public static void main(String[] args) {
        Notification[] notifications = {
            new EmailNotification(),
            new SmsNotification()
        };

        for (Notification notification : notifications) {
            notification.send("Practice complete");
        }
    }
}

interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}
