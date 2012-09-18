package com.github.dreambrother.jload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nik
 */
public class JLoadTestScanner {

    public List<String> findAllTests(File dir) {
        return findAllTests("", dir);
    }
    
    private List<String> findAllTests(String currentPackage, File dir) {
        final List<String> result = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                result.addAll(findAllTests(currentPackage + "." + file.getName(), file));
            } else {
                if (file.getName().endsWith("LTCase.java")) {
                    result.add(currentPackage.replaceFirst(".", "") + "." 
                            + file.getName().replaceFirst(".java", ""));
                }
            }
        }
        return result;
    }
}
