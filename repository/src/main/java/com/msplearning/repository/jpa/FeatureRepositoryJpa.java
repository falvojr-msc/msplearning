package com.msplearning.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.Feature;
import com.msplearning.repository.FeatureRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The FeatureRepositoryJpa class provides the persistence operations of entity {@link Feature}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("featureRepository")
public class FeatureRepositoryJpa extends GenericRepositoryJpa<Feature, Long> implements FeatureRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Feature> findAll() {
		return (List<Feature>) super.findByJPQL("FROM Feature f WHERE f.id IN (1, 2, 3, 4)");
	}

}
