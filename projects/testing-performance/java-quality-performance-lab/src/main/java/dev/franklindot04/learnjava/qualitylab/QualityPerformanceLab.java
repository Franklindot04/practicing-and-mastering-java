package dev.franklindot04.learnjava.qualitylab;

import java.time.Clock;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class QualityPerformanceLab {
    private final Repository repository; private final ExternalDependency dependency; private final LabCache cache; private final MetricsCollector metrics; private final Clock clock;
    public QualityPerformanceLab(Repository repository, ExternalDependency dependency, LabCache cache, MetricsCollector metrics, Clock clock){ this.repository=repository; this.dependency=dependency; this.cache=cache; this.metrics=metrics; this.clock=clock; }
    public Response reserve(Request request){ long start=System.nanoTime(); if(request.quantity()<1 || request.quantity()>20) return finish(request,false,"invalid",start); String cached=cache.get(request.sku()); if(cached==null){ cached=dependency.fetch(request.sku()); cache.put(request.sku(), cached); } boolean ok=repository.reserve(request.sku(), request.quantity()); return finish(request, ok, ok ? cached : "rejected", start); }
    private Response finish(Request r, boolean ok, String msg, long start){ long micros=Math.max(1,(System.nanoTime()-start)/1000); metrics.record(micros, ok); return new Response(r.id(), ok, micros, msg + "@" + clock.instant()); }
    public WorkloadResult run(WorkloadScenario scenario){ Random random=new Random(scenario.profile().seed()); for(int i=0;i<scenario.profile().requests();i++){ reserve(new Request("r"+i, "SKU" + random.nextInt(3), 1 + random.nextInt(3))); if(scenario.cpuHeavy()) cpuCost(150); if(scenario.allocationHeavy()) new byte[512].hashCode(); } return new WorkloadResult(metrics.snapshot(), scenario.profile().requests()); }
    public long cpuCost(int limit){ long sum=0; for(int n=2;n<limit;n++){ boolean prime=true; for(int d=2;d*d<=n;d++) if(n%d==0){prime=false;break;} if(prime) sum+=n; } return sum; }
    public interface Repository { boolean reserve(String sku, int quantity); int available(String sku); }
    public interface ExternalDependency { String fetch(String sku); }
    public record WorkloadResult(MetricsSnapshot metrics, int requests) {}
    public static InMemoryRepository repositoryWithStock(int stock){ InMemoryRepository r=new InMemoryRepository(); r.put("SKU0",stock); r.put("SKU1",stock); r.put("SKU2",stock); return r; }
    public static final class InMemoryRepository implements Repository { private final Map<String,Integer> stock=new HashMap<>(); public void put(String sku,int quantity){stock.put(sku,quantity);} public synchronized boolean reserve(String sku,int quantity){ int available=available(sku); if(available<quantity)return false; stock.put(sku,available-quantity); return true;} public int available(String sku){return stock.getOrDefault(sku,0);} }
}
