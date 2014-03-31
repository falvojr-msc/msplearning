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
import com.msplearning.entity.Student;
import com.msplearning.entity.common.Response;

/**
 * The StudentRestClient interface provides the RESTful services of {@link Student} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUrl.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface StudentRestClient extends RestClientSupport {

	@Post("/student")
	Response<Student> insert(Student student);

	@Put("/student")
	Response<Student> update(Student student);

	@Get("/student/{id}")
	Response<Student> findById(Long id);

	@Get("/student")
	Response<List<Student>> findAll();

	@Delete("/student/{id}")
	Response<Void> delete(Long id);
}
