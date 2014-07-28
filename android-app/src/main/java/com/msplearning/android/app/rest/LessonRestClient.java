package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.Lesson;

/**
 * The LessonRestClient interface provides the RESTful services of {@link Lesson} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface LessonRestClient extends RestClientSupport {

	@Post("/lesson")
	Lesson insert(Lesson lesson);

	@Get("/lesson")
	Lesson[] findAll();

	@Get("/lesson/{id}")
	Lesson findById(Long id);

	@Get("/lesson/discipline/{idDiscipline}")
	Lesson[] findByDiscipline(Long idDiscipline);

	@Put("/lesson")
	Lesson update(Lesson lesson);

	@Delete("/lesson/{id}")
	void delete(Long id);
}
