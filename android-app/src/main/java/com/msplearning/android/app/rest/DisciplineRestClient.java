package com.msplearning.android.app.rest;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.app.rest.json.MSPLearningHttpMessageConverter;
import com.msplearning.entity.Discipline;

/**
 * The DisciplineRestClient interface provides the RESTful services of {@link Discipline} entity.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUtil.ROOT_URL, converters = { MSPLearningHttpMessageConverter.class })
public interface DisciplineRestClient extends RestClientSupport {

	@Post("/discipline")
	Discipline insert(Discipline discipline);

	@Get("/discipline/{id}")
	Discipline findById(Long id);

	@Get("/discipline")
	Discipline[] findAll();

	@Put("/discipline")
	Discipline update(Discipline discipline);

	@Delete("/discipline/{id}")
	void delete(Long id);
}
