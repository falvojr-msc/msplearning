package com.msplearning.android.app.interoperability;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.interoperability.util.RestServerUrl;
import com.msplearning.android.app.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Teacher;
import com.msplearning.entity.common.Response;

/**
 * The TeacherRESTfulClient interface provides the RESTful services of
 * {@link Teacher} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUrl.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface TeacherRestClient extends RestClientSupport {

	@Post("/teacher")
	Response insert(Teacher student);

	@Put("/teacher")
	Response update(Teacher student);

	@Get("/teacher/{id}")
	Response findById(Long id);

	@Get("/teacher")
	Response findAll();

	@Delete("/teacher/{id}")
	Response delete(Long id);
}
