package com.github.dreambrother.jload;

/**
 *
 * @author nik
 */
public class JLoadTestResult {

    private final String testName;
    private final long executionCount;
    private final long executionTime;

    public JLoadTestResult(String testName, long executionCount, long executionTime) {
        this.testName = testName;
        this.executionCount = executionCount;
        this.executionTime = executionTime;
    }

    public String getTestName() {
        return testName;
    }

    public long getExecutionCount() {
        return executionCount;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
