package com.msplearning.rest.app.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.msplearning.entity.common.json.GsonFactory;

/**
 * The GsonMessageBody class implements {@link MessageBodyReader} and {@link MessageBodyWriter}, configuring the {@link Gson} object for serialization standards
 * used were.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonMessageBody implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

	@Override
	public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
		OutputStreamWriter writer = new OutputStreamWriter(entityStream, StandardCharsets.UTF_8);
		try {
			Type jsonType;
			if (type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}
			GsonFactory.createGson().toJson(object, jsonType, writer);
		} finally {
			writer.close();
		}
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream entityStream) throws IOException, WebApplicationException {
		InputStreamReader streamReader = new InputStreamReader(entityStream, StandardCharsets.UTF_8);
		try {
			Type jsonType;
			if (type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}
			return GsonFactory.createGson().fromJson(streamReader, jsonType);
		} finally {
			streamReader.close();
		}
	}
}
