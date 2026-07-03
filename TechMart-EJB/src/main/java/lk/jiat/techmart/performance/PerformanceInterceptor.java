package lk.jiat.techmart.performance;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@PerformanceMonitor
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class PerformanceInterceptor {

    private static final Logger LOGGER =
            Logger.getLogger(PerformanceInterceptor.class.getName());

    @Inject
    private PerformanceMonitorBean performanceMonitorBean;

    @AroundInvoke
    public Object monitorPerformance(InvocationContext context) throws Exception {

        long startTime = System.nanoTime();

        try {

            return context.proceed();

        } finally {

            long executionTime =
                    (System.nanoTime() - startTime) / 1_000_000;

            String methodName =
                    context.getTarget().getClass().getSimpleName()
                            + "."
                            + context.getMethod().getName();

            performanceMonitorBean.recordExecution(methodName, executionTime);

            LOGGER.warning(methodName + " executed in "
                    + executionTime + " ms");

        }

    }

}