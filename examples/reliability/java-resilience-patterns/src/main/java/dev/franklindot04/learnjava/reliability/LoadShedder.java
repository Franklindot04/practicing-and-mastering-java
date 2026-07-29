package dev.franklindot04.learnjava.reliability;

import java.util.concurrent.*;import java.util.concurrent.atomic.*;

public final class LoadShedder { private final Semaphore permits; private final AtomicInteger accepted=new AtomicInteger(); private final AtomicInteger rejected=new AtomicInteger(); public LoadShedder(int limit){if(limit<1)throw new IllegalArgumentException("limit must be positive"); permits=new Semaphore(limit);} public int accepted(){return accepted.get();} public int rejected(){return rejected.get();} public <T> T execute(RequestBudget budget, Callable<T> work) throws Exception { if(budget!=null&&budget.isExpired()){rejected.incrementAndGet(); throw new RejectedExecutionException("request budget expired");} if(!permits.tryAcquire()){rejected.incrementAndGet(); throw new RejectedExecutionException("load shedder saturated");} accepted.incrementAndGet(); try{return work.call();} finally{permits.release();} } }
