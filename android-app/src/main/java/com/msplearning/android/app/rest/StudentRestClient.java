package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.Student;

/**
 * The StudentRestClient interface provides the RESTful services of {@link Student} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface StudentRestClient extends RestClientSupport {

	@Post("/student")
	Student insert(Student student);

	@Get("/student")
	Student[] findAll();

	@Get("/student/{id}")
	Student findById(Long id);

	@Put("/student")
	Student update(Student student);

	@Delete("/student/{id}")
	void delete(Long id);
}
