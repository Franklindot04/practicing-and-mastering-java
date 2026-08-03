package dev.franklindot04.learnjava.qualityperformance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ProfilingWorkloads {
    private final Object lock = new Object();
    private final List<byte[]> retained = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        String mode = args.length == 0 ? "cpu" : args[0];
        ProfilingWorkloads workloads = new ProfilingWorkloads();
        switch (mode) {
            case "cpu" -> System.out.println(workloads.cpuHeavy(25_000));
            case "allocation" -> System.out.println(workloads.allocateMany(2_000));
            case "lock" -> System.out.println(workloads.lockContention());
            case "blocked" -> System.out.println(workloads.blockedThread());
            case "retained" -> System.out.println(workloads.retainedObjects(64));
            default -> throw new IllegalArgumentException("unknown mode: " + mode);
        }
    }
    long cpuHeavy(int limit) { long sum=0; for(int n=2;n<limit;n++){ if(isPrime(n)) sum+=n; } return sum; }
    private boolean isPrime(int n){ for(int d=2; d*d<=n; d++){ if(n%d==0) return false; } return true; }
    long allocateMany(int batches){ long total=0; for(int i=0;i<batches;i++){ byte[] data=new byte[1024]; total+=data.length; } return total; }
    int retainedObjects(int count){ retained.clear(); for(int i=0;i<count;i++) retained.add(new byte[1024]); return retained.size(); }
    int lockContention() throws InterruptedException { ExecutorService pool=Executors.newFixedThreadPool(4); CountDownLatch done=new CountDownLatch(4); for(int i=0;i<4;i++) pool.submit(() -> { for(int j=0;j<2_000;j++) synchronized(lock) { Math.sqrt(j); } done.countDown(); }); done.await(2, TimeUnit.SECONDS); pool.shutdownNow(); return (int) done.getCount(); }
    String blockedThread() throws InterruptedException { CountDownLatch started=new CountDownLatch(1); Thread t=new Thread(() -> { synchronized(lock) { started.countDown(); try { Thread.sleep(250); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); } } }, "bounded-blocking-demo"); synchronized(lock){ t.start(); started.await(1,TimeUnit.SECONDS); } t.join(1000); return "blocked scenario completed"; }
}
