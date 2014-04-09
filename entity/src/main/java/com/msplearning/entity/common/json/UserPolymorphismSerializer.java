package com.msplearning.entity.common.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.msplearning.entity.Student;
import com.msplearning.entity.Teacher;
import com.msplearning.entity.User;

/**
 * The UserPolymorphismSerializer class. Useful for serializing and deserializing the types {@link User}, {@link Teacher} and {@link Student}. The super class
 * {@link User} is serialized by standard way, while its extensions require custom implementation.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class UserPolymorphismSerializer implements JsonSerializer<User>, JsonDeserializer<User> {

	private static final String PROPERTIES = "properties";
	private static final String CLASSNAME = "classname";

	@Override
	public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		if (jsonObject.has(CLASSNAME)) {
			String type = jsonObject.get(CLASSNAME).getAsString();
			JsonElement element = jsonObject.get(PROPERTIES);
			try {
				return context.deserialize(element, Class.forName(type));
			} catch (ClassNotFoundException exception) {
				throw new JsonParseException(exception);
			}
		} else {
			return GsonFactory.createGsonBuilderDateAdapters().create().fromJson(jsonObject, typeOfT);
		}
	}

	@Override
	public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
		if ((src instanceof Teacher) || (src instanceof Student)) {
			JsonObject result = new JsonObject();
			result.add(CLASSNAME, new JsonPrimitive(src.getClass().getCanonicalName()));
			result.add(PROPERTIES, context.serialize(src, src.getClass()));
			return result;
		} else {
			return new JsonParser().parse(GsonFactory.createGsonBuilderDateAdapters().create().toJson(src, typeOfSrc));
		}
	}

}