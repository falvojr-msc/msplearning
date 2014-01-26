package com.msplearning.restful.app;

import java.io.File;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
	 * This field is set by Spring on context:property-placeholder configured in
	 * applicationContext.xml
	 */
	@Value("${msplearning.basedirectory}")
	private String baseDirectory;

	@Path("apk/{id}")
	@GET
	@Produces(CustomMediaType.APPLICATION_ANDROID_PACKAGE_ARCHIVE)
	public Response getAndroidApk(@PathParam("id") Long idApp) throws BusinessException {
		try {
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File(this.baseDirectory + "\\pom.xml"));
			request.setGoals(Arrays.asList("-DskipTests=true", "verify"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("M3_HOME")));
			InvocationResult result = invoker.execute(request);

			if (result.getExitCode() == 0) {
				File fileApk = new File(this.baseDirectory + "\\android-app\\target\\customProduct.apk");
				return Response.ok(fileApk).header("Content-Disposition", "attachment;filename=MSPlearning.apk").build();
			} else {
				if (result.getExecutionException() == null) {
					throw new WebApplicationException(String.format("Failed to publish site. Exit code: %s.", result.getExitCode()),
							Status.INTERNAL_SERVER_ERROR);
				} else {
					throw new WebApplicationException("Failed to publish site.", result.getExecutionException(), Status.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
