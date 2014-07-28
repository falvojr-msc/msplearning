package com.msplearning.rest.app.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.msplearning.entity.common.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(BusinessException businessException) {
		return Response.status(Response.Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(businessException.getMessage())
				.build();
	}

}
