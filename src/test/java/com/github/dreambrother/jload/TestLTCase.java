package com.github.dreambrother.jload;

import com.github.dreambrother.jload.annotations.LoadTest;

/**
 *
 * @author nik
 */
public class TestLTCase {

    @LoadTest(iterationCount = 10, threadCount = 1)
    public void loadTest1() throws InterruptedException {
        Thread.sleep(100L);
    }
    
    @LoadTest(iterationCount = 10, threadCount = 1)
    public void loadTest2() throws InterruptedException {
        Thread.sleep(300L);
    }
}
