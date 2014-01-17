package com.msplearning.restful.app;

import java.io.File;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msplearning.restful.app.generic.CustomMediaType;
import com.msplearning.service.exception.BusinessException;

/**
 * The DownloadRESTfulServer class provides the RESTful services of the
 * resources download.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/product")
public class ProductRESTfulServer {

	/**
	 * Cached {@link Invoker} that execute Maven Build commands in RESTful
	 * services.
	 */
	private final Invoker invoker = new DefaultInvoker();

	/**
	 * This field is set by Spring on context:property-placeholder configured in
	 * applicationContext.xml
	 */
	@Value("${msplearning.local.repository.directory}")
	private String repositoryDirectory;

	/**
	 * Method that set the local repository directory, based on
	 * <code>repositoryDirectory</code> property.
	 */
	@PostConstruct
	public void afterSpringInjections() {
		this.invoker.setLocalRepositoryDirectory(new File(this.repositoryDirectory));
	}

	@Path("apk/{id}")
	@GET
	@Produces(CustomMediaType.APPLICATION_ANDROID_PACKAGE_ARCHIVE)
	public Response getAndroidApk(@PathParam("id") Long idApp) throws BusinessException {
		try {
			InvocationRequest request = new DefaultInvocationRequest();
			request.setGoals(Arrays.asList("clean", "install"));

			InvocationResult result = this.invoker.execute(request);

			if (result.getExitCode() != 0) {
				if (result.getExecutionException() == null) {
				} else {
				}
			}

			File fileApk = new File(this.repositoryDirectory);
			return Response.ok(fileApk).header("Content-Disposition", "attachment;filename=MSPlearning.apk").build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
