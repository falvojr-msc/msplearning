package com.msplearning.android.compatibility.interoperability;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.msplearning.entity.Student;

@Rest(rootUrl = "http://192.168.0.103:8080/restful-app", converters = { FormHttpMessageConverter.class, GsonHttpMessageConverter.class })
public interface StudentRESTfulClient {

	@Post("/student/insert")
	Student insert(Student student);
}
