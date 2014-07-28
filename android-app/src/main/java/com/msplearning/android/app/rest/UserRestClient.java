package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.web.client.RestClientException;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.User;

/**
 * The UserRestClient interface provides the RESTful services of {@link User} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface UserRestClient extends RestClientSupport {

	@Post("/user/auth")
	User authenticate(User credential) throws RestClientException;

	@Get("/user/{email}")
	User findByEmail(String email) throws RestClientException;
}
