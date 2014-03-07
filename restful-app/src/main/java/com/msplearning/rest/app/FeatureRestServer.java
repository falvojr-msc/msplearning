package com.msplearning.rest.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Feature;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.service.FeatureService;

/**
 * The FeatureRESTfulServer class provides the RESTful services of entity
 * {@link Feature}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/feature")
public class FeatureRestServer {

	@Autowired
	private FeatureService featureService;

	@GET
	public Response getAll() {
		try {
			return Response.ok(this.featureService.getAll()).build();
		} catch (BusinessException businessException) {
			return Response.serverError().entity(businessException.getMessage()).build();
		}
	}
}
