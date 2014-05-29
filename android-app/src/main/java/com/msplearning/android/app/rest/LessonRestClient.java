package com.msplearning.android.app.rest;

import java.util.List;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Lesson;
import com.msplearning.entity.common.Response;

/**
 * The LessonRestClient interface provides the RESTful services of {@link Lesson} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface LessonRestClient extends RestClientSupport {

	@Post("/lesson")
	Response<Lesson> insert(Lesson lesson);

	@Put("/lesson")
	Response<Lesson> update(Lesson lesson);

	@Get("/lesson/{id}")
	Response<Lesson> findById(Long id);

	@Get("/lesson")
	Response<List<Lesson>> findAll();

	@Delete("/lesson/{id}")
	Response<Void> delete(Long id);

	@Post("/lesson/discipline")
	Response<List<Lesson>> findByDiscipline(Long idDiscipline);
}
