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
