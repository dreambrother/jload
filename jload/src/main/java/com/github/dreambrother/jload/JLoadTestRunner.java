package com.github.dreambrother.jload;

import com.github.dreambrother.jload.annotations.LoadTest;
import com.github.dreambrother.jload.exceptions.TestExecutionException;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author nik
 */
public class JLoadTestRunner {
    
    private static final File TEST_FOLDER = new File("src/test/java");
    
    private final JLoadTestScanner scanner = new JLoadTestScanner();
    private final JLoadTestFormatter formatter = new JLoadTestFormatter();

    /**
     * @return formatted test execution result
     */
    public String run() {
        return run(null);
    }

    /**
     * @return formatted test execution result
     */
    public String run(ClassLoader classLoader) {
        final List<String> tests = scanner.findAllTests(TEST_FOLDER);
        List<JLoadTestResult> result = new LinkedList<>();
        for (String test : tests) {
            try {
                Class<?> testClass = classLoader != null ? classLoader.loadClass(test)  : Class.forName(test);
                Object testInstance = testClass.newInstance();
                Method[] methods = testClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(LoadTest.class)) {
                        LoadTest annotation = method.getAnnotation(LoadTest.class);
                        result.add(executeTestMethod(testInstance, method, annotation.iterationCount(),
                                annotation.timeout(), annotation.threadCount()));
                    }
                }
            } catch (Exception ex) {
                throw new TestExecutionException(ex);
            }
        }
        return formatter.format(result);
    }
    
    private JLoadTestResult executeTestMethod(Object testInstance, Method testMethod, long iterationCount, 
            long timeout, int threadCount) {
        if (threadCount < 1) {
            throw new IllegalArgumentException("Thread count cannot be lesser than 1. Given: " + threadCount);
        }
        if (timeout < 0) {
            throw new IllegalArgumentException("Timeout cannot be lesser than 0. Given: " + timeout);
        }
        if (iterationCount < 1) {
            throw new IllegalArgumentException("Iteration count cannot be lesser than 1. Given: " + iterationCount);
        }
        
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        TestTask task = new TestTask(testInstance, testMethod, iterationCount / threadCount);
        TestTask lastTask = new TestTask(testInstance, testMethod, 
                iterationCount / threadCount + iterationCount % threadCount);
        List<Future<?>> futures = new ArrayList<>(threadCount);
        long startTime = System.nanoTime();
        for(int i = 0; i < threadCount - 1; i++) {
            futures.add(threadPool.submit(task));
        }
        futures.add(threadPool.submit(lastTask));
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception ex) {
                throw new TestExecutionException(ex);
            }
        }
        long endTime = System.nanoTime();
        return new JLoadTestResult(testMethod.getDeclaringClass().getCanonicalName() + "." + testMethod.getName(), 
                iterationCount, (endTime - startTime) / 1_000_000);
    }
    
    private static final class TestTask implements Runnable {

        private final Object testInstance;
        private final Method testMethod;
        private final long iterationCount;

        public TestTask(Object testInstance, Method testMethod, long iterationCount) {
            this.testInstance = testInstance;
            this.testMethod = testMethod;
            this.iterationCount = iterationCount;
        }
        
        @Override
        public void run() {
            for (int i = 0; i < iterationCount; i++) {
                try {
                    testMethod.invoke(testInstance);
                } catch (Exception ex) {
                    throw new TestExecutionException(ex);
                }
            }
        }
    }
}
