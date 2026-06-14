import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> greeting = CompletableFuture
                .supplyAsync(() -> "java")
                .thenApply(String::toUpperCase)
                .thenApply(value -> "Hello, " + value);

        System.out.println(greeting.join());
    }
}
