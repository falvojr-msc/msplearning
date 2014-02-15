package com.msplearning.repository;

import com.msplearning.entity.Feature;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.repository.jpa.FeatureRepositoryJpa;

/**
 * Interface of {@link FeatureRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface FeatureRepository extends GenericRepository<Feature, Long> {

}
