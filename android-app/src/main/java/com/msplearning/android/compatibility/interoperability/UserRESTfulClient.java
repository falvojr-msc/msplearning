package com.msplearning.android.compatibility.interoperability;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.User;

@Rest(rootUrl = RESTfulServerUtil.ROOT_URL, converters = { CustomGsonHttpMessageConverter.class })
public interface UserRESTfulClient extends RestClientSupport {

	@Post("/user/auth")
	Boolean authenticate(User user);

	@Get("/user/{username}")
	User findByUsername(String username);
}
