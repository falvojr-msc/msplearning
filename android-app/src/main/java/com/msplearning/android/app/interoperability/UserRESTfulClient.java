package com.msplearning.android.app.interoperability;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.interoperability.util.RESTfulServer;
import com.msplearning.android.app.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.User;
import com.msplearning.entity.common.Response;

/**
 * The UserRESTfulClient interface provides the RESTful services of {@link User}
 * entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RESTfulServer.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface UserRESTfulClient extends RestClientSupport {

	@Post("/user/auth")
	Response authenticate(User user);

	@Get("/user/{username}")
	Response findByUsername(String username);
}
