package com.msplearning.rest.app;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.App;
import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.entity.common.Response;
import com.msplearning.rest.app.generic.GenericCrudRestService;
import com.msplearning.service.AppUserService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The AppUserRestService class provides the RESTful services of entity {@link App}. Methods getById and delete was overrided because of composite primary key.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/appuser")
public class AppUserRestService extends GenericCrudRestService<AppUser, AppUserId> {

	@Autowired
	private AppUserService appUserService;

	@Override
	protected GenericCrudService<AppUser, AppUserId> getService() {
		return this.appUserService;
	}

	@POST
	@Path("/getById")
	@Override
	public Response<AppUser> getById(AppUserId id) {
		return super.getById(id);
	}

	@POST
	@Path("/delete")
	@Override
	public Response<Void> delete(AppUserId id) {
		return super.delete(id);
	}
}
