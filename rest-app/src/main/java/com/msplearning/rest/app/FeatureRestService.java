package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Feature;
import com.msplearning.service.FeatureService;

/**
 * The FeatureRestService class provides the RESTful services of entity {@link Feature}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/feature")
public class FeatureRestService {

	@Autowired
	private FeatureService featureService;

	@GET
	public List<Feature> getAll() {
		return this.featureService.getAll();
	}
}
