package com.github.dreambrother.jload;

import java.util.List;

/**
 *
 * @author nik
 */
public class JLoadTestPrinter {

    public void print(List<JLoadTestResult> testResults) {
        System.out.println("Load tests execution result: ");
        for (JLoadTestResult jLoadTestResult : testResults) {
            System.out.println(jLoadTestResult.getTestName());
            System.out.println(String.format("Iterations: %d, execution time: %d", 
                    jLoadTestResult.getExecutionCount(), jLoadTestResult.getExecutionTime()));
            System.out.println("Operations/sec: " + 
                    jLoadTestResult.getExecutionCount() / (jLoadTestResult.getExecutionTime() / 1000f));
            System.out.println(String.format("Average iteration time: %f ms.", (float) jLoadTestResult.getExecutionTime() / jLoadTestResult.getExecutionCount()));
            System.out.println();
        }
    }
}
