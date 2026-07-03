package lk.jiat.techmart.performance;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class PerformanceMonitorBean {

    private static final Map<String, PerformanceMetric> METRICS =
            new ConcurrentHashMap<>();

    @Lock(LockType.WRITE)
    public void recordExecution(String methodName, long executionTime) {

        PerformanceMetric metric =
                METRICS.computeIfAbsent(
                        methodName,
                        PerformanceMetric::new
                );

        metric.recordExecution(executionTime);

    }

    @Lock(LockType.READ)
    public Collection<PerformanceMetric> getAllMetrics() {

        return METRICS.values();

    }

    @Lock(LockType.READ)
    public PerformanceMetric getMetric(String methodName) {

        return METRICS.get(methodName);

    }

}