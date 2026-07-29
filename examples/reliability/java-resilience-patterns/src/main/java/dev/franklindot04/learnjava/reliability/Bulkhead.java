package dev.franklindot04.learnjava.reliability;

import java.util.concurrent.*;

public final class Bulkhead { private final Semaphore semaphore; public Bulkhead(int permits){if(permits<1)throw new IllegalArgumentException("permits must be positive");this.semaphore=new Semaphore(permits);} public int active(){return semaphore.getQueueLength()+availableLimit()-semaphore.availablePermits();} public int available(){return semaphore.availablePermits();} private int availableLimit(){return semaphore.availablePermits()+semaphore.getQueueLength();} public <T> T execute(Callable<T> work) throws Exception { if(!semaphore.tryAcquire()) throw new BulkheadRejectedException("bulkhead is full"); try{return work.call();} finally{semaphore.release();} } }
