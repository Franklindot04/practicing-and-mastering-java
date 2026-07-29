package dev.franklindot04.learnjava.reliability;

import java.util.concurrent.*;

public final class InMemoryIdempotencyStore<T> implements IdempotencyStore<T> { private record Entry<T>(String fingerprint, FutureTask<T> task) {} private final ConcurrentHashMap<String, Entry<T>> entries=new ConcurrentHashMap<>(); public T executeOnce(String key,String fp,Callable<T> work) throws Exception { Entry<T> created=new Entry<>(fp,new FutureTask<>(work)); Entry<T> existing=entries.putIfAbsent(key,created); Entry<T> chosen=existing==null?created:existing; if(!chosen.fingerprint.equals(fp)) throw new IllegalArgumentException("idempotency key reused with different fingerprint"); if(existing==null) created.task.run(); try{return chosen.task.get();} catch(ExecutionException ex){ if(ex.getCause() instanceof Exception e) throw e; throw new RuntimeException(ex.getCause()); }} }
