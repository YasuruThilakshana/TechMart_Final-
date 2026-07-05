package lk.jiat.techmart.performance;

import java.io.Serializable;

public class PerformanceMetric implements Serializable {

    private String methodName;

    private long executionCount;

    private long totalExecutionTime;

    private long minExecutionTime = Long.MAX_VALUE;

    private long maxExecutionTime = 0;

    public PerformanceMetric() {
    }

    public PerformanceMetric(String methodName) {
        this.methodName = methodName;
    }

    public synchronized void recordExecution(long executionTime) {

        executionCount++;

        totalExecutionTime += executionTime;

        if (executionTime < minExecutionTime) {
            minExecutionTime = executionTime;
        }

        if (executionTime > maxExecutionTime) {
            maxExecutionTime = executionTime;
        }

    }

    public double getAverageExecutionTime() {

        if (executionCount == 0) {
            return 0;
        }

        return (double) totalExecutionTime / executionCount;

    }



    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(long executionCount) {
        this.executionCount = executionCount;
    }

    public long getTotalExecutionTime() {
        return totalExecutionTime;
    }

    public void setTotalExecutionTime(long totalExecutionTime) {
        this.totalExecutionTime = totalExecutionTime;
    }

    public long getMinExecutionTime() {
        return executionCount == 0 ? 0 : minExecutionTime;
    }

    public void setMinExecutionTime(long minExecutionTime) {
        this.minExecutionTime = minExecutionTime;
    }

    public long getMaxExecutionTime() {
        return maxExecutionTime;
    }

    public void setMaxExecutionTime(long maxExecutionTime) {
        this.maxExecutionTime = maxExecutionTime;
    }

}