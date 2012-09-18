package com.github.dreambrother.javalibloadtesting;

import com.github.dreambrother.jload.annotations.LoadTest;

/**
 *
 * @author nik
 */
public class TestLTCase {

    @LoadTest(iterationCount = 10, threadCount = 1)
    public void loadTest() throws InterruptedException {
        Thread.sleep(100L);
    }
    
    @LoadTest(iterationCount = 10, threadCount = 1)
    public void loadTest2() throws InterruptedException {
        Thread.sleep(300L);
    }
}
