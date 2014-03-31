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
import com.msplearning.entity.Teacher;
import com.msplearning.entity.common.Response;

/**
 * The TeacherRestClient interface provides the RESTful services of {@link Teacher} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUrl.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface TeacherRestClient extends RestClientSupport {

	@Post("/teacher")
	Response<Teacher> insert(Teacher student);

	@Put("/teacher")
	Response<Teacher> update(Teacher student);

	@Get("/teacher/{id}")
	Response<Teacher> findById(Long id);

	@Get("/teacher")
	Response<List<Teacher>> findAll();

	@Delete("/teacher/{id}")
	Response<Void> delete(Long id);
}
