package org.molgenis.jsx;

import java.util.Arrays;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "transform", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class JSXTransformerMojo extends AbstractMojo
{
	@Parameter(property = "transform.reactPath", defaultValue = "${project.basedir}/src/main/resources/js")
	private String reactPath;

	@Parameter(property = "transform.jsxTransformerJS", defaultValue = "JSXTransformer.js")
	private String jsxTransformerJS;

	@Parameter(property = "transform.jsxFolder", defaultValue = "${project.basedir}/src/main/resources/jsx")
	private String jsxFolder;

	@Parameter(property = "transform.outputFolder", defaultValue = "${project.build.directory}/generated-resources/js")
	private String outputFolder;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException
	{
		try
		{
			JSXTransformer jsxTransformer = new JSXTransformer();
			jsxTransformer.setModulePaths(Arrays.asList(reactPath));
			jsxTransformer.setJsxTransformerJS(jsxTransformerJS);
			jsxTransformer.init();
			jsxTransformer.compileFiles(jsxFolder, outputFolder);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}

}
