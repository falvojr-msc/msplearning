package com.msplearning.android.compatibility.interoperability;

import com.googlecode.androidannotations.annotations.rest.Delete;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.msplearning.android.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Student;
import com.msplearning.entity.Teacher;

@Rest(rootUrl = RESTfulServerUtil.ROOT_URL, converters = { CustomGsonHttpMessageConverter.class })
public interface TeacherRESTfulClient {

	@Post("/teacher/insert")
	Long insert(Teacher teacher);

	@Post("/teacher/update")
	void update(Teacher teacher);

	@Delete("/teacher/delete/{id}")
	void delete(Long id);
	
	@Get("/teacher/find/{id}")
	Student findById(Long id);
}
