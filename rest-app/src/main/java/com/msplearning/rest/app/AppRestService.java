package com.msplearning.rest.app;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.App;
import com.msplearning.entity.common.BusinessException;
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

		String rootPath = this.context.getRealPath(File.separator);
		this.appService.generateApk(entity.getId(), rootPath);

		return entity;
	}

	@Deprecated
	@GET
	@Path("/apk/{idApp}")
	public Boolean testGenerateApk(@PathParam("idApp") Long idApp) {
		String rootPath = this.context.getRealPath(File.separator);
		this.appService.generateApk(idApp, rootPath);
		return true;
	}
}
