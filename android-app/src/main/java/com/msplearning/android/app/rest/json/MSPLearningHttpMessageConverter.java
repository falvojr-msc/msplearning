package com.msplearning.android.app.rest.json;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;
import com.msplearning.entity.common.json.GsonFactory;

/**
 * The MSPLearningHttpMessageConverter class extends {@link GsonHttpMessageConverter}, configuring the {@link Gson} object for serialization standards used were.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class MSPLearningHttpMessageConverter extends GsonHttpMessageConverter {

	public MSPLearningHttpMessageConverter() {
		super(GsonFactory.createGson());
	}
}