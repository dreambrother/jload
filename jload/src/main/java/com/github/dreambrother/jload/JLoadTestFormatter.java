/**
 * Copyright 2012-2013 Nikita Shmakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.dreambrother.jload;

import java.util.List;

/**
 *
 * @author nik
 */
public class JLoadTestFormatter {

    public String format(List<JLoadTestResult> testResults) {
        final StringBuilder result = new StringBuilder("Load tests execution result: \n");
        for (JLoadTestResult jLoadTestResult : testResults) {
            result.append(jLoadTestResult.getTestName())
                    .append("\n")
                    .append(String.format("Iterations: %d, execution time: %d ms", 
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
