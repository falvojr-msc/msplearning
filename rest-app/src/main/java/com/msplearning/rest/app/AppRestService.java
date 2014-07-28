package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msplearning.entity.App;
import com.msplearning.rest.app.generic.GenericCrudRestService;
import com.msplearning.service.AppService;
import com.msplearning.service.AppUserService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The AppRestService class provides the RESTful services of entity {@link App}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/app")
public class AppRestService extends GenericCrudRestService<App, Long> {

	/**
	 * This field is set by Spring on context:property-placeholder configured in applicationContext.xml
	 */
	@Value("${project.basedirectory}")
	private String baseDirectory;

	@Autowired
	private AppService appService;

	@Autowired
	private AppUserService appUserService;

	@Override
	protected GenericCrudService<App, Long> getService() {
		return this.appService;
	}

	@GET
	@Path("/user/{idUser}")
	public List<App> findAppsByUser(@PathParam("idUser") Long idUser) {
		return this.appUserService.findAppsByUser(idUser);
	}

	//TODO: REST service for change the project.properties file inside the APK.
}
