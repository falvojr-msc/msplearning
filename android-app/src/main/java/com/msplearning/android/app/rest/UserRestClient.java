package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.User;
import com.msplearning.entity.common.Response;

/**
 * The UserRestClient interface provides the RESTful services of {@link User} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface UserRestClient extends RestClientSupport {

	@Post("/user/auth")
	Response<User> authenticate(User user);

	@Get("/user/{email}")
	Response<Void> verifyEmail(String email);
}
