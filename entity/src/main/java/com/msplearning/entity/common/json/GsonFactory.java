package com.msplearning.entity.common.json;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonFactory {

	private GsonFactory() {
		super();
	}

	public static Gson createGson() {
		return new GsonBuilder()
		.registerTypeAdapter(Date.class, new DateGsonSerializer())
		.registerTypeAdapter(java.sql.Date.class, new DateGsonSerializer())
		.registerTypeAdapter(java.sql.Timestamp.class, new DateGsonSerializer())
		.create();
	}
}
