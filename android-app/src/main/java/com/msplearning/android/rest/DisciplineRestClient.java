package com.msplearning.android.rest;

import java.util.List;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;

import com.msplearning.android.rest.generic.RestServerUrl;
import com.msplearning.android.rest.json.CustomGsonHttpMessageConverter;
import com.msplearning.entity.Discipline;
import com.msplearning.entity.User;
import com.msplearning.entity.common.Response;

/**
 * The DisciplineRestClient interface provides the RESTful services of {@link User} entity.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Rest(rootUrl = RestServerUrl.DEBUG, converters = { CustomGsonHttpMessageConverter.class })
public interface DisciplineRestClient extends RestClientSupport {

	@Post("/discipline")
	Response<Discipline> insert(Discipline Discipline);

	@Put("/discipline")
	Response<Discipline> update(Discipline Discipline);

	@Get("/discipline/{id}")
	Response<Discipline> findById(Long id);

	@Get("/discipline")
	Response<List<Discipline>> findAll();

	@Delete("/discipline/{id}")
	Response<Void> delete(Long id);
}
