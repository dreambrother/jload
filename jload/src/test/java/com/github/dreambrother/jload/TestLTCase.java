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
