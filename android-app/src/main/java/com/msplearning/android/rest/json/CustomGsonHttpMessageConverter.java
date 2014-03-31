package com.msplearning.android.rest.json;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;
import com.msplearning.entity.common.json.GsonFactory;

/**
 * The CustomGsonHttpMessageConverter class extends {@link GsonHttpMessageConverter}, configuring the {@link Gson} object for serialization standards used were.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class CustomGsonHttpMessageConverter extends GsonHttpMessageConverter {

	public CustomGsonHttpMessageConverter() {
		super(GsonFactory.createGson());
	}

}