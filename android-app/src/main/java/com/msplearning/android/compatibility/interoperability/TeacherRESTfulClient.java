package com.msplearning.android.compatibility.interoperability;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Teacher;

@Rest(rootUrl = RESTfulServerUtil.ROOT_URL, converters = { CustomGsonHttpMessageConverter.class })
public interface TeacherRESTfulClient extends RestClientSupport {

	@Post("/teacher")
	Teacher insert(Teacher student);

	@Put("/teacher")
	Teacher update(Teacher student);

	@Get("/teacher/{id}")
	Teacher findById(Long id);
	
	@Get("/teacher")
	void findAll();
	
	@Delete("/teacher/{id}")
	void delete(Long id);
}
