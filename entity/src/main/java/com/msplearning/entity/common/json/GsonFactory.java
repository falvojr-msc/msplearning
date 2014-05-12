package com.msplearning.entity.common.json;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msplearning.entity.AppFeatureId;
import com.msplearning.entity.AppUserId;
import com.msplearning.entity.Discipline;
import com.msplearning.entity.Lesson;
import com.msplearning.entity.User;

public final class GsonFactory {

	private GsonFactory() {
		super();
	}

	static GsonBuilder createGsonBuilderDateAdapters() {
		return new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateGsonSerializer())
			.registerTypeAdapter(java.sql.Date.class, new DateGsonSerializer())
			.registerTypeAdapter(java.sql.Timestamp.class, new DateGsonSerializer());
	}

	public static Gson createGson() {
		return GsonFactory.createGsonBuilderDateAdapters()
			.registerTypeAdapter(AppFeatureId.class, new BidirectionalManyToOneSerializer<AppFeatureId>())
			.registerTypeAdapter(AppUserId.class, new BidirectionalManyToOneSerializer<AppUserId>())
			.registerTypeAdapter(Discipline.class, new BidirectionalManyToOneSerializer<Discipline>())
			.registerTypeAdapter(Lesson.class, new BidirectionalManyToOneSerializer<Lesson>())
			.registerTypeAdapter(User.class, new UserPolymorphismSerializer())
			.create();
	}
}
