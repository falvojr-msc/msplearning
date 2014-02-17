package com.msplearning.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Feature;
import com.msplearning.entity.FeatureOperator;
import com.msplearning.repository.FeatureRepository;

/**
 * The FeatureServiceJpa class provides the business operations of entity
 * {@link Feature}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("featureService")
public class FeatureService implements InitializingBean {

	@Autowired
	private FeatureRepository featureRepository;

	public List<Feature> getAll() {
		return this.featureRepository.findAll();
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		this.createFeaturesIfThereAreNo();
	}

	/**
	 * Checks for {@link Feature}'s in the database for, if applicable, create standards {@link Feature}'s.
	 */
	private void createFeaturesIfThereAreNo() {
		if (this.featureRepository.findAll().size() == 0) {

			Feature pedagogical;
			this.featureRepository.insert(pedagogical = new Feature(1L, "Pedagogical", FeatureOperator.A, true, false, false));
			this.featureRepository.insert(new Feature(2L, "Content Management", null, true, false, false, pedagogical));
			this.featureRepository.insert(new Feature(3L, "Educational Activities", null, true, false, false, pedagogical));
			this.featureRepository.insert(new Feature(4L, "Interactivity", null, true, false, false, pedagogical));
			this.featureRepository.insert(new Feature(5L, "Multimedia Resources", null, true, false, false, pedagogical));

			Feature usability;
			this.featureRepository.insert(usability = new Feature(6L, "Usability", FeatureOperator.A, true, false, false));
			this.featureRepository.insert(new Feature(7L, "Accessibility", null, true, false, false, usability));
			this.featureRepository.insert(new Feature(8L, "Attractiveness", null, true, false, false, usability));
			this.featureRepository.insert(new Feature(9L, "Intelligibility", null, true, false, false, usability));
			this.featureRepository.insert(new Feature(10L, "Learnability", null, true, false, false, usability));
			this.featureRepository.insert(new Feature(11L, "Operability", null, true, false, false, usability));

			Feature compatibility;
			this.featureRepository.insert(compatibility = new Feature(12L, "Compatibility", FeatureOperator.A, true, false, false));
			this.featureRepository.insert(new Feature(13L, "Coexistence", null, true, false, false, compatibility));
			this.featureRepository.insert(new Feature(14L, "Interoperability", null, true, false, false, compatibility));

			Feature security;
			this.featureRepository.insert(security = new Feature(15L, "Security", FeatureOperator.A, true, false, false));
			this.featureRepository.insert(new Feature(16L, "Accountablility", null, false, false, false, security));
			this.featureRepository.insert(new Feature(17L, "Authenticity", null, false, false, false, security));
			this.featureRepository.insert(new Feature(18L, "Confidentiality", null, false, false, false, security));
			this.featureRepository.insert(new Feature(19L, "Integrity", null, true, false, false, security));
			this.featureRepository.insert(new Feature(20L, "Reliability", null, true, false, false, security));
		}
	}

}
