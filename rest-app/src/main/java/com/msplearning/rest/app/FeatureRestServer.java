package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Feature;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.entity.common.Response;
import com.msplearning.entity.common.Status;
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
	public Response<List<Feature>> getAll() {
		try {
			return new Response<List<Feature>>(Status.OK, this.featureService.getAll());
		} catch (BusinessException businessException) {
			return new Response<List<Feature>>(Status.OK, businessException.getMessage());
		}
	}
}
