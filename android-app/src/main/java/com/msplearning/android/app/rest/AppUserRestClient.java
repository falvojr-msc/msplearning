package com.msplearning.android.app.rest;

import java.util.List;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.entity.common.Response;

/**
 * The AppUserRestClient interface provides the RESTful services of {@link AppUser} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface AppUserRestClient extends RestClientSupport {

	@Post("/appUser")
	Response<AppUser> insert(AppUser appUser);

	@Put("/appUser")
	Response<AppUser> update(AppUser appUser);

	@Post("/appUser/findById")
	Response<AppUser> findById(AppUserId id);

	@Get("/appUser")
	Response<List<AppUser>> findAll();

	@Post("/appUser/delete")
	Response<Void> delete(Long id);
	
	@Post("/appUser/findManagedAppUsersFrom")
	Response<List<AppUser>> findManagedAppUsersFrom(AppUserId appUser);
}
