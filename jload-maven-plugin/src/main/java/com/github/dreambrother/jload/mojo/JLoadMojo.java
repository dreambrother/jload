package com.github.dreambrother.jload.mojo;

import com.github.dreambrother.jload.JLoadTestRunner;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal test
 * @requiresDependencyResolution test
 * @author nik
 */
public class JLoadMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(new JLoadTestRunner().run());
    }
}
