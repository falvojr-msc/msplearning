package com.msplearning.android.rest;

import java.util.List;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.rest.generic.RestServerUrl;
import com.msplearning.android.rest.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.App;
import com.msplearning.entity.common.Response;

/**
 * The AppRestClient interface provides the RESTful services of {@link App} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUrl.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface AppRestClient extends RestClientSupport {

	@Post("/app")
	Response<App> insert(App app);

	@Put("/app")
	Response<App> update(App app);

	@Get("/app/{id}")
	Response<App> findById(Long id);

	@Get("/app")
	Response<List<App>> findAll();

	@Delete("/app/{id}")
	Response<Void> delete(Long id);
}
