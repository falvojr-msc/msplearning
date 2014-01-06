package com.msplearning.android.compatibility.interoperability;

import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.msplearning.android.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.User;

@Rest(rootUrl = RESTfulServerUtil.ROOT_URL, converters = { CustomGsonHttpMessageConverter.class })
public interface UserRESTfulClient {

	@Post("/user/auth")
	Boolean authenticate(User user);
	
	@Get("/user/find/{username}")
	User findByUsername(String username);
}
