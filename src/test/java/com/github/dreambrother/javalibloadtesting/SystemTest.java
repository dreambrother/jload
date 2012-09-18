package com.github.dreambrother.javalibloadtesting;

import com.github.dreambrother.jload.JLoadTestRunner;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SystemTest {

    @Test
    public void test() {
        new JLoadTestRunner().run();
    }
}
