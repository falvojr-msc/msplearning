package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.web.client.RestClientException;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.App;

/**
 * The AppRestClient interface provides the RESTful services of {@link App} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface AppRestClient extends RestClientSupport {

	@Post("/app")
	App insert(App app) throws RestClientException;

	@Get("/app")
	App[] findAll();

	@Get("/app/{id}")
	App findById(Long id);

	@Put("/app")
	App update(App app) throws RestClientException;

	@Delete("/app/{id}")
	void delete(Long id);
}
