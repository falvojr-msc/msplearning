package com.msplearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Feature;
import com.msplearning.entity.FeatureOperator;
import com.msplearning.repository.FeatureRepository;
import com.msplearning.service.generic.BaseService;

/**
 * The FeatureServiceJpa class provides the business operations of entity
 * {@link Feature}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("featureService")
public class FeatureService extends BaseService implements InitializingBean {

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

			Feature pedagogical = new Feature("Pedagogical", FeatureOperator.A, true, false, false);
			pedagogical.setChildren(new ArrayList<Feature>());
			pedagogical.getChildren().add(new Feature("Content Management", null, true, false, false));
			pedagogical.getChildren().add(new Feature("Educational Activities", null, true, false, false));
			pedagogical.getChildren().add(new Feature("Interactivity", null, true, false, false));
			pedagogical.getChildren().add(new Feature("Multimedia Resources", null, true, false, false));

			Feature usability = new Feature("Usability", FeatureOperator.A, true, false, false);
			usability.setChildren(new ArrayList<Feature>());
			usability.getChildren().add(new Feature("Accessibility", null, true, false, false));
			usability.getChildren().add(new Feature("Attractiveness", null, true, false, false));
			usability.getChildren().add(new Feature("Intelligibility", null, true, false, false));
			usability.getChildren().add(new Feature("Learnability", null, true, false, false));
			usability.getChildren().add(new Feature("Operability", null, true, false, false));

			Feature compatibility = new Feature("Compatibility", FeatureOperator.A, true, false, false);
			compatibility.setChildren(new ArrayList<Feature>());
			compatibility.getChildren().add(new Feature("Coexistence", null, true, false, false));
			compatibility.getChildren().add(new Feature("Interoperability", null, true, false, false));

			Feature security = new Feature("Security", FeatureOperator.A, true, false, false);
			security.setChildren(new ArrayList<Feature>());
			security.getChildren().add(new Feature("Accountablility", null, false, false, false));
			security.getChildren().add(new Feature("Authenticity", null, false, false, false));
			security.getChildren().add(new Feature("Confidentiality", null, false, false, false));
			security.getChildren().add(new Feature("Integrity", null, true, false, false));
			security.getChildren().add(new Feature("Reliability", null, true, false, false));

			this.featureRepository.insert(pedagogical);
			this.featureRepository.insert(usability);
			this.featureRepository.insert(compatibility);
			this.featureRepository.insert(security);
		}
	}

}
