package com.msplearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Feature;
import com.msplearning.repository.FeatureRepository;

/**
 * The FeatureServiceJpa class provides the business operations of entity
 * {@link Feature}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("featureService")
public class FeatureService {

	@Autowired
	private FeatureRepository featureRepository;

	public List<Feature> getAll() {
		return this.featureRepository.findAll();
	}

}
