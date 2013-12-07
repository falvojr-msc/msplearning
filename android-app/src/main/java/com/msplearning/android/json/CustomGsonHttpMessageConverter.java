package com.msplearning.android.json;

import java.util.Date;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msplearning.entity.json.DateGsonSerializer;

/**
 * The CustomGsonHttpMessageConverter class extends
 * {@link GsonHttpMessageConverter}, configuring the {@link Gson} object for
 * serialization standards used were.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class CustomGsonHttpMessageConverter extends GsonHttpMessageConverter {

	private static Gson gson;

	public CustomGsonHttpMessageConverter() {
		super(CustomGsonHttpMessageConverter.getGson());
	}

	private static Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder()
				.registerTypeAdapter(Date.class, new DateGsonSerializer())
				.registerTypeAdapter(java.sql.Date.class, new DateGsonSerializer())
				.registerTypeAdapter(java.sql.Timestamp.class, new DateGsonSerializer())
				.create();
		}
		return gson;
	}
}
