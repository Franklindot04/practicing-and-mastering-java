import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        LockedCounter counter = new LockedCounter();
        Thread first = new Thread(() -> incrementMany(counter));
        Thread second = new Thread(() -> incrementMany(counter));

        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println("Count: " + counter.value());
    }

    private static void incrementMany(LockedCounter counter) {
        for (int count = 0; count < 1_000; count++) {
            counter.increment();
        }
    }
}

class LockedCounter {
    private final ReentrantLock lock = new ReentrantLock();
    private int value;

    public void increment() {
        lock.lock();
        try {
            value++;
        } finally {
            lock.unlock();
        }
    }

    public int value() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}
