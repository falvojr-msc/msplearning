package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.EducationalContent;

/**
 * The EducationalContentRestClient interface provides the RESTful services of {@link EducationalContent} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface EducationalContentRestClient extends RestClientSupport {

	@Post("/educationalContent")
	EducationalContent insert(EducationalContent educationalContent);

	@Get("/educationalContent")
	EducationalContent[] findAll();

	@Get("/educationalContent/{id}")
	EducationalContent findById(Long id);

	@Get("/educationalContent/lesson/{idLesson}")
	EducationalContent[] findByLesson(Long idLesson);

	@Put("/educationalContent")
	EducationalContent update(EducationalContent educationalContent);

	@Delete("/educationalContent/{id}")
	void delete(Long id);
}
