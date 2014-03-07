package com.msplearning.rest.app;

import java.io.File;
import java.util.Arrays;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msplearning.entity.App;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.entity.common.Response;
import com.msplearning.entity.common.Status;
import com.msplearning.rest.app.generic.GenericCrudRestServer;
import com.msplearning.service.AppService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The StudentRESTfulServer class provides the RESTful services of entity
 * {@link App}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/app")
public class AppRestServer extends GenericCrudRestServer<App, Long> {

	/**
	 * This field is set by Spring on context:property-placeholder configured in
	 * applicationContext.xml
	 */
	@Value("${project.basedirectory}")
	private String baseDirectory;

	@Autowired
	private AppService appService;

	@Override
	protected GenericCrudService<App, Long> getService() {
		return this.appService;
	}

	@POST
	@Override
	public Response insert(App entity) {
		try {
			this.getService().insert(entity);

			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File(this.baseDirectory + "\\pom.xml"));
			request.setGoals(Arrays.asList("-DskipTests=true", "verify"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("M3_HOME")));
			InvocationResult result = invoker.execute(request);

			if (result.getExitCode() == 0) {
				String path = this.baseDirectory + "\\android-app\\target\\android-app-1.0-SNAPSHOT.apk";
				return new Response(Status.OK, new File(path));
			} else {
				return new Response(Status.SERVER_ERROR);
			}
		} catch (BusinessException businessException) {
			return new Response(Status.OK, businessException);
		} catch (MavenInvocationException mavenException) {
			return new Response(Status.SERVER_ERROR);
		}
	}
}
