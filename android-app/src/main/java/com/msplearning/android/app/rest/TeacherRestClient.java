package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.Teacher;

/**
 * The TeacherRestClient interface provides the RESTful services of {@link Teacher} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface TeacherRestClient extends RestClientSupport {

	@Post("/teacher")
	Teacher insert(Teacher student);

	@Get("/teacher")
	Teacher[] findAll();

	@Get("/teacher/{id}")
	Teacher findById(Long id);

	@Put("/teacher")
	Teacher update(Teacher student);

	@Delete("/teacher/{id}")
	void delete(Long id);
}
