package com.msplearning.rest.app;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msplearning.entity.App;
import com.msplearning.rest.app.generic.GenericCrudRestService;
import com.msplearning.service.AppService;
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

	@Override
	protected GenericCrudService<App, Long> getService() {
		return this.appService;
	}

	//TODO: REST service for change the project.properties file inside the APK.
}
