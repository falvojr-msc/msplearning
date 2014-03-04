package com.msplearning.android.app.interoperability;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.interoperability.util.RESTfulServer;
import com.msplearning.android.app.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Student;
import com.msplearning.entity.common.Response;

/**
 * The StudentRESTfulClient interface provides the RESTful services of
 * {@link Student} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RESTfulServer.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface StudentRESTfulClient extends RestClientSupport {

	@Post("/student")
	Response insert(Student student);

	@Put("/student")
	Response update(Student student);

	@Get("/student/{id}")
	Response findById(Long id);

	@Get("/student")
	Response findAll();

	@Delete("/student/{id}")
	Response delete(Long id);
}
