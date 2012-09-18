package com.github.dreambrother.jload;

import java.util.List;

/**
 *
 * @author nik
 */
public class JLoadTestFormatter {

    public String print(List<JLoadTestResult> testResults) {
        final StringBuilder result = new StringBuilder("Load tests execution result: \n");
        for (JLoadTestResult jLoadTestResult : testResults) {
            result.append(jLoadTestResult.getTestName())
                    .append("\n")
                    .append(String.format("Iterations: %d, execution time: %d", 
                        jLoadTestResult.getExecutionCount(), jLoadTestResult.getExecutionTime()))
                    .append("\n")
                    .append("Operations/sec: ")
                    .append(jLoadTestResult.getExecutionCount() / (jLoadTestResult.getExecutionTime() / 1000f))
                    .append("\n")
                    .append(String.format("Average iteration time: %f ms.", 
                        (float) jLoadTestResult.getExecutionTime() / jLoadTestResult.getExecutionCount()))
                    .append("\n\n");
        }
        return result.toString();
    }
}
