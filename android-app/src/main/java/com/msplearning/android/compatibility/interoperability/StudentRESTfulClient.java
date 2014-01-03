package com.msplearning.android.compatibility.interoperability;

import com.googlecode.androidannotations.annotations.rest.Delete;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.msplearning.android.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Student;

@Rest(rootUrl = "http://192.168.0.112:8080/restful-app", converters = { CustomGsonHttpMessageConverter.class })
public interface StudentRESTfulClient {

	@Post("/student/insert")
	Long insert(Student student);

	@Post("/student/update")
	void update(Student student);

	@Delete("/student/delete/{id}")
	void delete(Long id);
	
	@Get("/student/find/{id}")
	Student findById(Long id);
}
