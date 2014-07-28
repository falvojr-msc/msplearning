package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.AppUser;

/**
 * The AppUserRestClient interface provides the RESTful services of {@link AppUser} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface AppUserRestClient extends RestClientSupport {

	@Post("/appUser")
	AppUser insert(AppUser appUser);

	@Get("/appUser")
	AppUser[] findAll();

	@Get("/appUser/{idApp}/{idUser}")
	AppUser findById(Long idApp, Long idUser);

	@Get("/appUser/requests/{idApp}/{idUser}")
	AppUser[] findAccessRequests(Long idApp, Long idUser);

	@Put("/appUser")
	AppUser update(AppUser appUser);

	@Delete("/appUser/{idApp}/{idUser}")
	void delete(Long idApp, Long idUser);
}
