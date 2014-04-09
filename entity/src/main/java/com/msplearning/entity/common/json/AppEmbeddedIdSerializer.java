package com.msplearning.entity.common.json;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.msplearning.entity.App;
import com.msplearning.entity.AppFeatureId;
import com.msplearning.entity.AppUserId;

/**
 * The AppEmbeddedIdSerializer class. Useful for serializing and deserializing of {@link AppFeatureId} and {@link AppUserId} classes.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class AppEmbeddedIdSerializer<T extends Serializable> implements JsonSerializer<T> {

	@Override
	public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
		GsonBuilder gsonBuilder = GsonFactory.createGsonBuilderDateAdapters();

		if (src instanceof AppFeatureId) {
			AppFeatureId appFeatureId = ((AppFeatureId) src);
			appFeatureId.setApp(new App(appFeatureId.getApp().getId()));
		} else if (src instanceof AppUserId) {
			AppUserId appUserId = ((AppUserId) src);
			appUserId.setApp(new App(appUserId.getApp().getId()));
		}

		return new JsonParser().parse(gsonBuilder.create().toJson(src, typeOfSrc));
	}

}