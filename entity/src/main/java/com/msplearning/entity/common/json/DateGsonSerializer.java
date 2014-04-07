package com.msplearning.entity.common.json;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * The DateGsonSerializer class. Useful for serializing and deserializing of type {@link Date}, working as an adapter for this type of data.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class DateGsonSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		return src == null ? null : new JsonPrimitive(src.getTime());
	}

	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return json == null ? null : new Date(json.getAsLong());
	}
}