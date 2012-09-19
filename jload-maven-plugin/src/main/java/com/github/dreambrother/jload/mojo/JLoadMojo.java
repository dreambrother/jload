package com.github.dreambrother.jload.mojo;

import com.github.dreambrother.jload.JLoadTestRunner;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * @goal test
 * @requiresDependencyResolution test
 * @author nik
 */
public class JLoadMojo extends AbstractMojo {

    /**
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            List runtimeClasspathElements = project.getTestClasspathElements();
            URL[] runtimeUrls = new URL[runtimeClasspathElements.size()];
            for (int i = 0; i < runtimeClasspathElements.size(); i++) {
                String element = (String) runtimeClasspathElements.get(i);
                runtimeUrls[i] = new File(element).toURI().toURL();
            }
            URLClassLoader newLoader = new URLClassLoader(runtimeUrls,
                    Thread.currentThread().getContextClassLoader());
            getLog().info(new JLoadTestRunner().run(newLoader));
        } catch (MalformedURLException | DependencyResolutionRequiredException ex) {
            throw new MojoExecutionException(ex.getMessage(), ex);
        }
    }
}
