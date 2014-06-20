package com.msplearning.android.app.rest;

import java.util.List;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Slide;
import com.msplearning.entity.common.Response;

/**
 * The SlideRestClient interface provides the RESTful services of {@link Slide} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface SlideRestClient extends RestClientSupport {

	@Post("/slide")
	Response<Slide> insert(Slide slide);

	@Put("/slide")
	Response<Slide> update(Slide slide);

	@Get("/slide/{id}")
	Response<Slide> findById(Long id);

	@Get("/slide")
	Response<List<Slide>> findAll();

	@Delete("/slide/{id}")
	Response<Void> delete(Long id);

	@Post("/slide/lesson")
	Response<List<Slide>> findByLesson(Long idLesson);
}
