package com.msplearning.android.compatibility.interoperability;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Student;

@Rest(rootUrl = RESTfulServerUtil.ROOT_URL, converters = { CustomGsonHttpMessageConverter.class })
public interface StudentRESTfulClient extends RestClientSupport {
	
	@Post("/student")
	Student insert(Student student);

	@Put("/student")
	Student update(Student student);

	@Get("/student/{id}")
	Student findById(Long id);
	
	@Get("/student")
	void findAll();
	
	@Delete("/student/{id}")
	void delete(Long id);
}
