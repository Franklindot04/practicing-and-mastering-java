import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> 21 * 2;

        Future<Integer> result = executor.submit(task);
        System.out.println("Result: " + result.get());

        executor.shutdown();
    }
}
