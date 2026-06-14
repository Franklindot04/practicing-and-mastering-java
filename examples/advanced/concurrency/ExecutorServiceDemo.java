import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int taskNumber = 1; taskNumber <= 4; taskNumber++) {
            int number = taskNumber;
            executor.submit(() -> System.out.println("Task " + number + " ran on " + Thread.currentThread().getName()));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
