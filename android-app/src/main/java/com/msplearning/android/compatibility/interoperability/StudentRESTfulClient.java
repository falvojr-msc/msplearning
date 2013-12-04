package com.msplearning.android.compatibility.interoperability;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.msplearning.entity.Student;

@Rest(rootUrl = "http://10.0.2.2:8080/restful-app", converters = { FormHttpMessageConverter.class, GsonHttpMessageConverter.class })
public interface StudentRESTfulClient {

	@Post("/insert/student/")
	Student insert(Student student);
}
