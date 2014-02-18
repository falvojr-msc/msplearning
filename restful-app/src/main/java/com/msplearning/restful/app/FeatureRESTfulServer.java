package com.msplearning.restful.app;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Feature;
import com.msplearning.service.FeatureService;

/**
 * The FeatureRESTfulServer class provides the RESTful services of entity
 * {@link Feature}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/feature")
public class FeatureRESTfulServer {

	@Autowired
	private FeatureService featureService;

	@GET
	public List<Feature> getAll() {
		return this.featureService.getAll();
	}

}
