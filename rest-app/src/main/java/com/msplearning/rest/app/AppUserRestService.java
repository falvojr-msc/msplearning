package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.App;
import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.rest.app.generic.GenericBaseCrudRestService;
import com.msplearning.service.AppUserService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The AppUserRestService class provides the RESTful services of entity {@link App}. Methods getById and delete was overrided because of composite primary key.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/appUser")
public class AppUserRestService extends GenericBaseCrudRestService<AppUser, AppUserId> {

	@Autowired
	private AppUserService appUserService;

	@Override
	protected GenericCrudService<AppUser, AppUserId> getService() {
		return this.appUserService;
	}

	@GET
	@Path("/{idApp}/{idUser}")
	public AppUser findById(@PathParam("idApp") Long idApp, @PathParam("idUser") Long idUser) {
		return this.appUserService.findById(new AppUserId(idApp, idUser));
	}

	@GET
	@Path("/requests/{idApp}/{idUser}")
	public List<AppUser> findAccessRequests(@PathParam("idApp") Long idApp, @PathParam("idUser") Long idUser) {
		return this.appUserService.findAccessRequests(new AppUserId(idApp, idUser));
	}

	@DELETE
	@Path("/{idApp}/{idUser}")
	public void delete(@PathParam("idApp") Long idApp, @PathParam("idUser") Long idUser) {
		this.appUserService.delete(new AppUserId(idApp, idUser));
	}
}
