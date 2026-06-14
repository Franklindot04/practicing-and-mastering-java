public class SynchronizedCounterDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();
        Thread first = new Thread(() -> incrementMany(counter));
        Thread second = new Thread(() -> incrementMany(counter));

        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println("Count: " + counter.value());
    }

    private static void incrementMany(SynchronizedCounter counter) {
        for (int count = 0; count < 1_000; count++) {
            counter.increment();
        }
    }
}

class SynchronizedCounter {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized int value() {
        return value;
    }
}
