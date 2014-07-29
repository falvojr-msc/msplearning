package com.msplearning.rest.app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msplearning.entity.App;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.rest.app.generic.GenericCrudRestService;
import com.msplearning.rest.util.FileUtil;
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

	private static String DEFAULT_APK_PATH = "\\android-app\\target\\android-app-1.0-SNAPSHOT.apk";

	@Context
	private ServletContext context;

	@Autowired
	private AppService appService;

	@Override
	protected GenericCrudService<App, Long> getService() {
		return this.appService;
	}

	@GET
	@Path("/user/{idUser}")
	public List<App> findAppsByUser(@PathParam("idUser") Long idUser) {
		return this.appService.findByUser(idUser);
	}

	@POST
	@Path("/user/{idUser}")
	public App insert(App entity, @PathParam("idUser") Long idUser) throws BusinessException {
		this.appService.insert(entity, idUser);

		// Move this to AppService:
		String apkFolderPath = this.context.getRealPath(File.separator) + File.separator + entity.getId();
		FileUtil.makeFolder(apkFolderPath);
		try {
			File apk = new File(apkFolderPath + "APK");
			FileUtil.copy(new File(DEFAULT_APK_PATH), apk);
			// TODO: Edit apk file.
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}
	//TODO: REST service for change the project.properties file inside the APK.
}
