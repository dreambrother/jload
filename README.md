jLoad
=====
Java libraries load testing framework
-------------------------------------

Test your java library (or just code unit!) with only one annotation!  
To use the framework, You just need to:  
*   Create test class with *LTCase suffix
*   Add @LoadTest annotaion to your test method
*   Add jload-maven-plugin to your Maven project and execute: mvn clean package jload:test  

Usage example:
```java
public class AppLTCase {
    
    private App app = new App();
    
    @LoadTest(iterationCount = 10, threadCount = 3)
    public void testOperation1Performance() {
        app.executeOperation1();
    }
        
    @LoadTest(iterationCount = 10, threadCount = 3, timeout = 5000L)
    public void testOperation2Performance() {
        app.executeOperation2();
    }
}
```

Plugin dependency declaration:
```xml
<plugin>
    <groupId>com.github.dreambrother</groupId>
    <artifactId>jload-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
</plugin>
```
Output example:

    Load tests execution result: 
    com.github.dreambrother.jload.TestLTCase.loadTest1
    Iterations: 10, execution time: 1003 ms
    Operations/sec: 9.97009
    Average iteration time: 100.300003 ms.

    com.github.dreambrother.jload.TestLTCase.loadTest2
    Iterations: 10, execution time: 3003 ms
    Operations/sec: 3.3300033
    Average iteration time: 300.299988 ms.

At this moment, plugin has not been loaded to the Maven Central Repository. You need to download the source code and execute mvn clean install at the root of the project.
